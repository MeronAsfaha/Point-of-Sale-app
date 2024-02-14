package ui;

import business.dao.OrderDAO;
import business.domain.Order;
import javafx.beans.Observable;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PastOrdersController implements Initializable {

    @FXML
    private TableView<Order> tableView;
//    @FXML
//    private TableColumn<Order,String> idColumn;
//    @FXML
//    private TableColumn<Order,String> employeeIdColumn;
//    @FXML
//    private TableColumn<Order,String> lastNameColumn;
//    @FXML
//    private TableColumn<Order,String > orderDateColumn;
//    @FXML
//    private TableColumn<Order,String> priceColumn;
//    @FXML
//    private TableColumn<Order,String> detailsColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Order> orderObservableList = FXCollections.observableList(Objects.requireNonNull(OrderDAO.readFromFile()));

        tableView.setItems(orderObservableList);
        TableColumn orderIdColumn = new TableColumn("Order ID");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderIdColumn.setPrefWidth(200);
        TableColumn employeeIdColumn = new TableColumn("Employee Info");
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        employeeIdColumn.setPrefWidth(300);
        TableColumn dateColumn = new TableColumn("Order Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        dateColumn.setPrefWidth(200);
        TableColumn priceColumn = new TableColumn("Total Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        priceColumn.setPrefWidth(100);

        tableView.getColumns().addAll(orderIdColumn,employeeIdColumn,dateColumn,priceColumn);


//        orderIDColumn.setCellValueFactory(new PropertyValueFactory<Order, Long>("orderId"));
//        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("employee.employeeID"));
//        Active.setCellValueFactory(new PropertyValueFactory<User, String>("active"));

    }

    @FXML
    protected void onPreviousButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
