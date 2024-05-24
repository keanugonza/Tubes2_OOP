package com.TiyangAlit.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

public class Muat {
    private String FILE_NAME;

    public Muat (String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFILE_NAME() {
        return this.FILE_NAME;
    }

    public static void loadGameState(String FILE_NAME) {
        try {
            Toko toko = Game.getToko();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            // Read current turn count
            int currentTurn = Integer.parseInt(reader.readLine().trim());
            Game.setTurnCnt(currentTurn);

            // Read shop data count
            int shopItemCount = Integer.parseInt(reader.readLine().trim());


            // Read shop items and their quantities
            for (int i = 0; i < shopItemCount; i++) {
                String[] line = reader.readLine().split(" ", 2);
                if (line.length == 2) {
                    String productName = line[0];
                    if (productName.equals("Sirip Hiu") || productName.equals("Susu") || productName.equals("Daging Domba") || productName.equals("Daging Kuda") || productName.equals("Telur") || productName.equals("Daging Beruang")) {
                        KartuFactory produkHewanFactory = new ProdukHewanFactory();
                        Produk prod = (ProdukHewan) produkHewanFactory.createKartu(productName);
                        int quantity = Integer.parseInt(line[1].trim());
                        toko.getData().put(prod, quantity);
                    } else if (productName.equals("Jagung") || productName.equals("Labu") || productName.equals("Stroberi")) {
                        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
                        Produk prod = (ProdukTanaman) produkTanamanFactory.createKartu(productName);
                        int quantity = Integer.parseInt(line[1].trim());
                        toko.getData().put(prod, quantity);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading game state from " + FILE_NAME);
            e.printStackTrace();
        }
    }


    public static void loadPlayer(String FILE_NAME) {
        try {
            Player player = Game.getCurrentPlayer();
            Toko toko = Game.getToko();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            // Read jumlah Gulden
            int jumlahGulden = Integer.parseInt(reader.readLine().trim());
            player.setUang(jumlahGulden);

            // Read jumlah Kartu dalam Deck
            int jumlahDeck = Integer.parseInt(reader.readLine().trim());
            player.getDeckPasif().getSize();

            // Read shop items and their quantities
            for (int i = 0; i < shopItemCount; i++) {
                String[] line = reader.readLine().split(" ", 2);
                if (line.length == 2) {
                    String productName = line[0];
                    if (productName.equals("Sirip Hiu") || productName.equals("Susu") || productName.equals("Daging Domba") || productName.equals("Daging Kuda") || productName.equals("Telur") || productName.equals("Daging Beruang")) {
                        KartuFactory produkHewanFactory = new ProdukHewanFactory();
                        Produk prod = (ProdukHewan) produkHewanFactory.createKartu(productName);
                        int quantity = Integer.parseInt(line[1].trim());
                        toko.getData().put(prod, quantity);
                    } else if (productName.equals("Jagung") || productName.equals("Labu") || productName.equals("Stroberi")) {
                        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
                        Produk prod = (ProdukTanaman) produkTanamanFactory.createKartu(productName);
                        int quantity = Integer.parseInt(line[1].trim());
                        toko.getData().put(prod, quantity);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading game state from " + FILE_NAME);
            e.printStackTrace();
        }
    }


}
