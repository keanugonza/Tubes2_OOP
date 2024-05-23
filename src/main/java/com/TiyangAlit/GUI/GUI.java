package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;


public class GUI extends Application{

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) throws Exception {
        try{
            InputStream stream  = new FileInputStream("src/main/java/com/TiyangAlit/Resources/StardewValley.png");
            Image image = new Image(stream);
            stage.getIcons().add(image);
            stage.setTitle("Stardew Valley");

            InputStream fxmlUrl = new FileInputStream("src/main/java/com/TiyangAlit/GUI/GUI.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(fxmlUrl);


            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/style.css")).toExternalForm());


            Controller controller1 = fxmlLoader.getController();
//            GridController.FillLadang(controller1.cardGrid,1,5);

            Player player1 = new Player("player1");

            // Bikin factory
            KartuFactory produkHewanFactory = new ProdukHewanFactory();
            KartuFactory karnivoraFactory = new KarnivoraFactory();
            KartuFactory herbivoraFactory = new HerbivoraFactory();
            KartuFactory itemFactory = new ItemFactory();
            KartuFactory tanamanFactory = new TanamanFactory();

            // Bikin kartu
            ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
            Herbivora kuda = (Herbivora) herbivoraFactory.createKartu("Kuda");
            Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");
            Tanaman jagung = (Tanaman) tanamanFactory.createKartu("Biji Jagung");
            Item acc = (Item) itemFactory.createKartu("Accelerate");
            Item del = (Item) itemFactory.createKartu("Delay");

            try {
                player1.place(0, 0, hiuDarat);  // Hiu1
                player1.place(0, 1, hiuDarat);  // Hiu2
                player1.place(0, 2, jagung);
                player1.place(0, 3, jagung);
                player1.place(0, 4, kuda);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(acc);
                player1.getDeckAktif().addKartu(del);
                player1.getDeckAktif().addKartu(del);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Ladang ladangku = player1.getLadang();
            Deck deck = player1.getDeckAktif();
            GridController.FillLadang(controller1.cardGrid, controller1.activeDeck, ladangku, deck);
            GridController.FillDeck(controller1.cardGrid, controller1.activeDeck, ladangku, deck);

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            throw new Exception("ERROR");
        }

    }

}
