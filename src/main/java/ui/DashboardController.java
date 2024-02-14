package ui;

import business.service.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label employeeId;


    @FXML
    protected void switchToFoodWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Food.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void switchToBeveragesWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BeveragesWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onLogOutClicked(ActionEvent event) throws IOException{
        EmployeeService.logOut();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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

    @FXML
    protected void onViewPastOrdersButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PastOrders.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeId.setText(String.valueOf(EmployeeService.loggedInEmployee.getEmployeeID()));
    }
}
