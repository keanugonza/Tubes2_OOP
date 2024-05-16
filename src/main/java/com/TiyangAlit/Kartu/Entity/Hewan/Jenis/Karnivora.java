package com.TiyangAlit.Kartu.Entity.Hewan.Jenis;

import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Entity.Hewan.HewanExceptions.SalahTipeMakananException;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;

public class Karnivora extends Hewan {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Karnivora(String nama, int bobot, int bobotHarvest) {
        super(nama, bobot, bobotHarvest);
    }

    // Lain-lain
    @Override
    public void makan(Produk makanan) throws SalahTipeMakananException {
        if (makanan instanceof ProdukTanaman) {
            throw new SalahTipeMakananException("Hewan karnivora tidak dapat memakan produk tumbuhan.");
        }

        addBobot(makanan.getTambahBerat());
    }
}
