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
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;
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

    static Ladang ladang;
    static Deck deck;
    static HomeController controlerHome;
    static ShopController controlerShop;
    static String CSSUrl;

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

            Scene scene = new Scene(root);

            // Ambil CSS Eksternal
            MainGUI.CSSUrl = Objects.requireNonNull(this.getClass().getResource("/style.css")).toExternalForm();
            scene.getStylesheets().add(MainGUI.CSSUrl);

            // Bikin Player dan Toko
            Player player1 = new Player("player1");
            Player player2 = new Player("player2");
            Toko toko = new Toko();

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

            // mengisi ladang dan deck player
            try {
                System.out.println("helo");
                // Player 1
                player1.place(0, 0, hiuDarat);  // Hiu1
                player1.place(0, 1, hiuDarat);  // Hiu2
                player1.place(0, 2, bijiJagung);
                player1.place(0, 3, bijiJagung);
                player1.place(0, 4, kuda);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(del);
                player1.getDeckAktif().addKartu(del);

                // Player 2
                player2.place(0, 0, hiuDarat);  // Hiu1
                player2.place(0, 1, hiuDarat);  // Hiu2
                player2.place(0, 2, bijiJagung);
                player2.place(0, 3, bijiJagung);
                player2.place(0, 4, kuda);
                player2.getDeckAktif().addKartu(acc);
                player2.getDeckAktif().addKartu(acc);
                player2.getDeckAktif().addKartu(acc);
                player2.getDeckAktif().addKartu(acc);
                player2.getDeckAktif().addKartu(del);
                player2.getDeckAktif().addKartu(del);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            MainGUI.ladang = player1.getLadang();
            MainGUI.deck = player1.getDeckAktif();

            MainGUI.controlerHome = homePageLoader.getController();
            MainGUI.controlerShop = shopPageLoader.getController();

            GridController.FillLadang(MainGUI.controlerHome.cardGrid, MainGUI.controlerHome.activeDeck, ladang, deck);
            GridController.FillDeck(MainGUI.controlerHome.cardGrid, MainGUI.controlerHome.activeDeck, ladang, deck);

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
