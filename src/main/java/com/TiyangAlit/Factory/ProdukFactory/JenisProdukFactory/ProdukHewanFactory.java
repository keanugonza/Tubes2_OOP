package com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory;

import com.TiyangAlit.Factory.ProdukFactory.ProdukFactory;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProdukHewanFactory extends ProdukFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapHarga.put("Sirip Hiu", 500);
        mapHarga.put("Susu", 100);
        mapHarga.put("Daging Domba", 120);
        mapHarga.put("Daging Kuda", 150);
        mapHarga.put("Telur", 50);
        mapHarga.put("Daging Beruang", 500);

        mapBerat.put("Sirip Hiu", 12);
        mapBerat.put("Susu", 4);
        mapBerat.put("Daging Domba", 6);
        mapBerat.put("Daging Kuda", 8);
        mapBerat.put("Telur", 2);
        mapBerat.put("Daging Beruang", 12);
    }

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!mapHarga.containsKey(nama))
            return null;

        Path currRelativePath = Paths.get("src","main", "java", "com", "TiyangAlit", "Resources", "Assets", "Produk", nama + ".png");
        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";

        return new ProdukHewan(nama, currAbsolutePathString, mapHarga.get(nama), mapBerat.get(nama));
    }
}