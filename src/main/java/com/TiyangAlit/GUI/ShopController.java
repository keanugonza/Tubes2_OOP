package com.TiyangAlit.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    public GridPane shopGrid;
    public Button shopBackButton;

    public void ToHome(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController.SwitchToHome(actionEvent);
    }

    public void ToEnemyField(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        SceneController.SwitchToEnemyField(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.shopBackButton.setOnMouseClicked(e -> );
    }

}