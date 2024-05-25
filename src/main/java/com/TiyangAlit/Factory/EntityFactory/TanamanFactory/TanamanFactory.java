package com.TiyangAlit.Factory.EntityFactory.TanamanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Kartu;

public class TanamanFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    public final static String[] listTanaman = new String[]{"Biji Jagung","Biji Labu","Biji Stroberi"};

    static {
        mapBobotHarvest.put("Biji Jagung", 3);
        mapBobotHarvest.put("Biji Labu", 5);
        mapBobotHarvest.put("Biji Stroberi", 4);

        mapProduk.put("Biji Jagung", "Jagung");
        mapProduk.put("Biji Labu", "Labu");
        mapProduk.put("Biji Stroberi", "Stroberi");
    }

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!mapBobotHarvest.containsKey(nama))
            return null;

//        Path currRelativePath = Paths.get("src","main", "java", "com", "TiyangAlit", "Resources", "Assets", "Tanaman", nama + ".png");
//        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";

        String currAbsolutePathString = "/Assets/Tanaman/" + nama + ".png";

        return new Tanaman(nama, currAbsolutePathString, 0, mapBobotHarvest.get(nama), mapProduk.get(nama));
    }
}
