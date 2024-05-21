package com.TiyangAlit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
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
            InputStream fxmlUrl = new FileInputStream("src/main/java/com/TiyangAlit/GUI.fxml");
            InputStream stream  = new FileInputStream("src/main/java/com/TiyangAlit/StardewValley.png");
            Image image = new Image(stream);
            stage.getIcons().add(image);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(fxmlUrl);
            stage.setTitle("Stardew Valley");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            throw new Exception("ERROR");
        }

    }
}
