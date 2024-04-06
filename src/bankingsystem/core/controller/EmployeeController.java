package bankingsystem.core.controller;

import bankingsystem.core.humanresources.*;
import bankingsystem.core.intangableresources.*;
import bankingsystem.core.tangableresources.*;
import bankingsystem.core.application.*;

import java.util.List;

public class EmployeeController {

    private static Branch branch = BranchApplication.getBranch();

    public static boolean addEmployeeToBranch(Person person, Division division) {
        Employee employee = new Employee(person, division);
        boolean success = branch.addEmployee(employee);
        return success;
    }

    public static boolean updateEmployee(String employeeId, String name, String address, String phoneNumber) {
        Employee employee = branch.getEmployee(Integer.parseInt(employeeId));
        if (employee == null){
            return false;
        }

        employee.getPerson().setName(name);
        employee.getPerson().setAddress(address);
        employee.getPerson().setPhoneNumber(phoneNumber);

        List<Employee> employees = branch.getEmployees();
        for (Employee e : employees) {
            if (e.getPerson().equals(employee.getPerson())) {
                e.getPerson().setName(name);
                e.getPerson().setAddress(address);
                e.getPerson().setPhoneNumber(phoneNumber);
            }
        }
        return success;
    }

    public static boolean deleteEmployee(String employeeId) {
        return true;
    }

    public static boolean removeEmployeeFromBranch(String employeeId, String branchId) {
        return true;
    }
}