package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    public Label headerToko;
    public GridPane shopGrid;
    public GridPane activeDeck;

    public void ToHome(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController.SwitchToHome(actionEvent);
    }

    public void ToEnemyField(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController.SwitchToEnemyField(actionEvent);
    }

    public void ToNext(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController.SwitchPlayer(actionEvent, false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.shopBackButton.setOnMouseClicked(e -> );
    }

}