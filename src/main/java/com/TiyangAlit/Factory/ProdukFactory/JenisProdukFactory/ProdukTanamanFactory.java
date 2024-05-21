package com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory;

import com.TiyangAlit.Factory.ProdukFactory.ProdukFactory;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProdukTanamanFactory extends ProdukFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapHarga.put("Jagung", 150);
        mapHarga.put("Labu", 500);
        mapHarga.put("Stroberi", 350);

        mapBerat.put("Jagung", 3);
        mapBerat.put("Labu", 10);
        mapBerat.put("Stroberi", 5);
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

        return new ProdukTanaman(nama, currAbsolutePathString, mapHarga.get(nama), mapBerat.get(nama));
    }
}