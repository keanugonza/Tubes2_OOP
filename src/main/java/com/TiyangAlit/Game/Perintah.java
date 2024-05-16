package com.TiyangAlit.Game;

import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Player.Player;

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

        // TODO: Efek item
        // TODO: Serangan beruang
    }

    public void PLACE(Game game, int row, int col, Kartu kartu) {
        // TODO: Implement
    }
}
