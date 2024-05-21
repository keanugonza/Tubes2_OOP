package com.TiyangAlit.GUI;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML

    private Circle myCircle;
    private double x;
    private double y;

    public void down(javafx.event.ActionEvent actionEvent){
        myCircle.setCenterY(y+=30);
    }

    public void left(javafx.event.ActionEvent actionEvent){
        myCircle.setCenterX(x-=30);
    }

    public void right(javafx.event.ActionEvent actionEvent){
        myCircle.setCenterX(x+=30);
    }

    public void up(javafx.event.ActionEvent actionEvent) {
        myCircle.setCenterY(y-=30);
    }
}
