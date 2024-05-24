package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.OmnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
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

            // Load FXML dan Setup Stage
            InputStream homePageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Home.fxml");
            FXMLLoader homePageLoader = new FXMLLoader();
            Parent root = homePageLoader.load(homePageFxml);

            InputStream shopPageFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/Shop.fxml");
            FXMLLoader shopPageLoader = new FXMLLoader();

            InputStream enemyFieldFxml = new FileInputStream("src/main/java/com/TiyangAlit/GUI/EnemyField.fxml");
            FXMLLoader enemyFieldLoader = new FXMLLoader();

            Scene scene = new Scene(root);

            // Ambil CSS Eksternal
            MainGUI.CSSUrl = Objects.requireNonNull(this.getClass().getResource("/style.css")).toExternalForm();
            scene.getStylesheets().add(MainGUI.CSSUrl);

            // ----------------  GAME ----------------
            Game game = new Game();
            MainGUI.currentPlayer = game.getCurrentPlayer();
            MainGUI.enemyPlayer = game.getEnemyPlayer();
            System.out.println("Enemy Player: " + enemyPlayer.getNama());
            // Bikin Player dan Toko

//          Bikin factory
            KartuFactory herbivoraFactory = new HerbivoraFactory();
            KartuFactory karnivoraFactory = new KarnivoraFactory();
            KartuFactory omnivoraFactory = new OmnivoraFactory();
            KartuFactory tanamanFactory = new TanamanFactory();
            KartuFactory produkHewanFactory = new ProdukHewanFactory();
            KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
            KartuFactory itemFactory = new ItemFactory();

//          Bikin kartu
//          Kartu Hewan
            Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");
            Herbivora sapi = (Herbivora) herbivoraFactory.createKartu("Sapi");
            Herbivora domba = (Herbivora) herbivoraFactory.createKartu("Domba");
            Herbivora kuda = (Herbivora) herbivoraFactory.createKartu("Kuda");
            Omnivora ayam = (Omnivora) omnivoraFactory.createKartu("Ayam");
            Karnivora beruang = (Karnivora) karnivoraFactory.createKartu("Beruang");

//          Kartu Produk
            ProdukTanaman labu = (ProdukTanaman) produkTanamanFactory.createKartu("Labu");
            Tanaman bijiJagung = (Tanaman) tanamanFactory.createKartu("Biji Jagung");

            ProdukHewan la = (ProdukHewan) produkHewanFactory.createKartu("Susu");
            Item acc = (Item) itemFactory.createKartu("Accelerate");
            Item del = (Item) itemFactory.createKartu("Delay");
            Item inst = (Item) itemFactory.createKartu("Instant Harvest");
            Item prot = (Item) itemFactory.createKartu("Protect");
            // mengisi ladang dan deck player
            try {
                System.out.println("helo");
                // Player 1
                MainGUI.currentPlayer.place(0, 0, hiuDarat, MainGUI.currentPlayer.getLadang());  // Hiu1
                MainGUI.currentPlayer.place(0, 1, hiuDarat, MainGUI.currentPlayer.getLadang());  // Hiu2
                MainGUI.currentPlayer.place(0, 2, bijiJagung, MainGUI.currentPlayer.getLadang());
                MainGUI.currentPlayer.place(0, 3, bijiJagung, MainGUI.currentPlayer.getLadang());
                MainGUI.currentPlayer.place(0, 4, kuda, MainGUI.currentPlayer.getLadang());
                MainGUI.currentPlayer.getDeckAktif().addKartu(hiuDarat);
                MainGUI.currentPlayer.getDeckAktif().addKartu(inst);
                MainGUI.currentPlayer.getDeckAktif().addKartu(prot);
                MainGUI.currentPlayer.getDeckAktif().addKartu(del);
                MainGUI.currentPlayer.getDeckAktif().addKartu(acc);
//                MainGUI.currentPlayer.getDeckAktif().addKartu(inst);
                MainGUI.currentPlayer.getDeckAktif().addKartu(inst);

                // Player 2
                MainGUI.enemyPlayer.place(3, 3, hiuDarat, MainGUI.enemyPlayer.getLadang());  // Hiu1
                MainGUI.enemyPlayer.place(0, 1, hiuDarat, MainGUI.enemyPlayer.getLadang());  // Hiu2
                MainGUI.enemyPlayer.place(0, 2, bijiJagung, MainGUI.enemyPlayer.getLadang());
                MainGUI.enemyPlayer.place(0, 3, bijiJagung, MainGUI.enemyPlayer.getLadang());
                MainGUI.enemyPlayer.place(0, 4, kuda, MainGUI.enemyPlayer.getLadang());
                MainGUI.enemyPlayer.getDeckAktif().addKartu(hiuDarat);
                MainGUI.enemyPlayer.getDeckAktif().addKartu(acc);
                MainGUI.enemyPlayer.getDeckAktif().addKartu(acc);
                MainGUI.enemyPlayer.getDeckAktif().addKartu(acc);
                MainGUI.enemyPlayer.getDeckAktif().addKartu(del);
                MainGUI.enemyPlayer.getDeckAktif().addKartu(del);
                System.out.println("------------------------------------");
                MainGUI.enemyPlayer.getLadang().displayLadang();
                System.out.println("------------------------------------");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            MainGUI.ladangPlayer = MainGUI.currentPlayer.getLadang();
            MainGUI.deckPlayer = MainGUI.currentPlayer.getDeckAktif();
            MainGUI.ladangEnemy = MainGUI.enemyPlayer.getLadang();
            MainGUI.deckEnemy = MainGUI.enemyPlayer.getDeckAktif();

            MainGUI.controlerHome = homePageLoader.getController();

            GridController.FillLadang(MainGUI.controlerHome.cardGrid, MainGUI.controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);
            GridController.FillDeck(MainGUI.controlerHome.cardGrid, MainGUI.controlerHome.activeDeck, MainGUI.ladangPlayer, MainGUI.deckPlayer);

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        }catch (Exception e){
            throw new Exception("ERROR");
        }

    }

}
