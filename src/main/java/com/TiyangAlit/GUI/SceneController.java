package com.TiyangAlit.GUI;

import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Kartu;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


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
        GridController.FillDeck(null, shopController.activeDeck, MainGUI.controlerHome.player1Coin, MainGUI.controlerHome.player2Coin, MainGUI.ladangPlayer, MainGUI.deckPlayer);
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
        controlerHome.turnNumber.setText(String.valueOf(Game.getTurnCnt()));
        controlerHome.player1Coin.setText(String.valueOf(Game.getPlayers()[0].getUang()));
        controlerHome.player2Coin.setText(String.valueOf(Game.getPlayers()[1].getUang()));
        GridController.FillLadang(controlerHome.cardGrid, controlerHome.activeDeck, controlerHome.player1Coin, controlerHome.player2Coin, MainGUI.ladangPlayer, MainGUI.deckPlayer);
        GridController.FillDeck(controlerHome.cardGrid, controlerHome.activeDeck, controlerHome.player1Coin, controlerHome.player2Coin, MainGUI.ladangPlayer, MainGUI.deckPlayer);
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
        controllerEnemyField.turnNumber.setText(String.valueOf(Game.getTurnCnt()));
        controllerEnemyField.player1Coin.setText(String.valueOf(Game.getPlayers()[0].getUang()));
        controllerEnemyField.player2Coin.setText(String.valueOf(Game.getPlayers()[1].getUang()));
        GridController.FillLadang(controllerEnemyField.enemyGrid, controllerEnemyField.activeDeck, controllerEnemyField.player1Coin, controllerEnemyField.player2Coin, MainGUI.ladangEnemy, MainGUI.deckPlayer);
        GridController.FillDeck(controllerEnemyField.enemyGrid, controllerEnemyField.activeDeck, controllerEnemyField.player1Coin, controllerEnemyField.player2Coin, MainGUI.ladangEnemy, MainGUI.deckPlayer);
    }

    public static void SwitchPlayer(javafx.scene.input.MouseEvent event, boolean Load){
        try{
            InputStream homePageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Home.fxml");
            FXMLLoader homePageLoader = new FXMLLoader();
            root = homePageLoader.load(homePageFxml);
            HomeController controlerHome = homePageLoader.getController();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(MainGUI.CSSUrl);
            stage.setScene(scene);
            stage.show();

            if(!Load){
                Game.NEXT();
            } else{
                Game.MUAT();
            }

            controlerHome.turnNumber.setText(String.valueOf(Game.getTurnCnt()));
            MainGUI.currentPlayer = Game.getCurrentPlayer();
            MainGUI.enemyPlayer = Game.getEnemyPlayer();
            MainGUI.ladangPlayer = MainGUI.currentPlayer.getLadang();
            MainGUI.deckPlayer = MainGUI.currentPlayer.getDeckAktif();
            MainGUI.ladangEnemy = MainGUI.enemyPlayer.getLadang();
            MainGUI.deckEnemy = MainGUI.enemyPlayer.getDeckAktif();
            GridController.FillLadang(controlerHome.cardGrid, controlerHome.activeDeck, controlerHome.player1Coin, controlerHome.player2Coin, MainGUI.ladangPlayer, MainGUI.deckPlayer);
            GridController.FillDeck(controlerHome.cardGrid, controlerHome.activeDeck, controlerHome.player1Coin, controlerHome.player2Coin, MainGUI.ladangPlayer, MainGUI.deckPlayer);
            controlerHome.player1Coin.setText(String.valueOf(Game.getPlayers()[0].getUang()));
            controlerHome.player2Coin.setText(String.valueOf(Game.getPlayers()[1].getUang()));
            if(!MainGUI.currentPlayer.getDeckAktif().isFull()){
                SceneController.ShufflePopUp(scene.getWindow(), controlerHome.cardGrid, controlerHome.activeDeck);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void Popup(MouseEvent event, Window owner, String message) throws IOException {

        Stage onTop = new Stage(StageStyle.TRANSPARENT);
        onTop.setY(owner.getY() + 400);
        onTop.setX(owner.getX() + 200);
        onTop.initOwner(owner);
        onTop.initModality(Modality.APPLICATION_MODAL);
        owner.getScene().getRoot().setEffect(new GaussianBlur());

        onTop.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                onTop.close();
                owner.getScene().getRoot().setEffect(null);
            }
        });

        VBox newPane = new VBox(5);
        newPane.setPrefHeight(140);
        newPane.setPrefWidth(500);
        newPane.setStyle("-fx-border-color: black; -fx-border-radius: 2;");
        newPane.setPadding(new Insets(5,5,5,5));

        Label label = new Label(message);
        label.setFont(new Font("Continuum Medium", 20));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.valueOf("CENTER"));
        label.setTextFill(Paint.valueOf("red"));
        newPane.setAlignment(Pos.CENTER);
        newPane.getChildren().add(label);

        Scene newScene = new Scene(newPane);
        onTop.setScene(newScene);
        onTop.setResizable(false);
        onTop.showAndWait();
    }

    public static void ShufflePopUp( Window owner, GridPane GridLadang, GridPane GridDeck){
        try{
            InputStream shuffleFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Shuffle.fxml");
            FXMLLoader shuffleLoader = new FXMLLoader();
            root = shuffleLoader.load(shuffleFxml);
            ShuffleController shuffleController = shuffleLoader.getController();

            Stage onTop = new Stage(StageStyle.TRANSPARENT);
            onTop.setY(owner.getY() + 400);
            onTop.setX(owner.getX() + 200);
            onTop.initOwner(owner);
            onTop.initModality(Modality.WINDOW_MODAL);

            owner.getScene().getRoot().setEffect(new GaussianBlur());

            AtomicReference<List<Kartu>> shuffleResult = new AtomicReference<>(MainGUI.currentPlayer.shuffleKartu(4));

            GridController.FillShuffle(owner, onTop, GridLadang,GridDeck, shuffleController.shufflePopup, shuffleResult.get());

            Scene shuffleScene = new Scene(root);
            shuffleScene.getStylesheets().add(MainGUI.CSSUrl);
            shuffleController.reshuffle.setOnMouseClicked(e -> {
                try {
                    shuffleResult.set(MainGUI.currentPlayer.shuffleKartu(shuffleResult.get().size()));
                    GridController.FillShuffle(owner,onTop, GridLadang, GridDeck,shuffleController.shufflePopup, shuffleResult.get());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });

            onTop.setScene(shuffleScene);
            onTop.setResizable(false);
            onTop.showAndWait();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
