package business.service;

import business.dao.EmployeeDAO;
import business.domain.Employee;

import java.util.Optional;

public class EmployeeService {

    public static Employee loggedInEmployee;
    public static boolean logIn(Integer id, String pin){
        if(id == null || pin.length() !=4)
            throw new IllegalArgumentException("Invalid id or PIN");
        Optional<Employee> employeeOptional = EmployeeDAO.employees.stream()
                .filter(employee -> employee.getEmployeeID()==id && employee.getPin().equals(pin))
                .findFirst();
        loggedInEmployee = employeeOptional.orElseThrow(() -> new RuntimeException("Incorrect Employee ID or PIN"));
        return true;
    }

    public static void logOut(){
        loggedInEmployee = null;
    }
}
