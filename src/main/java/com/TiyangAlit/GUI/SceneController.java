package com.TiyangAlit.GUI;

import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Player.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        ShopController shopController = shopPageLoader.getController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(MainGUI.CSSUrl);
        stage.setScene(scene);
        stage.show();
        GridController.FillToko(shopController.shopGrid);
        GridController.FillDeck(null, shopController.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
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
        GridController.FillLadang(controlerHome.cardGrid, controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
        GridController.FillDeck(controlerHome.cardGrid, controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
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
        GridController.FillLadang(controllerEnemyField.enemyGrid, controllerEnemyField.activeDeck, MainGUI.ladangEnemy, MainGUI.deckPlayer);
        GridController.FillDeck(controllerEnemyField.enemyGrid, controllerEnemyField.activeDeck, MainGUI.ladangEnemy, MainGUI.deckPlayer);
    }

    public static void SwitchPlayer(javafx.scene.input.MouseEvent event) throws IOException{
        InputStream homePageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Home.fxml");
        FXMLLoader homePageLoader = new FXMLLoader();
        root = homePageLoader.load(homePageFxml);
        HomeController controlerHome = homePageLoader.getController();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(MainGUI.CSSUrl);
        stage.setScene(scene);
        stage.show();
        System.out.println("cur :" + MainGUI.currentPlayer);
        System.out.println("enemy: " + MainGUI.enemyPlayer);
        Game.NEXT();
        MainGUI.currentPlayer = Game.getCurrentPlayer();
        MainGUI.enemyPlayer = Game.getEnemyPlayer();
        MainGUI.ladangPlayer = MainGUI.currentPlayer.getLadang();
        MainGUI.deckPlayer = MainGUI.currentPlayer.getDeckAktif();
        MainGUI.ladangEnemy = MainGUI.enemyPlayer.getLadang();
        MainGUI.deckEnemy = MainGUI.enemyPlayer.getDeckAktif();
        GridController.FillLadang(controlerHome.cardGrid, controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
        GridController.FillDeck(controlerHome.cardGrid, controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
    }
}
