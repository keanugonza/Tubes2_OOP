package com.TiyangAlit.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class SceneController {
    static Parent root;
    static Stage stage;
    static Scene scene;

    public static void SwitchToShop(javafx.scene.input.MouseEvent event) throws IOException {
        InputStream shopPageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Shop.fxml");
        FXMLLoader shopPageLoader = new FXMLLoader();
        root = shopPageLoader.load(shopPageFxml);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(MainGUI.CSSUrl);
        stage.setScene(scene);
        stage.show();
    }

    public static void SwitchToHome(javafx.scene.input.MouseEvent event) throws IOException {
        InputStream homePageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Home.fxml");
        FXMLLoader homePageLoader = new FXMLLoader();
        root = homePageLoader.load(homePageFxml);
        HomeController controlerHome = homePageLoader.getController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(MainGUI.CSSUrl);
        stage.setScene(scene);
        stage.show();
        GridController.FillLadang(controlerHome.cardGrid, controlerHome.activeDeck, MainGUI.ladang, MainGUI.deck);
        GridController.FillDeck(controlerHome.cardGrid, controlerHome.activeDeck, MainGUI.ladang, MainGUI.deck);
    }

    public static void SwitchToEnemyField(javafx.scene.input.MouseEvent event) throws IOException {
        InputStream enemyFieldFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/EnemyField.fxml");
        FXMLLoader enemyFieldLoader = new FXMLLoader();
        root = enemyFieldLoader.load(enemyFieldFxml);
        EnemyFieldController controllerEnemyField = enemyFieldLoader.getController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(MainGUI.CSSUrl);
        stage.setScene(scene);
        stage.show();
        GridController.FillLadang(controllerEnemyField.enemyGrid, controllerEnemyField.activeDeck, MainGUI.ladang, MainGUI.deck);
        GridController.FillDeck(controllerEnemyField.enemyGrid, controllerEnemyField.activeDeck, MainGUI.ladang, MainGUI.deck);
    }
}
