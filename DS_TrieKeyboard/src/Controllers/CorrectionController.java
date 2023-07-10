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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static TrieTree.AppInitilizer.reverseTrie;
import static TrieTree.AppInitilizer.trie;

public class CorrectionController {

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
    private Text currectMsg;

    @FXML
    private AnchorPane hidingBox;

    @FXML
    private Text incorrectMsg;

    @FXML
    private TextField inputBox;

    @FXML
    void backButtonOA(ActionEvent event) throws IOException {
        switchToMain(event);
    }

    @FXML
    void processButtonOA(ActionEvent event) {
        processButton.setVisible(false);
        if (Trie.spellCheck(trie, inputBox.getText())){
            currectMsg.setVisible(true);
            trie.incFrequency(inputBox.getText());
        }
        else {
            incorrectMsg.setVisible(true);
            hidingBox.setVisible(true);
            txt1.setVisible(true);
            txt2.setVisible(true);
            txt3.setVisible(true);
            txt4.setVisible(true);
            txt5.setVisible(true);
            String[] result = trie.correction(trie, reverseTrie, inputBox.getText());
            txt1.setText(result[0]);
            txt2.setText(result[1]);
            txt3.setText(result[2]);
            txt4.setText(result[3]);
            txt5.setText(result[4]);



        }

    }

    @FXML
    void txt1OA(ActionEvent event) {
        Trie.incFrequency(txt1.getText());
    }

    @FXML
    void txt2OA(ActionEvent event) {
        Trie.incFrequency(txt2.getText());
    }

    @FXML
    void txt3OA(ActionEvent event) {
        Trie.incFrequency(txt3.getText());
    }

    @FXML
    void txt4OA(ActionEvent event) {
        Trie.incFrequency(txt4.getText());
    }

    @FXML
    void txt5OA(ActionEvent event) {
        Trie.incFrequency(txt5.getText());
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
