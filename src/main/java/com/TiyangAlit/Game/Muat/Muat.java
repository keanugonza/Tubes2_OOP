package com.TiyangAlit.Game.Muat;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Deck.Jenis.DeckPasif;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.OmnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Muat {
    /*
     *  ATTRIBUTES
     */
    private static final ArrayList<String> listProdukTanaman = new ArrayList<>();

    static {
        listProdukTanaman.add("Labu");
        listProdukTanaman.add("Jagung");
        listProdukTanaman.add("Stroberi");
    }

    /*
     *  METHODS
     */
    public static String getPath(String filename) {
        Path currRelativePath = Paths.get("src", "main", "java", "com", "TiyangAlit", "Game", "Config", filename);
        return String.valueOf(currRelativePath.toAbsolutePath());
    }

    public static String convertNama(String nama) {
        StringBuilder sb = new StringBuilder();
        for (String str : nama.toLowerCase().split("_")) {
            sb.append(Character.toTitleCase(str.charAt(0)))
                    .append(str.substring(1))
                    .append(" ");
        }

        return sb.toString().trim();
    }

    public static void convertSlot(String slot, int[] out) {
        out[0] = slot.charAt(0) - 'A';  //  COL
        out[1] = Integer.parseInt(slot.substring(1)) - 1;
    }

    public static void muat_gameState() {
        String filePath = getPath("gamestate.txt");
        filePath = filePath.replace('\\', '/');

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // <CURRENT_TURN>
            String line = reader.readLine();
            Game.setTurnCnt(Integer.parseInt(line));

            // <JUMLAH_ITEM_DI_SHOP> (N)
            line = reader.readLine();
            int n_toko = Integer.parseInt(line);
            for (int i = 0; i < n_toko; i++) {
                // SHOP_N
                line = reader.readLine();
                String[] parts = line.split(" "); // [0] : Nama [1] : Jml

                String namaProduk = convertNama(parts[0]);
                int jumlahProduk = Integer.parseInt(parts[1]);

                // Buat produk
                KartuFactory factory = new ProdukHewanFactory();
                if (listProdukTanaman.contains(namaProduk))
                    factory = new ProdukTanamanFactory();
                Produk produk = (Produk) factory.createKartu(namaProduk);

                // Masukin ke toko
                try {
                    for (int j = 0; j < jumlahProduk; j++)
                        Game.getToko().tambahKartu(produk);
                } catch (Exception ignored) { }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void muat_player(String playerName) {
        String filePath = getPath(playerName + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Player currentPlayer = playerName.equals("player1") ? Game.getPlayers()[0] : Game.getPlayers()[1];

            // <JUMLAH_GULDEN>
            String line = reader.readLine();
            currentPlayer.addUang(Integer.parseInt(line));

            // <JUMLAH_DECK>
            line = reader.readLine();
            currentPlayer.getDeckPasif().initDeck(Integer.parseInt(line));

            // <JUMLAH_DECK_AKTIF>
            line = reader.readLine();
            int n_deck_aktif = Integer.parseInt(line);
            for (int i = 0; i < n_deck_aktif; i++) {
                // <LOKASI_KARTU_N> <KARTU_DECK_AKTIF_N>
                line = reader.readLine();
                String[] parts = line.split(" ");

                int slot = Deck.parsePosition(parts[0]);
                String namaKartu = convertNama(parts[1]);

                KartuFactory factory;
                if (Arrays.stream(ItemFactory.listItem).anyMatch(s -> s.contains(namaKartu)))
                    factory = new ItemFactory();
                else if (Arrays.stream(HerbivoraFactory.listHerbivora).anyMatch(s -> s.contains(namaKartu)))
                    factory = new HerbivoraFactory();
                else if (Arrays.stream(KarnivoraFactory.listKarnivora).anyMatch(s -> s.contains(namaKartu)))
                    factory = new KarnivoraFactory();
                else if (Arrays.stream(OmnivoraFactory.listOmnivora).anyMatch(s -> s.contains(namaKartu)))
                    factory = new OmnivoraFactory();
                else if (Arrays.stream(ProdukTanamanFactory.listProdukTanaman).anyMatch(s -> s.contains(namaKartu)))
                    factory = new ProdukTanamanFactory();
                else if (Arrays.stream(ProdukHewanFactory.listProdukHewan).anyMatch(s -> s.contains(namaKartu)))
                    factory = new ProdukHewanFactory();
                else
                    factory = new TanamanFactory();
                Kartu kartu = factory.createKartu(namaKartu);

                currentPlayer.getDeckAktif().addKartu(kartu);
            }

            // <JUMLAH_KARTU_LADANG>
            line = reader.readLine();
            int n_ladang = Integer.parseInt(line);

            for (int i = 0; i < n_ladang; i++) {
                // <LOKASI_KARTU_N> <KARTU_LADANG_N> <BOBOT_N> <N ITEM AKTIF> <ITEM...>
                line = reader.readLine();
                String[] parts = line.split(" ");

                String slot = parts[0];
                String namaEntity = convertNama(parts[1]);
                int bobot = Integer.parseInt(parts[2]);
                int n_effects = Integer.parseInt(parts[3]);

                KartuFactory entityFactory;
                if (Arrays.stream(HerbivoraFactory.listHerbivora).anyMatch(s -> s.contains(namaEntity)))
                    entityFactory = new HerbivoraFactory();
                else if (Arrays.stream(KarnivoraFactory.listKarnivora).anyMatch(s -> s.contains(namaEntity)))
                    entityFactory = new KarnivoraFactory();
                else if (Arrays.stream(OmnivoraFactory.listOmnivora).anyMatch(s -> s.contains(namaEntity)))
                    entityFactory = new OmnivoraFactory();
                else
                    entityFactory = new TanamanFactory();
                Entity ent = (Entity) entityFactory.createKartu(namaEntity);
                ent.setBobot(bobot);

                for (int j = 4; j < n_effects + 4; j++) {
                    String namaItem = convertNama(parts[j]);
                    ent.addEffect(namaItem);
                }

                int[] colrow = new int[2];
                convertSlot(slot, colrow);
                System.out.println(colrow[0] +  " " + colrow[1]);
                currentPlayer.getLadang().place(colrow[1], colrow[0], ent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
