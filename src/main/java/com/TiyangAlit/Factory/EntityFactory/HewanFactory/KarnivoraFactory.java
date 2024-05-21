package com.TiyangAlit.Factory.EntityFactory.HewanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Kartu;

import java.nio.file.Path;
import java.nio.file.Paths;

public class KarnivoraFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapBobotHarvest.put("Hiu Darat", 20);

        mapProduk.put("Hiu Darat", "Sirip Hiu");
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

        return new Karnivora(nama, currAbsolutePathString, 0, mapBobotHarvest.get(nama), mapProduk.get(nama));
    }
}