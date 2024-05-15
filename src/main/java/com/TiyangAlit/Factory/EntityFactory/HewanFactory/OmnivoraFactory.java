package com.TiyangAlit.Factory.EntityFactory.HewanFactory;

import com.TiyangAlit.Factory.EntityFactory.EntityFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora;
import com.TiyangAlit.Kartu.Kartu;

public class OmnivoraFactory extends EntityFactory {
    /*
     *  ATTRIBUTES
     */
    static {
        mapBobotHarvest.put("Ayam", 5);
        mapBobotHarvest.put("Beruang", 25);
    }

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!mapBobotHarvest.containsKey(nama))
            return null;

        return new Omnivora(nama, 0, mapBobotHarvest.get(nama));
    }
}