package ui;

import business.dao.OrderDAO;
import business.domain.BeverageOrderItem;
import business.domain.FoodOrderItem;
import business.domain.OrderItem;
import business.domain.Product;
import business.service.EmployeeService;
import business.service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class OrderViewController implements Initializable {

    @FXML
    private ListView<OrderItem> orderList;

    @FXML
    private Label employeeId;

    @FXML
    private Label totalLabel;

    @FXML
    private Label statusLabel;
    ObservableList<OrderItem> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (OrderService.currentOrder != null) {

            observableList = FXCollections.observableList(OrderService.currentOrder.getOrderItems().values().stream().toList());
            orderList.setItems(observableList);
            totalLabel.setText(String.format("$%.2f", OrderService.currentOrder.totalPrice()));
        }

        employeeId.setText(String.valueOf(EmployeeService.loggedInEmployee.getEmployeeID()));
    }

    @FXML
    protected void onPreviousButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onRemoveButtonClicked(ActionEvent event) {
        OrderItem item = orderList.getSelectionModel().getSelectedItem();
        if (observableList == null) {
            statusLabel.setText("The order is empty.");
            return;
        }
        if (item == null) {
            statusLabel.setText("Please select an item to be deleted.");
            return;
        }
        String productName;
        if (item instanceof FoodOrderItem) {
            productName = ((FoodOrderItem) item).getFood().getName();
        } else {
            BeverageOrderItem beverageOrderItem = ((BeverageOrderItem) item);
            productName = beverageOrderItem.getDrink().getName() + beverageOrderItem.getSize() + beverageOrderItem.getMilkType();
        }
        OrderService.currentOrder.removeOrderItem(productName);
        orderList.setItems(FXCollections.observableList(OrderService.currentOrder.getOrderItems().values().stream().toList()));
        totalLabel.setText(String.format("$%.2f", OrderService.currentOrder.totalPrice()));
    }

    @FXML
    protected void onCheckoutButtonClicked(ActionEvent event){
       try{
           OrderService.checkout();
           orderList.setItems(FXCollections.observableList(Arrays.asList()));
           statusLabel.setText("Successful Checkout");
           totalLabel.setText("");
       }catch (RuntimeException e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setContentText(e.getMessage());
           alert.show();
       }


    }
}
