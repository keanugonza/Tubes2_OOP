package com.TiyangAlit;


import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

import java.nio.file.Path;
import java.nio.file.Paths;

/*
 *  CARA RUN:
 *  - mvn clean package
 *  - java -jar .\target\Tubes2_OOP_TiyangAlit-1.0-SNAPSHOT.jar
 */

public class Main {
    public static void main(String[] args) {
        // Bikin factory
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        KartuFactory itemFactory = new ItemFactory();

        // Bikin kartu
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Toko toko = new Toko();
        try {
            toko.tambahKartu(susu);
            toko.printMapData();
            toko.ambilKartu(susu);
            toko.printMapData();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Path currRelativePath = Paths.get("");
//        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();
//        System.out.println("Current absolute path is - " + currAbsolutePathString);
    }
}