package Controllers;

import TrieTree.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static TrieTree.AppInitilizer.trie;

public class AutoCompleteController {

    @FXML
    private Button backButton;

    @FXML
    private Button processButton;

    @FXML
    private Button txt1;

    @FXML
    private Button txt2;

    @FXML
    private Button txt3;

    @FXML
    private Button txt4;

    @FXML
    private Button txt5;

    @FXML
    private AnchorPane hidingBox;

    @FXML
    private TextField inputBox;

    @FXML
    void backButtonOA(ActionEvent event) throws IOException {
        switchToMain(event);
    }

    @FXML
    void processButtonOA(ActionEvent event) {
        hidingBox.setVisible(true);
        processButton.setVisible(false);
        String[] result = trie.autocomplete(trie, inputBox.getText());
        if (result[0] != null){
            txt1.setVisible(true);
            txt1.setText(result[0]);
        }
        if (result[1] != null){
            txt2.setVisible(true);
            txt2.setText(result[1]);
        }
        if (result[2] != null){
            txt3.setVisible(true);
            txt3.setText(result[2]);
        }
        if (result[3] != null){
            txt4.setVisible(true);
            txt4.setText(result[3]);
        }
        if (result[4] != null){
            txt5.setVisible(true);
            txt5.setText(result[4]);
        }

    }


    @FXML
    void txt1OA(ActionEvent event) {
        Trie.incFrequency(txt1.getText());
        System.out.println(txt1.getText() + " freq: " + trie.findFrequency(txt1.getText()));

    }

    @FXML
    void txt2OA(ActionEvent event) {
        Trie.incFrequency(txt2.getText());
        System.out.println(txt2.getText() + " freq: " + trie.findFrequency(txt1.getText()));

    }

    @FXML
    void txt3OA(ActionEvent event) {
        Trie.incFrequency(txt3.getText());
        System.out.println(txt3.getText() + " freq: " + trie.findFrequency(txt1.getText()));

    }

    @FXML
    void txt4OA(ActionEvent event) {
        Trie.incFrequency(txt4.getText());
        System.out.println(txt4.getText() + " freq: " + trie.findFrequency(txt1.getText()));

    }

    @FXML
    void txt5OA(ActionEvent event) {
       Trie.incFrequency(txt5.getText());
        System.out.println(txt5.getText() + " freq: " + trie.findFrequency(txt1.getText()));

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMain(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Controllers/Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
