package business.dao;

import business.domain.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDAO {
    public static List<Employee> employees = new ArrayList<>();
//            new Employee("John", "Smith", 1234, "1234"),
//            new Employee("Mary", "Stewart",56789, "5678")
//    ));
    static {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String employee = br.readLine();
            while(employee !=null){
                String [] emps = employee.split(",");
                Employee e = new Employee(emps[0],emps[1],Integer.parseInt(emps[2]),emps[3]);
                employees.add(e);
                employee = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    static{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"))){
            for(Employee e: employees){
                bw.write(String.format("%s,%s,%d,%s\n", e.getFirstName(),e.getLastName(),e.getEmployeeID(),e.getPin()));
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
