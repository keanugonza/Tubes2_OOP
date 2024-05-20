package com.TiyangAlit;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class GUI  extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Group root = new Group();
            Scene scene = new Scene(root, Color.GRAY);
            InputStream stream  = new FileInputStream("src/StardewValley.png");
            Image image = new Image(stream);
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.setTitle("TyangAlit");
            stage.show();
        }catch (Exception e){
            throw new Exception("ERROR");
        }

    }
}
