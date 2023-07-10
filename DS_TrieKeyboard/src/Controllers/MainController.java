package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


    @FXML
    private Button correctionButton;

    @FXML
    private Button spellCheckButton;


    @FXML
    void correctionButtonOA(ActionEvent event) throws IOException {
        switchToCorrection(event);
    }

    @FXML
    void spellCheckButtonOA(ActionEvent event) throws IOException {
        switchToSpellCheck(event);
    }


    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToSpellCheck(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Controllers/AutoComplete.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCorrection(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Controllers/Correction.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
