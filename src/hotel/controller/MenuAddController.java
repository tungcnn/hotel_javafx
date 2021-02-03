package hotel.controller;

import static hotel.controller.HotelManager.rooms;
import hotel.entities.Customer;
import hotel.entities.Room;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuAddController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox roomNumber;
    @FXML
    private TextField idField;
    @FXML
    private TextField dateField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button submitBtn;

    public MenuAddController() {

    }

    @FXML
    private void backMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hotel/view/Menu.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void handleSubmit(MouseEvent event) throws IOException {
        Alert a = new Alert(AlertType.INFORMATION);
        Customer c = createCustomer();
        
        int roomNum = roomNumber.getSelectionModel().getSelectedIndex() + 1;
        Room room = getRoom(roomNum);
        
        room.setStartDate(datePicker.getValue());
        room.customers.add(c);
        c.setRoomType(room.getRoomType());
        c.setStartDate(datePicker.getValue());
        
        saveToFile(room, c);
        
        Text text = new Text("Đã thêm " + room.customers.get(room.customers.size() - 1).toString() + "\nVào " + room.toString() + "\nNgày " + room.getStartDate());
        text.setWrappingWidth(400);
        a.getDialogPane().setContent(text);
        a.show();
        
        backMenu(event);
    }
    private void saveToFile(Room room, Customer c) throws IOException {
        FileWriter myWriter = new FileWriter("guests.txt", true);
        BufferedWriter bw = new BufferedWriter(myWriter);
        bw.write(room.getRoomNumber()+","+c.getName()+","+c.getDateOfBirth()+","+String.valueOf(c.getId()) + "," + room.getStartDate() + "\n");
        bw.close();
    }
    private Customer createCustomer() {
        String name = nameField.getText();
        String date = dateField.getText();
        int id = Integer.parseInt(idField.getText());
        return new Customer(name, date, id);
    }

    private Room getRoom(int choice) {
        Room room2 = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == choice) {
                room2 = room;
            }
        }
        return room2;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Queen Room - 1.000.000 VND/Night",
                "King Room - 2.000.000 VND/Night",
                "Junior Suite - 5.000.000 VND/Night",
                "Master Suite - 10.000.000 VND/Night",
                "President Suite - 20.000.000 VND/Night");
        roomNumber.setItems(list);
    }
}
