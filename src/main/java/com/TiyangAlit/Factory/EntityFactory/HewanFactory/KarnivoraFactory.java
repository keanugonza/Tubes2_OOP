package com.TiyangAlit.Factory.EntityFactory.HewanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Kartu;

public class KarnivoraFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    public final static String[] listKarnivora = new String[]{"Hiu Darat"};

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

//        Path currRelativePath = Paths.get("src","main", "java", "com", "TiyangAlit", "Resources", "Assets", "Hewan", nama + ".png");
//        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";

        String currAbsolutePathString = "/Assets/Hewan/" + nama + ".png";

        return new Karnivora(nama, currAbsolutePathString, 0, mapBobotHarvest.get(nama), mapProduk.get(nama));
    }
}