package business.dao;

import business.domain.Employee;
import business.domain.Order;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO {

    public static void checkOutToFile(Order order) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("orders.txt", true))) {
            String orderId = order.getOrderId().toString();
            String employeeString = String.format("%d,%s", order.getEmployee().getEmployeeID(), order.getEmployee().getLastName());
            String orderDate = order.getOrderDate().toString();
            double price = order.totalPrice();
            String orderItems = String.join(",", order.getOrderItems().keySet());
            bw.write(String.format("%s|%s|%s|%.2f|%s\n", orderId, employeeString, orderDate, price, orderItems));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Order> readFromFile() {
        List<Order> orderString = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("orders.txt"))) {
            String order = br.readLine();
            while (order != null) {

                String[] string = order.split("\\|");
                Order orderStringInner = new Order();
                orderStringInner.setOrderId(Long.valueOf(string[0]));
                String[] employee = string[1].split(",");
//                price,employeelastName,employeeId,orderDetails
                orderStringInner.setEmployee(new Employee(null,employee[1],Integer.parseInt(employee[0]),null));
                orderStringInner.setOrderDate(LocalDate.parse(string[2]));
                orderStringInner.setTotalPrice(Double.parseDouble(string[3]));
                orderString.add(orderStringInner);
                order = br.readLine();
            }
            return orderString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
