package com.TiyangAlit.Toko;

import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;

public class Test_Toko {
    public static void main(String[] args) {
        // Bikin factory
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        KartuFactory itemFactory = new ItemFactory();

        // Bikin kartu
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Toko toko = new Toko();
        try {
            toko.printMapData();
            toko.tambahKartu(susu);
            System.out.println("========================");
            toko.printMapData();
            toko.ambilKartu(susu);
            System.out.println("========================");
            toko.printMapData();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Path currRelativePath = Paths.get("");
//        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();
//        System.out.println("Current absolute path is - " + currAbsolutePathString)
    }
}
