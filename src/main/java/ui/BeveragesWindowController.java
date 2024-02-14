package ui;

import business.service.EmployeeService;
import business.service.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BeveragesWindowController implements Initializable {
    @FXML
    private ComboBox<String> milkComboBox;

    @FXML ComboBox<String> drinkSizeComboBox;

    @FXML
    private Label employeeId;
    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML
    private ToggleGroup choices;

    @FXML
    private Label drinksStatusLabel;

    private String [] milk = {"None", "Non-fat", "1%", "2%", "Whole-Milk", "Oat-Milk", "Almond-Milk"};



    @FXML
    public void onAddOrderClicked(ActionEvent event) {
        Optional<ToggleButton> selectedToggleOptional = Optional.ofNullable((ToggleButton) choices.getSelectedToggle());
        Optional<Integer> quantityOptional = Optional.ofNullable(quantityComboBox.getSelectionModel().getSelectedItem());
        Optional<String> milkTypeOptional = Optional.ofNullable(milkComboBox.getSelectionModel().getSelectedItem());
        Optional<String> sizeOptional = Optional.ofNullable(drinkSizeComboBox.getSelectionModel().getSelectedItem());

        try {
            ToggleButton selectedToggle = selectedToggleOptional.orElseThrow(() -> new NullPointerException("Please select a drink"));
            Integer quantity = quantityOptional.orElseThrow(() -> new NullPointerException("Please select quantity"));
            String milkType = milkTypeOptional.orElseThrow(() -> new NullPointerException("Please select milk type"));
            String size = sizeOptional.orElseThrow(() -> new NullPointerException("Please select size"));

            String drinkType = selectedToggle.getText();
            OrderService.addBeverageOrderItem(quantity, drinkType, size, milkType);
            drinksStatusLabel.setText("Drink successfully added to order");

        }catch(NullPointerException e){
            drinksStatusLabel.setText(e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        milkComboBox.getItems().removeAll(milkComboBox.getItems());
        milkComboBox.getItems().addAll(milk);
        drinkSizeComboBox.getItems().removeAll((drinkSizeComboBox.getItems()));
        drinkSizeComboBox.getItems().addAll("LARGE","MEDIUM","SMALL");
        quantityComboBox.getItems().removeAll((quantityComboBox.getItems()));
        quantityComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
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
    protected void onViewOrderButtonClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("OrderView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
