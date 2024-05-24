package com.TiyangAlit;


import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Game.Simpan;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

import java.io.File;
import java.io.FileWriter;
/*
 *  CARA RUN:
 *  - mvn clean package
 *  - java -jar .\target\Tubes2_OOP_TiyangAlit-1.0-SNAPSHOT.jar
 */

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        // Bikin factory
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        KartuFactory itemFactory = new ItemFactory();
        KartuFactory tanamanFactory = new TanamanFactory();

        // Bikin kartu
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Produk DagingDomba = (ProdukHewan) produkHewanFactory.createKartu("Daging Domba");
        Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");
        Tanaman jagung = (Tanaman) tanamanFactory.createKartu("Biji Jagung");
        Item accelerate = (Item) itemFactory.createKartu("Accelerate");
        Item delay = (Item) itemFactory.createKartu("Delay");
        Item instantHarvest = (Item) itemFactory.createKartu("Instant Harvest");
        Item destroy = (Item) itemFactory.createKartu("Destroy");
        Item protect = (Item) itemFactory.createKartu("Protect");
        Item trap = (Item) itemFactory.createKartu("Trap");

        try {
            player1.place(0, 0, hiuDarat, player1.getLadang());  // Hiu1
            player1.place(1, 0, hiuDarat, player1.getLadang());  // Hiu2
            player1.place(2, 0, jagung, player1.getLadang()); // Biji Jagung
//            player1.getLadang().displayLadang();
            for (int i = 0; i < 7; i++)
                player1.place(0, 0, susu,player1.getLadang());  // Kasih 7 susu ke Hiu1
            player1.place(1, 0, accelerate, player1.getLadang());    // Accelerate hiu2
            player1.place(1, 0, accelerate, player1.getLadang());    // Accelerate hiu2
            player1.place(1, 0, protect, player1.getLadang());       // Protect hiu2
            player1.place(1, 0, destroy, player1.getLadang());       // Destroy hiu2 (gagal karena ada protect)
            player1.place(1, 0, delay, player1.getLadang());         // Delay hiu2
            player1.place(1, 0, trap, player1.getLadang());          // Trap hiu2

            // Display the locations and details of cards in the ladang
            System.out.println(player1.getLadang().getLocationCardNameBobotItemsApplied());

//          SIMPAN MUAT BROOOOOOOO
            File myObj = new File("bojonem.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            File mbahe = new File("mbahem.txt");
            if (mbahe.createNewFile()) {
                System.out.println("File created: " + mbahe.getName());
            } else {
                System.out.println("File already exists.");
            }

            Game game = new Game();

            Toko toko = new Toko();
            toko.tambahKartu(susu);
            toko.tambahKartu(susu);
            toko.tambahKartu(DagingDomba);
            toko.tambahKartu(DagingDomba);
            toko.tambahKartu(DagingDomba);
            toko.tambahKartu(DagingDomba);

            Simpan.saveGameState(game, toko, "bojonem.txt");
            toko.printMapData();

            player1.getDeckAktif().addKartu(susu);
            player1.getDeckAktif().addKartu(hiuDarat);
            player1.getDeckAktif().addKartu(susu);
            player1.getDeckAktif().addKartu(hiuDarat);
            System.out.println(player1.getDeckAktif().getLocationAndCardName());

            Simpan.savePlayer(player1,"mbahem.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}