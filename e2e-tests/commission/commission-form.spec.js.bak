const { test, expect } = require("@playwright/test");
const manager = require("playwright-visible-mouse");
const { expectRequiredSelectIfPresent } = require("playwright-visible-mouse/expectHelpers");

const EmployeeType = Object.freeze({ SALARIED: "SALARIED", NON_SALARIED: "NON_SALARIED" });
const ItemType = Object.freeze({ STANDARD: "STANDARD", BONUS: "BONUS", OTHER: "OTHER" });
const CustomerType = Object.freeze({ REGULAR: "REGULAR", NON_REGULAR: "NON_REGULAR" });

test.describe.configure({ mode: "parallel" });
test.beforeEach(async () => { manager.setUrl("http://localhost:9999/CommissionWebApp/index.jsp"); });

async function calculateCommission(employeeType, itemType, customerType, itemPrice) {
  const { btn, field, selectOptionOrGetState, text, page, InteractionMode, setInteractionMode } = await manager.launch({ mode: "maximized" });
  //  setInteractionMode(InteractionMode.INSTANT);
  setInteractionMode(InteractionMode.NORMAL);
  expectRequiredSelectIfPresent(await selectOptionOrGetState("employeeType", employeeType));
  expectRequiredSelectIfPresent(await selectOptionOrGetState("itemType", itemType));
  expectRequiredSelectIfPresent(await selectOptionOrGetState("customerType", customerType));
  if (itemPrice != null) await field("Enter item price...").type(itemPrice.toString());
  await btn("Calculate Commission").click();
  if (!(await btn("Calculate Again").exists(1000))) return null;
  const result = parseFloat((await text({ class: "commission-value" }).loc.textContent()).replace(/[$,]/g, ""));
  await page.waitForTimeout(500);
  return result;
}

//================================= TEST CASES =========================================

test("TC01", async () => {
  const result = await calculateCommission(null, ItemType.STANDARD, CustomerType.REGULAR, 1000);
  expect(result).toBe(null);
  // Implementation note: Logic for error message verification would be handled here
});

test("TC02", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, null, CustomerType.REGULAR, 1000);
  expect(result).toBe(null);
});

test("TC03", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.STANDARD, null, 1000);
  expect(result).toBe(null);
});

test("TC04", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.STANDARD, CustomerType.REGULAR, null);
  expect(result).toBe(null);
});

test("TC05", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.STANDARD, CustomerType.REGULAR, 0);
  expect(result).toBe(null);
});

test("TC06", async () => {
  const result = await calculateCommission(EmployeeType.NON_SALARIED, ItemType.OTHER, CustomerType.NON_REGULAR, 10000);
  expect(result).toBe(1000);
});

test("TC07", async () => {
  const result = await calculateCommission(EmployeeType.NON_SALARIED, ItemType.OTHER, CustomerType.NON_REGULAR, 10001);
  expect(result).toBe(500.05);
});

test("TC08", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.BONUS, CustomerType.REGULAR, 10000);
  expect(result).toBe(0);
});

test("TC09", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.BONUS, CustomerType.NON_REGULAR, 1000);
  expect(result).toBe(50.0);
});

test("TC10", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.BONUS, CustomerType.NON_REGULAR, 10000);
  expect(result).toBe(25.0);
});

test("TC11", async () => {
  const result = await calculateCommission(EmployeeType.NON_SALARIED, ItemType.BONUS, CustomerType.NON_REGULAR, 1000);
  expect(result).toBe(100.0);
});

test("TC12", async () => {
  const result = await calculateCommission(EmployeeType.NON_SALARIED, ItemType.BONUS, CustomerType.NON_REGULAR, 10000);
  expect(result).toBe(75.0);
});

test("TC13", async () => {
  const result = await calculateCommission(EmployeeType.SALARIED, ItemType.OTHER, CustomerType.NON_REGULAR, 1000);
  expect(result).toBe(0);
});
