package com.TiyangAlit.Kartu.Entity.Hewan;

import com.TiyangAlit.Kartu.Entity.Entity;

import java.util.HashMap;

public abstract class Hewan extends Entity {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Hewan(String nama, int bobot, int bobotHarvest) {
        super(nama, bobot, bobotHarvest);
    }
}