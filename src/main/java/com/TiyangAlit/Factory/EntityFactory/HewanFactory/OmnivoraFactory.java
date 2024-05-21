package com.TiyangAlit.Factory.EntityFactory.HewanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora;
import com.TiyangAlit.Kartu.Kartu;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OmnivoraFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapBobotHarvest.put("Ayam", 5);
        mapBobotHarvest.put("Beruang", 25);

        mapProduk.put("Ayam", "Telur");
        mapProduk.put("Beruang", "Daging Beruang");
    }

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!mapBobotHarvest.containsKey(nama))
            return null;

        Path currRelativePath = Paths.get("src","main", "java", "com", "TiyangAlit", "Resources", "Assets", "Hewan", nama + ".png");
        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";

        return new Omnivora(nama, currAbsolutePathString, 0, mapBobotHarvest.get(nama), mapProduk.get(nama));
    }
}