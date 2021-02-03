package hotel.controller;

import static hotel.controller.HotelManager.rooms;
import hotel.entities.Customer;
import hotel.entities.Room;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuDeleteController implements Initializable {

    @FXML
    private ComboBox<String> room;
    @FXML
    private DatePicker date;
    @FXML
    private Label fee;
    @FXML
    private Label ciDate;
    @FXML
    private Label coDate;

    @FXML
    private void backMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hotel/view/Menu.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) room.getScene().getWindow();
        stage.setScene(scene);
    }
    private void rewriteFile() throws IOException {
        String str = "";
        for (Room room1 : rooms) {
            for (Customer c : room1.customers) {
                str += String.valueOf(room1.getRoomNumber()) + "," + c.getName() + "," + c.getDateOfBirth() + "," + String.valueOf(c.getId()) + "\n";
            }
        }
        FileWriter fw = new FileWriter("guests.txt");
        fw.write(str);
        fw.close();
    }
    @FXML
    private void calculateFee(MouseEvent event) throws IOException {
        int roomNum = room.getSelectionModel().getSelectedIndex() + 1;
        Room room = getRoom(roomNum);
        room.customers.clear();
        room.setEndDate(date.getValue());
        fee.setText(String.valueOf(room.calculateFee()) + ".000.000 VND");
        rewriteFile();
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
        room.setItems(list);
    }

    @FXML
    private void showCIDate(ActionEvent event) {
        int roomNum = room.getSelectionModel().getSelectedIndex() + 1;
        Room room = getRoom(roomNum);
        ciDate.setText(String.valueOf(room.getStartDate()));
    }

    @FXML
    private void showCODate(ActionEvent event) {
        coDate.setText(String.valueOf(date.getValue()));
    }
}
