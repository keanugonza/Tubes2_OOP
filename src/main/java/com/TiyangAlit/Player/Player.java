package com.TiyangAlit.Player;

import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Ladang.LadangExceptions.InvalidKartuException;

public class Player {
    /*
     *  ATTRIBUTES
     */
    private final String nama;
    private int uang;
    private final Ladang ladang;
    // TODO: Deck

    /*
     *  ATTRIBUTES
     */
    public Player(String nama) {
        this.nama = nama;
        this.ladang = new Ladang();
        this.uang = 0;
    }

    // Getter & Setter
    public String getNama() { return this.nama; }
    public Ladang getLadang() { return this.ladang; }
    public int getUang() { return this.uang; }

    public void addUang(int amt) { this.uang += amt; }
    public void reduceUang(int amt) { this.uang -= amt; }

    // Perintah
    public void place(int row, int col, Kartu kartu) throws Exception {
        // Letakkan kartu di ladang sendiri
        // TODO: Throw exception kalo delay & destroy (?) tanya waktu asistensi
        ladang.place(row, col, kartu);
    }

    public void place(int row, int col, Kartu kartu, Player lawan) throws Exception {
        // Letakkan kartu di ladang lawan
        String nama = kartu.getNama();
        if (!(kartu instanceof Item) || (!nama.equals("Destroy") && !nama.equals("Delay")))
            throw new InvalidKartuException("Hanya kartu [Destroy] dan [Delay] yang dapat diletakkan di ladang lawan.");

        lawan.getLadang().place(row, col, kartu);
    }

    public void panen(int row, int col) throws Exception {
        // Panen (ladang sendiri)
        // TODO: add hasilPanen ke deck aktif (QnA no.6)
        Produk hasilPanen = this.ladang.panen(row, col);
        System.out.println(hasilPanen.getNama());  // Testing
    }
}
