package com.TiyangAlit.Player;

import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Toko.Toko;

public class Test_Player {
    public static void main(String[] args) {
        Player player1 = new Player("player1");
        // Bikin factory
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        KartuFactory itemFactory = new ItemFactory();
        KartuFactory tanamanFactory = new TanamanFactory();

        // Bikin kartu
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");
        Tanaman jagung = (Tanaman) tanamanFactory.createKartu("Biji Jagung");
        Item accelerate = (Item) itemFactory.createKartu("Accelerate");
        Item delay = (Item) itemFactory.createKartu("Delay");
        Item instantHarvest = (Item) itemFactory.createKartu("Instant Harvest");
        Item destroy = (Item) itemFactory.createKartu("Destroy");
        Item protect = (Item) itemFactory.createKartu("Protect");
        Item trap = (Item) itemFactory.createKartu("Trap");

        Toko toko = new Toko();
        try {
            toko.tambahKartu(susu);
            toko.printMapData();  //ini
            System.out.println("========================");
            player1.printDeckAktif(); //ini
            System.out.println("========================");
            player1.beli(susu,toko);
            player1.printDeckAktif(); //ini ada susu
            System.out.println("========================");
            toko.printMapData(); // ini susu berkurang
            System.out.println("========================");
            player1.beli(susu,toko);
            player1.printDeckAktif();
            System.out.println("========================");
            toko.printMapData(); // ini susu berkurang
            System.out.println("========================");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
