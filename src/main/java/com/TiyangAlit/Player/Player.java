package com.TiyangAlit.Player;

import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Deck.Jenis.DeckAktif;
import com.TiyangAlit.Deck.Jenis.DeckPasif;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Ladang.LadangExceptions.InvalidKartuException;
import com.TiyangAlit.Player.PlayerException.InvalidCardException;
import com.TiyangAlit.Player.PlayerException.NotEnoughMoneyException;
import com.TiyangAlit.Toko.Toko;
import com.TiyangAlit.Toko.TokoException.InvalidProdukTokoException;
import com.TiyangAlit.Toko.TokoException.NoSuchProdukOnToko;

import java.util.List;

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
        this.ladang = new Ladang(this);
        this.uang = 100000;
        this.deckAktif = new DeckAktif();
        this.deckPasif = new DeckPasif();
    }

    // Getter & Setter
    public String getNama() { return this.nama; }
    public Ladang getLadang() { return this.ladang; }
    public int getUang() { return this.uang; }
    public DeckAktif getDeckAktif() { return this.deckAktif; }
    public DeckPasif getDeckPasif() { return this.deckPasif; }

    public void addUang(int amt) { this.uang += amt; }
    public void reduceUang(int amt) { this.uang -= amt; }

    public void setUang(int amt) {
        this.uang = amt;
    }

    // Lain-lain
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Player p)) return false;

        return p.getNama().equals(this.getNama());
    }

    // Perintah
    public void place(int row, int col, Kartu kartu, Ladang ladang) throws Exception {
        if (!ladang.getPemilikLadang().equals(this)) {
            // Place di ladang musuh
            String nama = kartu.getNama();
            if (!(kartu instanceof Item) || (!nama.equals("Destroy") && !nama.equals("Delay")))
                throw new InvalidKartuException("Hanya kartu [Destroy] dan [Delay] yang dapat diletakkan di ladang lawan.");
        }

        ladang.place(row, col, kartu);
    }

    public List<Kartu> shuffleKartu(int jumlah) throws Exception{
        List<Kartu> list = this.deckPasif.shuffleKartu();
        if (jumlah > list.size()){
            throw new Exception("Terlalu banyak jumlahnya");
        }
        if (this.deckAktif.getSLotKosong() < list.size()){
            return list.subList(0, this.deckAktif.getSLotKosong());
        }else{
            return list.subList(0, jumlah);
        }
    }

    public void moveFromShuffle_to_Aktif(List<Kartu> list, Kartu kartu) throws Exception {
        this.deckAktif.addKartu(kartu);
        this.deckPasif.removeKartu(kartu);
        list.remove(kartu);
    }

    public void panen(int row, int col) throws Exception {
        // Lakukan panen jika terdapat slot kosong pada deck aktif
        if (this.deckAktif.isFull())
            throw new DeckFullException("Deck aktif sudah penuh, tidak bisa panen.");

        Produk hasilPanen = this.ladang.panen(row, col);
        this.deckAktif.addKartu(hasilPanen);
    }

    public void beli(Produk produk, Toko toko) throws Exception{
        if(produk.getHarga() > this.uang){
            throw new NotEnoughMoneyException("Uang Kurang");
        }
        try{
            this.deckAktif.addKartu(produk);
            toko.ambilKartu(produk);
            this.uang -= produk.getHarga();
        }catch (InvalidProdukTokoException e){
            System.out.println();
        } catch (NoSuchProdukOnToko e){
            System.out.println("Produk tidak ada di toko");
            this.deckAktif.removeKartu(produk);
        } catch (DeckFullException e){
            System.out.println("Deck penuh");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void jual(Kartu kartu, Toko toko) throws Exception {
        if(kartu instanceof Produk){
            toko.tambahKartu(kartu);
            this.deckAktif.removeKartu(kartu);
            this.uang += ((Produk) kartu).getHarga();
        } else{
            throw new InvalidCardException("Bukan Produk, tidak bisa dijual");
        }
    }

    //TESTING
    public void printDeckAktif(){
        this.deckAktif.displayDeck();
    }
}
