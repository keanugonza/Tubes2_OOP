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

    public static void gamestate() {
        try {
            Toko toko = Game.getToko();
            FileWriter fileWriter = new FileWriter("src/main/java/com/TiyangAlit/Game/Config/gamestate.txt");
            int turnCount = Game.getTurnCnt();
            fileWriter.write(String.valueOf(turnCount) + "\n");
            int tokoDataCount = toko.getLength();
            fileWriter.write(String.valueOf(tokoDataCount) + "\n");
            // Append mode
            for (Map.Entry<Produk, Integer> entry : toko.getData().entrySet()) {
                if (entry.getValue() > 0) {
                    fileWriter.write(entry.getKey().getNama() + " " + entry.getValue() + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing non-empty product types to " + "src/main/java/com/TiyangAlit/Game/Config");
            e.printStackTrace();
        }
    }

    public static void savePlayer1() {
        try {
            Player[] players = Game.getPlayers();
            Player player = players[0];
            FileWriter fileWriter = new FileWriter("src/main/java/com/TiyangAlit/Game/Config/player1.txt");
            fileWriter.write(player.getUang() + "\n");
            fileWriter.write(player.getDeckPasif().getSize() + "\n");
            fileWriter.write(player.getDeckAktif().getSize() + "\n");

            StringBuilder result = new StringBuilder();
            int rowIndex = 0;
            int column = 1;
            for (int i = 0; i < player.getDeckAktif().getSize(); i++) {
                char row = (char) ('A' + rowIndex);
                result.append(row)
                        .append(String.format("%02d", column))
                        .append(" ")
                        .append(player.getDeckAktif().getDeck().get(i).getNama().toUpperCase().replace(" ", "_"))
                        .append("\n");
                rowIndex++;
            }
            fileWriter.write(result.toString());
            fileWriter.write(player.getLadang().getJumlahKartu() + "\n");
            fileWriter.write(player.getLadang().getLocationCardNameBobotItemsApplied());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing non-empty product types");
            e.printStackTrace();
        }
    }

    public static void savePlayer2() {
        try {
            Player[] players = Game.getPlayers();
            Player player = players[1];
            FileWriter fileWriter = new FileWriter("src/main/java/com/TiyangAlit/Game/Config/player2.txt");
            fileWriter.write(player.getUang() + "\n");
            fileWriter.write(player.getDeckPasif().getSize() + "\n");
            fileWriter.write(player.getDeckAktif().getSize() + "\n");

            StringBuilder result = new StringBuilder();
            int rowIndex = 0;
            int column = 1;
            for (int i = 0; i < player.getDeckAktif().getSize(); i++) {
                char row = (char) ('A' + rowIndex);
                result.append(row)
                        .append(String.format("%02d", column))
                        .append(" ")
                        .append(player.getDeckAktif().getDeck().get(i).getNama().toUpperCase().replace(" ", "_"))
                        .append("\n");
                rowIndex++;
            }
            fileWriter.write(result.toString());
            fileWriter.write(player.getLadang().getJumlahKartu() + "\n");
            fileWriter.write(player.getLadang().getLocationCardNameBobotItemsApplied());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing non-empty product types");
            e.printStackTrace();
        }
    }

    public static void simpan(){
        gamestate();
        savePlayer1();
        savePlayer2();
    }



}