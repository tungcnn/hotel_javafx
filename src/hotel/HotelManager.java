/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import hotel.entities.Customer;
import hotel.entities.Room;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.fxml.*;

/**
 *
 * @author SonTung
 */
public class HotelManager extends Application {

    static ArrayList<Room> rooms = new ArrayList<>();

    static {
        rooms.add(new Room(1, "Queen Room", 1));
        rooms.add(new Room(2, "King Room", 2));
        rooms.add(new Room(3, "Junior Suite", 5));
        rooms.add(new Room(4, "Master Suite", 10));
        rooms.add(new Room(5, "President Room", 20));
    }

    @Override
    public void start(Stage stage) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        File myObj = new File("guests.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String[] str = myReader.nextLine().split(",");
            int index = Integer.parseInt(str[0]);
            String name = str[1];
            String date = str[2];
            int id = Integer.parseInt(str[3]);
            LocalDate startDate = LocalDate.parse(str[4], formatter);
            Room room = rooms.get(index - 1);
            Customer c = new Customer(name, date, id);
            room.customers.add(c);
            c.setRoomType(room.getRoomType());
            room.setStartDate(startDate);
        }
        myReader.close();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Hotel Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
