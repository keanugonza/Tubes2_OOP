package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;


public class MainGUI extends Application{

    static Ladang ladangPlayer;
    static Ladang ladangEnemy;
    static Deck deckPlayer;
    static Deck deckEnemy;
    static HomeController controlerHome;
    static String CSSUrl;
    static Player currentPlayer;
    static Player enemyPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{

            // Bikin icon dan judul aplikasi
            InputStream stream  = new FileInputStream("src/main/java/com/TiyangAlit/Resources/StardewValley.png");
            Image image = new Image(stream);
            stage.getIcons().add(image);
            stage.setTitle("Stardew Valley");

            // Load FXML dan Setup Home Page
            InputStream homePageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Home.fxml");
            FXMLLoader homePageLoader = new FXMLLoader();
            Parent root = homePageLoader.load(homePageFxml);
            MainGUI.controlerHome = homePageLoader.getController();
            controlerHome.turnNumber.setText(String.valueOf(Game.getTurnCnt()));

            Scene scene = new Scene(root);

            // Ambil CSS Eksternal
            MainGUI.CSSUrl = Objects.requireNonNull(this.getClass().getResource("/style.css")).toExternalForm();
            scene.getStylesheets().add(MainGUI.CSSUrl);

            // ----------------  GAME ----------------
            MainGUI.currentPlayer = Game.getCurrentPlayer();
            MainGUI.enemyPlayer = Game.getEnemyPlayer();


            MainGUI.ladangPlayer = MainGUI.currentPlayer.getLadang();
            MainGUI.deckPlayer = MainGUI.currentPlayer.getDeckAktif();
            MainGUI.ladangEnemy = MainGUI.enemyPlayer.getLadang();
            MainGUI.deckEnemy = MainGUI.enemyPlayer.getDeckAktif();


            GridController.FillLadang(MainGUI.controlerHome.cardGrid, MainGUI.controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
            GridController.FillDeck(MainGUI.controlerHome.cardGrid, MainGUI.controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            SceneController.ShufflePopUp(scene.getWindow(), controlerHome.cardGrid, controlerHome.activeDeck);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        }catch (Exception e){
            throw new Exception("ERROR");
        }

    }

}
