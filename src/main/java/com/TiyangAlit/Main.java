package com.TiyangAlit;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;

public class Main {
    public static void main(String[] args) {
        KartuFactory k = new ProdukHewanFactory();

        ProdukHewan susu = (ProdukHewan) k.createKartu("Sirip Hiu");
        System.out.println(susu.getHarga());
    }
}