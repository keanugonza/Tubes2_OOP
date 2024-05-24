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
    public Player[] getPlayers() { return players; }
    public int getCurrentPlayerIdx() { return currentPlayerIdx; }
    public Player getCurrentPlayer() { return currentPlayer; }
    public Player getEnemyPlayer() { return (players[currentPlayerIdx == 0 ? 1 : 0]); }
    public int getTurnCnt() { return turnCnt; }
    public Toko getToko() { return toko; }

    public void setCurrentPlayerIdx(int val) { currentPlayerIdx = val; }
    public void setCurrentPlayer(Player val) { currentPlayer = val; }
    public void incTurnCnt() { turnCnt++; }
}
