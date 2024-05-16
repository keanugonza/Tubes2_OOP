package com.TiyangAlit.Player;

import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Deck.Jenis.DeckAktif;
import com.TiyangAlit.Deck.Jenis.DeckPasif;
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
    private final DeckAktif deckAktif;
    private final DeckPasif deckPasif;

    /*
     *  ATTRIBUTES
     */
    public Player(String nama) {
        this.nama = nama;
        this.ladang = new Ladang();
        this.uang = 0;
        this.deckAktif = new DeckAktif();
        this.deckPasif = new DeckPasif();
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
        // Lakukan panen jika terdapat slot kosong pada deck aktif
        if (this.deckAktif.isFull())
            throw new DeckFullException("Deck aktif sudah penuh, tidak bisa panen.");

        Produk hasilPanen = this.ladang.panen(row, col);
        this.deckAktif.addKartu(hasilPanen);
    }

    public void shuffleKartu() {
        // TODO: Implement
    }

    public void beli() {
        // TODO: Implement
    }

    public void jual() {
        // TODO: Implement
    }
}
