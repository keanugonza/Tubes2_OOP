package com.TiyangAlit.Game;

import com.TiyangAlit.Player.Player;

import java.util.Scanner;

public class Game {
    /*
     *  ATTRIBUTES
     */
    private final Player[] players = new Player[]{
            new Player("player1"),
            new Player("player2"),
    };
    private int currentPlayerIdx;
    private Player currentPlayer;
    private int turnCnt;

    /*
     *  ATTRIBUTES
     */
    public Game() {
        this.currentPlayerIdx = 0;
        this.currentPlayer = this.players[this.currentPlayerIdx];
        this.turnCnt = 1;
    }

    // Getter & Setter
    public Player[] getPlayers() { return this.players; }
    public int getCurrentPlayerIdx() { return this.currentPlayerIdx; }
    public Player getCurrentPlayer() { return this.currentPlayer; }
    public int getTurnCnt() { return this.turnCnt; }

    public void setCurrentPlayerIdx(int currentPlayerIdx) { this.currentPlayerIdx = currentPlayerIdx; }
    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }
    public void incTurnCnt() { this.turnCnt++; }

    // Baca perintah
    public void start() {
        Scanner scanner = new Scanner(System.in);  // Testing, ganti kalo udah ada UI

        Perintah perintah = new Perintah();
        while (this.turnCnt <= 20) {
            String input = scanner.nextLine();
            if (input.equals("NEXT"))
                perintah.NEXT(this);
        }
    }
}
