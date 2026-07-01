import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import service.CommissionService;
import model.ItemType;
import model.EmployeeType;
import model.CustomerType;

/**
 *
 * @author PC.vn
 */
public class CommissionServiceTest {

    private CommissionService comissionService;
    private ItemType itemType;
    private EmployeeType employeeType;
    private CustomerType customerType;

    public CommissionServiceTest() {
        comissionService = new CommissionService();
    }

    @Test
    public void testTC01() {

        Exception exception = assertThrows(Exception.class, () -> {
            comissionService.calculateCommission(null, itemType.STANDARD, customerType.REGULAR, 1000);
        });
        assertEquals("Employee type is required", exception.getMessage());
    }

    @Test
    public void testTC02() {

        Exception exception = assertThrows(Exception.class, () -> {
            comissionService.calculateCommission(employeeType.SALARIED, null, customerType.REGULAR, 1000);
        });
        assertEquals("Item type is required", exception.getMessage());
    }

    @Test
    public void testTC03() {

        Exception exception = assertThrows(Exception.class, () -> {
            comissionService.calculateCommission(employeeType.SALARIED, itemType.STANDARD, null, 1000);
        });
        assertEquals("Customer type is required", exception.getMessage());
    }

    @Test
    public void testTC05() {

        Exception exception = assertThrows(Exception.class, () -> {
            comissionService.calculateCommission(employeeType.SALARIED, itemType.STANDARD, customerType.REGULAR, 0);
        });
        assertEquals("Item price must be greater than 0", exception.getMessage());
    }


    @Test
    public void testTC06() {

        double result = comissionService.calculateCommission(employeeType.NON_SALARIED, itemType.OTHER, customerType.NON_REGULAR, 10000);
        assertEquals(1000.0, result);
    }

    @Test
    public void testTC07() {

        double result = comissionService.calculateCommission(employeeType.NON_SALARIED, itemType.OTHER, customerType.NON_REGULAR, 10001);
        assertEquals(500.05, result);
    }

    @Test
    public void testTC08() {

        double result = comissionService.calculateCommission(employeeType.SALARIED, itemType.STANDARD, customerType.NON_REGULAR, 10000);
        assertEquals(0.0, result);
    }

      @Test
    public void testTC04() {

        double result = comissionService.calculateCommission(employeeType.SALARIED, itemType.BONUS, customerType.REGULAR, 10000);
        assertEquals(0.0, result);
    }
    @Test
    public void testTC09() {

        double result = comissionService.calculateCommission(employeeType.SALARIED, itemType.BONUS, customerType.NON_REGULAR, 1000);
        assertEquals(50.0, result);
    }

    @Test
    public void testTC10() {

        double result = comissionService.calculateCommission(employeeType.SALARIED, itemType.BONUS, customerType.NON_REGULAR, 10000);
        assertEquals(25.0, result);
    }

    @Test
    public void testTC11() {

        double result = comissionService.calculateCommission(employeeType.NON_SALARIED, itemType.BONUS, customerType.NON_REGULAR, 1000);
        assertEquals(100.0, result);
    }

    @Test
    public void testTC12() {

        double result = comissionService.calculateCommission(employeeType.NON_SALARIED, itemType.BONUS, customerType.NON_REGULAR, 10000);
        assertEquals(75.0, result);
    }

    @Test
    public void testTC13() {

        double result = comissionService.calculateCommission(employeeType.SALARIED, itemType.OTHER, customerType.NON_REGULAR, 1000);
        assertEquals(0.0, result);
    }

}
