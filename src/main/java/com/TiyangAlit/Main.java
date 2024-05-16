package com.TiyangAlit;


import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Player.Player;

/*
 *  CARA RUN:
 *  - mvn clean package
 *  - java -jar .\target\Tubes2_OOP_TiyangAlit-1.0-SNAPSHOT.jar
 */

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("player1");

        // Bikin factory
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        KartuFactory itemFactory = new ItemFactory();

        // Bikin kartu
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");
        Item accelerate = (Item) itemFactory.createKartu("Accelerate");
        Item delay = (Item) itemFactory.createKartu("Delay");
        Item instantHarvest = (Item) itemFactory.createKartu("Instant Harvest");
        Item destroy = (Item) itemFactory.createKartu("Destroy");
        Item protect = (Item) itemFactory.createKartu("Protect");
        Item trap = (Item) itemFactory.createKartu("Trap");

        try {
            player1.place(0, 0, hiuDarat);  // Hiu1
            player1.place(1, 0, hiuDarat);  // Hiu2
            player1.getLadang().displayLadang();
            for (int i = 0; i < 7; i++)
                player1.place(0, 0, susu);  // Kasih 7 susu ke Hiu1
            player1.place(1, 0, accelerate);    // Accelerate hiu2
            player1.place(1, 0, protect);       // Protect hiu2
            player1.place(1, 0, destroy);       // Destroy hiu2 (gagal karena ada protect)
            player1.place(1, 0, delay);         // Delay hiu2
            player1.place(1, 0, trap);          // Trap hiu2

            System.out.println("Berat Hiu1: " + player1.getLadang().getData().getEl(0, 0).getBobot());
            System.out.println("Berat Hiu2: " + player1.getLadang().getData().getEl(1, 0).getBobot());
            System.out.println("Efek Hiu2: " + player1.getLadang().getData().getEl(1, 0).getEffects());

            player1.panen(0,0); // Panen Hiu1

            player1.getDeckAktif().displayDeck();
            player1.place(1, 0, instantHarvest); // Panen Hiu2
            player1.getDeckAktif().displayDeck();

            player1.getLadang().displayLadang();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}