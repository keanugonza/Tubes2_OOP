package com.TiyangAlit.Game;

import com.TiyangAlit.Game.GameExceptions.GameSelesaiException;
import com.TiyangAlit.Game.Muat.Muat;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

public class Game {
    /*
     *  ATTRIBUTES
     */
    private static Player[] players = new Player[]{
            new Player("player1"),
            new Player("player2"),
    };
    private static int currentPlayerIdx = 0;
    private static Player currentPlayer = players[currentPlayerIdx];
    private static int turnCnt = 1;
    private static Toko toko = new Toko();

    static {
        players[0].getDeckPasif().initDeck();
        players[1].getDeckPasif().initDeck();
    }

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
    public static void RESET() {
        players = new Player[]{
                new Player("player1"),
                new Player("player2"),
        };
        currentPlayerIdx = 0;
        currentPlayer = players[currentPlayerIdx];
        turnCnt = 1;
        toko = new Toko();
    }

    public static void NEXT() throws GameSelesaiException {
        // Ganti giliran
        setCurrentPlayerIdx(currentPlayerIdx == 0 ? 1 : 0);
        setCurrentPlayer(players[currentPlayerIdx]);
        incTurnCnt();

        // Tambah umur tanaman
        for (Player player : players)
            player.getLadang().tambahUmur();

        if (turnCnt == 21) {
            Player pemenang = STOP();
            throw new GameSelesaiException(pemenang.getNama() + "menang!");
        }
    }

    public static void MUAT() {
        RESET();

        Muat.muat_gameState();
        Muat.muat_player("player1");
        Muat.muat_player("player2");
    }

    public static Player STOP() {
        // Panggil kalo turnCnt == 21

        Player pemenang = null;
        if (players[0].getUang() > players[1].getUang())
            pemenang = players[0];
        else if (players[1].getUang() > players[0].getUang())
            pemenang = players[1];
        return pemenang;
    }
}
