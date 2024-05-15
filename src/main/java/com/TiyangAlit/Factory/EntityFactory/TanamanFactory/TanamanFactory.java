package com.TiyangAlit.Factory.EntityFactory.TanamanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Kartu;

public class TanamanFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapBobotHarvest.put("Biji Jagung", 3);
        mapBobotHarvest.put("Biji Labu", 5);
        mapBobotHarvest.put("Biji Stroberi", 4);
    }

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!mapBobotHarvest.containsKey(nama))
            return null;

        return new Tanaman(nama, 0, mapBobotHarvest.get(nama));
    }
}
