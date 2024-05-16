package com.TiyangAlit;

import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
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

        // Bikin kartu
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");

        try {
            player1.place(0, 0, hiuDarat);  // Hiu1
            player1.place(1, 0, hiuDarat);  // Hiu2
            player1.getLadang().displayLadang();
            for (int i = 0; i < 7; i++)
                player1.place(0, 0, susu);  // Kasih 7 susu ke Hiu1

            System.out.println("Berat Hiu1: " + player1.getLadang().getData().getEl(0, 0).getBobot());
            System.out.println("Berat Hiu1: " + player1.getLadang().getData().getEl(1, 0).getBobot());

            player1.panen(0,0); // Panen Hiu1
            player1.getLadang().displayLadang();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}