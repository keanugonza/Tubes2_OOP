package com.TiyangAlit.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

public class Simpan {
//    private static final String FILE_NAME = "bapakmu.txt";
    private String FILE_NAME;

    public Simpan (String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFILE_NAME() {
        return this.FILE_NAME;
    }

    public static void saveGameState(String FILE_NAME) {
        try {
            Toko toko = Game.getToko();
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            int turnCount = Game.getTurnCnt();
            fileWriter.write(String.valueOf(turnCount) + "\n");
            System.out.println("Turn count saved to " + FILE_NAME);
            System.out.println("Piro : " + turnCount);
            int tokoDataCount = toko.getLength();
            fileWriter.write(String.valueOf(tokoDataCount) + "\n");
            System.out.println("Toko data count saved to " + FILE_NAME);
            // Append mode
            for (Map.Entry<Produk, Integer> entry : toko.getData().entrySet()) {
                if (entry.getValue() > 0) {
                    fileWriter.write(entry.getKey().getNama() + " " + entry.getValue() + "\n");
                }
            }
            System.out.println("Non-empty product types saved to " + FILE_NAME);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing non-empty product types to " + FILE_NAME);
            e.printStackTrace();
        }
    }

    public static void savePlayer(String FILE_NAME) {
        try {
            Player player = Game.getCurrentPlayer();
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            fileWriter.write(player.getUang() + "\n");
            fileWriter.write(player.getDeckPasif().getKartuCount() + "\n");
            fileWriter.write(player.getDeckAktif().getKartuCount() + "\n");

            StringBuilder result = new StringBuilder();
            int rowIndex = 0;
            int column = 1;
            for (int i = 0; i < player.getDeckAktif().getSize(); i++) {
                char row = (char) ('A' + rowIndex);
                result.append(row)
                        .append(String.format("%02d", column))
                        .append(" ")
                        .append(player.getDeckAktif().getDeck().get(i).getNama())
                        .append("\n");
                rowIndex++;
            }
            fileWriter.write(result.toString());
            fileWriter.write(player.getLadang().getJumlahKartu() + "\n");
            fileWriter.write(player.getLadang().getLocationCardNameBobotItemsApplied());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing non-empty product types to " + FILE_NAME);
            e.printStackTrace();
        }
    }



}