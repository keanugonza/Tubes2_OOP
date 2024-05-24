package com.TiyangAlit.Game;

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

    public static void setCurrentPlayerIdx(int val) { currentPlayerIdx = val; }
    public static void setCurrentPlayer(Player val) { currentPlayer = val; }
    public static void incTurnCnt() { turnCnt++; }
}
