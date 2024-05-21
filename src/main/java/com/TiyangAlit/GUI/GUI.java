//package com.TiyangAlit.GUI;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Objects;
//
//
//public class GUI  extends Application{
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        try{
//            InputStream stream  = new FileInputStream("src/main/java/com/TiyangAlit/Resources/StardewValley.png");
//            Image image = new Image(stream);
//            stage.getIcons().add(image);
//            stage.setTitle("Stardew Valley");
//
//            InputStream fxmlUrl = new FileInputStream("src/main/java/com/TiyangAlit/GUI/GUI.fxml");
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            Parent root = fxmlLoader.load(fxmlUrl);
//
//            Scene scene = new Scene(root);
//            scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/style.css")).toExternalForm());
//
//            stage.setScene(scene);
//            stage.show();
//        }catch (Exception e){
//            throw new Exception("ERROR");
//        }
//
//    }
//}
