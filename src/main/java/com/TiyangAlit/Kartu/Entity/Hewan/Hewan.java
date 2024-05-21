package com.TiyangAlit.Kartu.Entity.Hewan;

import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Hewan.HewanExceptions.SalahTipeMakananException;
import com.TiyangAlit.Kartu.Produk.Produk;

public abstract class Hewan extends Entity {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Hewan(String nama, String image, int bobot, int bobotHarvest, String produk) {
        super(nama, image, bobot, bobotHarvest, produk);
    }

    public abstract void makan(Produk makanan) throws SalahTipeMakananException;
}