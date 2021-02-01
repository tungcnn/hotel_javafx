package hotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void toMenuAdd(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuAdd.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void toMenuDisplay(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuDisplay.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void toMenuDelete(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuDelete.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.setScene(scene);
    }
}