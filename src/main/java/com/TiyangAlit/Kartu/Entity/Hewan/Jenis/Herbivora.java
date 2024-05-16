package com.TiyangAlit.Kartu.Entity.Hewan.Jenis;

import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Entity.Hewan.HewanExceptions.SalahTipeMakananException;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.Produk;

public class Herbivora extends Hewan {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Herbivora(String nama, int bobot, int bobotHarvest, String produk) {
        super(nama, bobot, bobotHarvest, produk);
    }

    // Lain-lain
    @Override
    public void makan(Produk makanan) throws SalahTipeMakananException {
        if (makanan instanceof ProdukHewan) {
            throw new SalahTipeMakananException("Hewan herbivora tidak dapat memakan produk hewan.");
        }

        addBobot(makanan.getTambahBerat());
    }
}
