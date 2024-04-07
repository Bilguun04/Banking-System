package bankingsystem.core.controller;

import bankingsystem.core.humanresources.*;
import bankingsystem.core.intangableresources.*;
import bankingsystem.core.tangableresources.*;
import bankingsystem.core.application.*;

import java.util.List;

public class EmployeeController {

    private static Branch branch = BankApplication.getBranch();

    public static boolean addEmployeeToBranch(Person person, int employeeID, Division division) {
        Employee employee = new Employee(person, employeeID, division);
        boolean success = branch.addEmployee(employee);
        return success;
    }

    public static boolean updateEmployee(int employeeId, String name, String address, String phoneNumber) {
        Employee employee = branch.getEmployee(employeeId);
        if (employee == null){
            return false;
        }

        employee.getPerson().setName(name);
        employee.getPerson().setAddress(address);
        employee.getPerson().setPhoneNumber(phoneNumber);

        List<Employee> employees = branch.getEmployees();
        for (Employee e : employees) {
            if (e.getEmployeeID() == employeeId) {
                e.getPerson().setName(name);
                e.getPerson().setAddress(address);
                e.getPerson().setPhoneNumber(phoneNumber);
                return true;
            }
        }
        return false;
    }

    public static void deleteEmployee(int employeeId) {
        Employee employee = branch.getEmployee(employeeId);
        employee.delete();
    }

    public static boolean removeEmployeeFromBranch(int employeeId) {
        Employee employee = branch.getEmployee(employeeId);
        boolean success = branch.removeEmployee(employee);
        return success;
    }
}