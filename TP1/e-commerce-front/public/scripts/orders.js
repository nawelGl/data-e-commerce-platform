import { renderNavbar } from "../components/navbar.js";
import { setHTML, setText, $ } from "../components/dom.js";
import { showToast } from "../components/toast.js";
import { ORDER_STATUSES } from "../constants/back.js";
import { membershipApi } from "../api/membershipApi.js";
import { productApi } from "../api/productApi.js";
import { orderApi } from "../api/orderApi.js";

setHTML("navbar", renderNavbar("orders"));

function setError(msg) {
  setText("error", msg || "");
}

async function loadSelects() {
  const [users, products] = await Promise.all([membershipApi.list(), productApi.list()]);

  $("o_user").innerHTML =
    `<option value="">-- user --</option>` +
    users.map((u) => `<option value="${u.id}">${u.name} (${u.email})</option>`).join("");

  $("o_product").innerHTML =
    `<option value="">-- product --</option>` +
    products.map((p) => `<option value="${p.id}">${p.name} (stock:${p.stock})</option>`).join("");
}

function renderItems(order) {
  if (!order.items || !order.items.length) return "";
  return order.items
    .map((it) => `${it.productName} x ${it.quantity} (${Number(it.subtotal).toFixed(2)})`)
    .join("<br/>");
}

async function loadOrders() {
  const orders = await orderApi.list();
  $("table").innerHTML =
    `<tr>
      <th>ID</th><th>UserId</th><th>Status</th><th>Total</th><th>Address</th><th>Items</th><th>Actions</th>
    </tr>` +
    orders
      .map((o) => {
        const immutable = o.status === "DELIVERED" || o.status === "CANCELLED";
        return `
          <tr>
            <td>${o.id}</td>
            <td>${o.userId}</td>
            <td><span class="badge">${o.status}</span></td>
            <td>${Number(o.totalAmount).toFixed(2)}</td>
            <td>${o.shippingAddress}</td>
            <td>${renderItems(o)}</td>
            <td class="actions">
              <select data-status="${o.id}" ${immutable ? "disabled" : ""}>
                ${ORDER_STATUSES.map((st) => `<option ${st === o.status ? "selected" : ""}>${st}</option>`).join("")}
              </select>
              <button data-update="${o.id}" ${immutable ? "disabled" : ""}>Update</button>
              <button data-cancel="${o.id}" ${immutable ? "disabled" : ""}>Cancel</button>
            </td>
          </tr>
        `;
      })
      .join("");
}

$("orderForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  try {
    setError("");
    const payload = {
      userId: Number($("o_user").value),
      shippingAddress: $("o_address").value,
      items: [{ productId: Number($("o_product").value), quantity: Number($("o_qty").value) }],
    };
    await orderApi.create(payload);
    showToast("Order created");
    e.target.reset();
    await loadOrders();
  } catch (err) {
    setError(err.message || "Create order failed");
  }
});

$("table").addEventListener("click", async (e) => {
  const updBtn = e.target.closest("button[data-update]");
  const cancelBtn = e.target.closest("button[data-cancel]");

  try {
    if (updBtn) {
      const id = updBtn.getAttribute("data-update");
      const sel = document.querySelector(`select[data-status="${id}"]`);
      await orderApi.updateStatus(id, sel.value);
      showToast("Status updated");
      await loadOrders();
    }
    if (cancelBtn) {
      const id = cancelBtn.getAttribute("data-cancel");
      await orderApi.cancel(id);
      showToast("Order cancelled");
      await loadOrders();
    }
  } catch (err) {
    setError(err.message || "Action failed");
  }
});

Promise.all([loadSelects(), loadOrders()]).catch((e) => setError(e.message));
