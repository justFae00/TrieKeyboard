package TrieTree;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// extends Application implements EventHandler<ActionEvent>

public class AppInitilizer extends Application implements EventHandler<ActionEvent> {

    public static Trie trie = new Trie();
    public static Trie reverseTrie = new Trie();

    //reverse a given string
    public static String reverseString(String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        //read from txt and insert it to the trie
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words using whitespace as delimiter
                for (String word : words) {
                    //System.out.println(word.toLowerCase());
                    trie.insert(trie, word);
                    reverseTrie.insert(reverseTrie, reverseString(word));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        //launch the program
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/Controllers/Main.fxml"))));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }


}