package com.TiyangAlit.Game;

import com.TiyangAlit.Game.Muat.Muat;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

public class Game {
    /*
     *  ATTRIBUTES
     */
    private static final Player[] players = new Player[]{
            new Player("player1"),
            new Player("player2"),
    };
    private static int currentPlayerIdx = 0;
    private static Player currentPlayer = players[currentPlayerIdx];
    private static int turnCnt = 1;
    private static final Toko toko = new Toko();

    /*
     *  METHODS
     */

    // Getter & Setter
    public static Player[] getPlayers() { return players; }
    public static int getCurrentPlayerIdx() { return currentPlayerIdx; }
    public static Player getCurrentPlayer() { return currentPlayer; }
    public static Player getEnemyPlayer() { return (players[currentPlayerIdx == 0 ? 1 : 0]); }
    public static int getTurnCnt() { return turnCnt; }
    public static Toko getToko() { return toko; }
    public static void setTurnCnt(int count) {
        turnCnt = count;
    }

    public static void setCurrentPlayerIdx(int val) { currentPlayerIdx = val; }
    public static void setCurrentPlayer(Player val) { currentPlayer = val; }
    public static void incTurnCnt() { turnCnt++; }

    // Perintah
    public static void NEXT() {
        // Ganti giliran
        setCurrentPlayerIdx(currentPlayerIdx == 0 ? 1 : 0);
        setCurrentPlayer(players[currentPlayerIdx]);
        incTurnCnt();

        // Tambah umur tanaman
        for (Player player : players)
            player.getLadang().tambahUmur();
    }

    public static void MUAT() {
        Muat.muat_gameState();
    }
}
