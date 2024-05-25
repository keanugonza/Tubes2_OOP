package com.TiyangAlit.GUI;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShuffleController implements Initializable{

    public GridPane shufflePopup;
    public ImageView reshuffle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/loop.png")));
        reshuffle.setImage(loop);
    }
}
