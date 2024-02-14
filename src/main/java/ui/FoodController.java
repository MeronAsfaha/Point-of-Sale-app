package ui;

import business.domain.Employee;
import business.service.EmployeeService;
import business.service.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FoodController implements Initializable {
    @FXML
    private Label employeeId;

    @FXML
    private ToggleGroup selection;

    @FXML
    private ComboBox<Integer> foodQuantity;
    @FXML
    private Label foodStatusLabel;

    @FXML
    protected void onPreviousButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAddOrderClicked(ActionEvent event){
        Optional<ToggleButton> selectedItemOptional = Optional.ofNullable((ToggleButton) selection.getSelectedToggle());
        Optional<Integer> quantityOptional = Optional.ofNullable(foodQuantity.getSelectionModel().getSelectedItem());
        try {
            ToggleButton selectedItem = selectedItemOptional.orElseThrow(() -> new NullPointerException("Please select a food item"));
            Integer quantity = quantityOptional.orElseThrow(() -> new NullPointerException("Please select quantity"));

            String foodType = selectedItem.getText();
            OrderService.addFoodOrderItem(quantity, foodType);
            foodStatusLabel.setText("Item added to order successfully");
        }catch(NullPointerException e){
            foodStatusLabel.setText(e.getMessage());
        }
    }

    @FXML
    protected void onViewOrderButtonClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("OrderView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeId.setText(String.valueOf(EmployeeService.loggedInEmployee.getEmployeeID()));
        foodQuantity.getItems().removeAll(foodQuantity.getItems());
        foodQuantity.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
    }
}
