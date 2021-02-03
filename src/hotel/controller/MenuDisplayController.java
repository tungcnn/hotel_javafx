package hotel.controller;

import static hotel.controller.HotelManager.rooms;
import hotel.entities.Customer;
import hotel.entities.Room;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuDisplayController implements Initializable {

    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> room;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, Integer> id;

    @FXML
    private TableColumn<Customer, String> date;
    @FXML
    private TextField searchField;

    ObservableList<Customer> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Customer, LocalDate> checkin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Room room : rooms) {
            for (Customer customer : room.customers) {
                list.add(customer);
            }
        }
        System.out.println();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        checkin.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        table.setItems(list);
    }

    @FXML
    private void backMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hotel/view/Menu.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) table.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void search(MouseEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Text text;
        try {
            Customer c = find(Integer.parseInt(searchField.getText()));
            if (c == null) {
                text = new Text("No customer with such ID");
                text.setWrappingWidth(400);
                a.getDialogPane().setContent(text);
                a.show();
            } else {
                Stage stage = (Stage) table.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/hotel/view/Detail.fxml"));
                stage.setUserData(c);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (NumberFormatException e) {
            text = new Text("Invalid Input");
            text.setWrappingWidth(400);
            a.getDialogPane().setContent(text);
            a.show();
        }
    }

    Customer find(int id) {
        for (Room room : rooms) {
            for (Customer customer : room.customers) {
                if (customer.getId() == id) {
                    return customer;
                }
            }
        }
        return null;
    }
}
