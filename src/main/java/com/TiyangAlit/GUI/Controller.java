package com.TiyangAlit.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    public GridPane cardGrid;
    public GridPane activeDeck;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("Hello World");
    }
}