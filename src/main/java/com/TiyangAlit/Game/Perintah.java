package com.TiyangAlit.Game;

import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

public class Perintah {
    public void NEXT(Game game) {
        Player[] players = game.getPlayers();
        int currentPlayerIdx = game.getCurrentPlayerIdx();

        // Ganti giliran
        if (currentPlayerIdx == 0)
            currentPlayerIdx = 1;
        else
            currentPlayerIdx = 0;
        game.setCurrentPlayerIdx(currentPlayerIdx);
        game.setCurrentPlayer(players[currentPlayerIdx]);
        game.incTurnCnt();

        // Tambah umur tanaman
        for (Player player : players)
            player.getLadang().tambahUmur();
    }

    // TAHAPAN SHUFFLE KARTU
    public void SHUFFLE(Game game) {
        Player currentPlayer = game.getCurrentPlayer();

        // TODO: Implement
    }

    // TAHAPAN AKSI BEBAS
    public void PLACE(Game game, int row, int col, Kartu kartu, Ladang ladang) throws Exception {
        Player currentPlayer = game.getCurrentPlayer();

        currentPlayer.place(row, col, kartu, ladang);
    }

    public void PANEN(Game game, int row, int col) throws Exception{
        Player currentPlayer = game.getCurrentPlayer();

        currentPlayer.panen(row, col);
    }

    public void BELI(Game game, Produk produk) throws Exception {
        Player currentPlayer = game.getCurrentPlayer();
        Toko toko = game.getToko();

        currentPlayer.beli(produk, toko);
    }

    public void JUAL(Game game, Produk produk) throws Exception {
        Player currentPlayer = game.getCurrentPlayer();
        Toko toko = game.getToko();

        currentPlayer.jual(produk, toko);
    }

    public void SIMPAN(Game game) {
        // TODO: Implement
    }

    public void MUAT(Game game) {
        // TODO: Implement
    }
}
