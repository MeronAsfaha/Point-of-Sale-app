package business.service;

import business.dao.OrderDAO;
import business.dao.ProductDAO;
import business.domain.*;

import java.util.Optional;

public class OrderService {

    public static Order currentOrder = null;

    public static void addFoodOrderItem(int quantity, String productName) {
        Product p = ProductDAO.products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid Product Name"));
        OrderItem foodOrderItem = new FoodOrderItem(quantity, p);
        if (currentOrder == null) {
            currentOrder = new Order(EmployeeService.loggedInEmployee, productName, foodOrderItem);
        } else if (currentOrder.getOrderItems().containsKey(productName)) {
            OrderItem item = currentOrder.getOrderItems().get(productName);
            item.setQuantity(item.getQuantity() + quantity);
            currentOrder.getOrderItems().put(productName, item);

        } else {
            currentOrder.getOrderItems().put(productName, foodOrderItem);
        }

    }

    public static void addBeverageOrderItem(int quantity, String productName, String size, String milkType) {
        Product p = ProductDAO.products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid Product Name"));

        OrderItem beverageOrderItem = new BeverageOrderItem(quantity, BeverageSize.valueOf(size), p, milkType);
        String key = productName+size+milkType;
       if(currentOrder == null){
           currentOrder = new Order(EmployeeService.loggedInEmployee,key,beverageOrderItem);
       } else if(currentOrder.getOrderItems().containsKey(key)){
           OrderItem item = currentOrder.getOrderItems().get(key);
           item.setQuantity(item.getQuantity()+quantity);
           currentOrder.getOrderItems().put(key,item);
       } else {
           currentOrder.getOrderItems().put(key,beverageOrderItem);
       }
    }

    public static void checkout() {
        OrderDAO.checkOutToFile(currentOrder);
        currentOrder = null;
    }


}
