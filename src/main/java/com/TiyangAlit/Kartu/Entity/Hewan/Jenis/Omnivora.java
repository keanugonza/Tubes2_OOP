package com.TiyangAlit.Kartu.Entity.Hewan.Jenis;

import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Produk.Produk;

public class Omnivora extends Hewan {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Omnivora(String nama, int bobot, int bobotHarvest, String produk) {
        super(nama, bobot, bobotHarvest, produk);
    }

    // Getter & Setter

    @Override
    public void makan(Produk makanan) {
        addBobot(makanan.getTambahBerat());
    }
}