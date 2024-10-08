package com.TiyangAlit.GUI;

import com.TiyangAlit.Game.Simpan.Simpan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    public Text player1Text;
    public Text player2Text;
    public Text player1Coin;
    public Text player2Coin;
    public Text turnNumber;
    public Button nextButton;
    public Button ladangkuButton;
    public Button ladangLawanButton;
    public Button tokoButton;
    public Button saveStateButton;
    public Button loadStateButton;
    public GridPane cardGrid;
    public GridPane activeDeck;
    public Text deckNumber;

    public void ToShop(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController newSceneController = new SceneController();
        newSceneController.SwitchToShop(actionEvent);
    }

    public void ToEnemyField(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController newSceneController = new SceneController();
        newSceneController.SwitchToEnemyField(actionEvent);
    }

    public void ToNext(javafx.scene.input.MouseEvent actionEvent) throws Exception {
        SceneController newSceneController = new SceneController();
        newSceneController.SwitchPlayer(actionEvent, false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveStateButton.setOnMouseClicked(e -> {
            Simpan.simpan();
        });

        loadStateButton.setOnMouseClicked(e -> {
            SceneController newSceneController = new SceneController();
            newSceneController.SwitchPlayer(e, true);
        });
    }
}