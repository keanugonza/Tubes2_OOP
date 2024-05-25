package com.TiyangAlit.Game.Muat;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    public static void muat_gameState() {
        String filePath = getPath("gamestate.txt");
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



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
