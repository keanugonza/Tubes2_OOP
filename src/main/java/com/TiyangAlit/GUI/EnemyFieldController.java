package com.TiyangAlit.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnemyFieldController implements Initializable {

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
    public Button loadPluginButton;
    public GridPane enemyGrid;
    public GridPane activeDeck;

    public void ToShop(ActionEvent actionEvent) throws IOException {
        SceneController.SwitchToShop(actionEvent);
    }

    public void ToHome(ActionEvent actionEvent) throws IOException {
        SceneController.SwitchToHome(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("Hello World");
    }
}
