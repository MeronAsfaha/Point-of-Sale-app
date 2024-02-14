package business.domain;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private Map<String,OrderItem> orderItems;
    private Long orderId;
    private Employee employee;
    private LocalDate orderDate;

    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public Order(Employee employee, String key, OrderItem orderItem) {
        this.employee = employee;
        this.orderDate = LocalDate.now();
        this.orderId = generateID();
        orderItems = new HashMap<>();
        addOrderItem(key,orderItem);
    }

    public Order(){}
    public Map<String,OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(String key,OrderItem orderItem) {
        this.orderItems.put(key,orderItem);
    }

    public void removeOrderItem(String key){
        this.orderItems.remove(key);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void setOrderDate(LocalDate date){
        this.orderDate=date;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    private Long generateID(){
        Random random = new Random();
        return random.nextLong(100000,999999999999L);
    }


    public double totalPrice(){
        return getOrderItems().values().stream().map(OrderItem::computePrice).reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderItems=" + orderItems +
                ", OrderId=" + orderId +
                ", employee=" + employee +
                ", orderDate=" + orderDate +
                '}';
    }
}
