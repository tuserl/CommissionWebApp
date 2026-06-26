const { test, expect } = require("@playwright/test");
const manager = require("playwright-visible-mouse");

test.beforeEach(async () => {
  manager.setUrl("http://localhost:9999/CommissionWebApp/index.jsp");
});

async function calculateCommision(employeeType, itemType, customerType, itemPrice) {
  const { btn, field, selectOption, text } = await manager.launch({ mode: "maximized" });

  await selectOption("Salaried Salesperson", "SALARIED")
  await field("Enter item price...").type("Admin@123");
  await btn("Calculate Commission").click();
  return text;
}


test("TC01", async () => {
  const text = await (calculateCommision());
  expect(await text("Calculate Commission").exists()).toBe(true);
});
