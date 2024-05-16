package com.TiyangAlit;

import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.FoF;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Player.Player;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("player1");

        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        ProdukHewan susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Karnivora hiuDarat = (Karnivora) karnivoraFactory.createKartu("Hiu Darat");

        try {
            player1.place(0, 0, hiuDarat);
            player1.place(1, 0, hiuDarat);
            player1.getLadang().displayLadang();
            player1.place(0, 0, susu);

            System.out.println(player1.getLadang().getData().getEl(0, 0).getBobot());
            System.out.println(player1.getLadang().getData().getEl(1, 0).getBobot());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}