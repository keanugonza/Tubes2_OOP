package com.TiyangAlit.Kartu.Produk;

import com.TiyangAlit.Kartu.Kartu;

public abstract class Produk extends Kartu {
    /*
     *   ATTRIBUTES
     */
    private final int harga;
    private final int tambahBerat;

    /*
     *   METHODS
     */
    public Produk(String nama, int harga, int tambahBerat) {
        super(nama);
        this.harga = harga;
        this.tambahBerat = tambahBerat;
    }

    // Getter & Setter
    public int getHarga() { return this.harga; }
    public int getTambahBerat() { return this.tambahBerat; }
}