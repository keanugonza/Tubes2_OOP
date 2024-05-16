package com.TiyangAlit.Kartu.Entity.Hewan;

import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Hewan.HewanExceptions.SalahTipeMakananException;
import com.TiyangAlit.Kartu.Produk.Produk;

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

    public abstract void makan(Produk makanan) throws SalahTipeMakananException;
}