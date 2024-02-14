package ui;

import business.service.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField pinTextField;
    @FXML
    private Label loginStatusLabel;

    @FXML
    protected void onLoginButtonClick(ActionEvent e) {

        try {
            Integer id = Integer.parseInt(idTextField.getText());
            String pin = pinTextField.getText();
            EmployeeService.logIn(id,pin);
            switchToDashboard(e);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Credentials");
            alert.setContentText(ex.getMessage());
            alert.show();
        }catch (RuntimeException rex) {
           loginStatusLabel.setText(rex.getMessage());
        }
    }

    @FXML
    protected void onExitClicked(ActionEvent event) {
        System.exit(0);
    }

    protected void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}