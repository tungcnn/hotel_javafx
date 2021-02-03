package hotel.controller;

import static hotel.controller.HotelManager.rooms;
import hotel.entities.Customer;
import hotel.entities.Room;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DetailController implements Initializable{

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField date;

    private void rewriteFile() throws IOException {
        String str = "";
        for (Room room1 : rooms) {
            for (Customer c : room1.customers) {
                str += String.valueOf(room1.getRoomNumber()) + "," + c.getName() + c.getDateOfBirth() + String.valueOf(c.getId()) + "\n";
            }
        }
        FileWriter fw = new FileWriter("guests.txt");
        fw.write(str);
        fw.close();
    }
    
    @FXML
    private void toMenuDisplay(MouseEvent event) throws IOException {
        Stage stage = (Stage) name.getScene().getWindow();
        Customer c = (Customer) stage.getUserData();
        c.setName(name.getText());
        c.setId(Integer.parseInt(id.getText()));
        c.setDateOfBirth(date.getText());
        rewriteFile();
        Parent root = FXMLLoader.load(getClass().getResource("/hotel/view/MenuDisplay.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        Stage stage = (Stage) name.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/hotel/view/MenuDisplay.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
