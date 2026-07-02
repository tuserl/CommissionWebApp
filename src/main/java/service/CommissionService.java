package service;

import model.CustomerType;
import model.EmployeeType;
import model.ItemType;

public class CommissionService {

    public double calculateCommission(EmployeeType employeeType,
            ItemType itemType,
            CustomerType customerType,
            double itemPrice) {

        if (employeeType == null) {
            throw new IllegalArgumentException("Employee type is required");
        }

        if (itemType == null) {
            throw new IllegalArgumentException("Item type is required");
        }

        if (customerType == null) {
            throw new IllegalArgumentException("Customer type is required");
        }

        if (itemPrice <= 0) {
            throw new IllegalArgumentException("Item price must be greater than 0");
        }
      

        if (itemType == ItemType.STANDARD || customerType == CustomerType.REGULAR) {
            return 0;
        }

        if (employeeType == EmployeeType.SALARIED && itemType == ItemType.BONUS) {
            if (itemPrice > 1000) {
                return 25;
            }
            return itemPrice * 0.05;
        }

        if (employeeType == EmployeeType.NON_SALARIED && itemType == ItemType.BONUS) {
            if (itemPrice > 1000) {
                return 75;
            }
            return itemPrice * 0.10;
        }

        if (employeeType == EmployeeType.NON_SALARIED) {
            if (itemPrice > 10000) {
                return itemPrice * 0.05;
            }
            return itemPrice * 0.10;
        }

        return 0;
    }
}
