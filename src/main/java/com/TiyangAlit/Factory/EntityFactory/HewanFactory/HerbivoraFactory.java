package com.TiyangAlit.Factory.EntityFactory.HewanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora;
import com.TiyangAlit.Kartu.Kartu;

public class HerbivoraFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapBobotHarvest.put("Sapi", 10);
        mapBobotHarvest.put("Domba", 12);
        mapBobotHarvest.put("Kuda", 14);

        mapProduk.put("Sapi", "Susu");
        mapProduk.put("Domba", "Daging Domba");
        mapProduk.put("Kuda", "Daging Kuda");
    }

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!mapBobotHarvest.containsKey(nama))
            return null;

        return new Herbivora(nama, 0, mapBobotHarvest.get(nama), mapProduk.get(nama));
    }
}
