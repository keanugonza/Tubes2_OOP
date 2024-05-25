package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Ladang.Matrix;
import com.TiyangAlit.Toko.Toko;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GridController {

    public Matrix<Double> XConstraint;
    public Matrix<Double> YConstraint;

    public GridController() {
        this.XConstraint = new Matrix<Double>(5,2);
        this.YConstraint = new Matrix<Double>(4,2);
        // X CONSTRAINT
        this.XConstraint.setEl(0,0,75.0);
        this.XConstraint.setEl(0,1, 165.0);
        this.XConstraint.setEl(1,0, 170.0);
        this.XConstraint.setEl(1,1, 260.0);
        this.XConstraint.setEl(2,0, 265.0);
        this.XConstraint.setEl(2,1, 355.0);
        this.XConstraint.setEl(3,0, 360.0);
        this.XConstraint.setEl(3,1, 450.0);
        this.XConstraint.setEl(4,0, 455.0);
        this.XConstraint.setEl(4,1, 546.0);

        // Y CONSTRAINT
        this.YConstraint.setEl(0,0,162.0);
        this.YConstraint.setEl(0,1,292.0);
        this.YConstraint.setEl(1,0,299.0);
        this.YConstraint.setEl(1,1,429.0);
        this.YConstraint.setEl(2,0,435.0);
        this.YConstraint.setEl(2,1,565.0);
        this.YConstraint.setEl(3,0,571.0);
        this.YConstraint.setEl(3,1,701.0);
    }

    public int[] getColRowFromPosition(GridPane Grid, double XPos, double YPos){
        int row = -1;
        int col = -1;


        for(int i = 0; i < 5; i++){
            if(this.XConstraint.getEl(i,0) < XPos && this.XConstraint.getEl(i,1) > XPos){
                col = i;
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(this.YConstraint.getEl(i,0) < YPos && this.YConstraint.getEl(i,1) > YPos){
                row = i;
                break;
            }
        }

        return new int[]{row, col};
    }

    public static void FillLadang(GridPane GridLadang, GridPane GridDeck, Text Player1Coin, Text Player2Coin, Ladang ladang, Deck deck){
        ladang.displayLadang();
        GridLadang.getChildren().clear();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                if(ladang.isFullSlot(i,j)){
                    final int temprow = i;
                    final int tempcol = j;
                    Entity card = ladang.getData().getEl(i, j);
                    CardComponent newCard = new CardComponent(card.getImage(), card.getNama(), Player1Coin, Player2Coin, GridLadang, GridDeck, null, ladang, deck, card, i,j, false,false, null);
                    newCard.setOnMouseReleased(e -> {
                        int[] Position = new GridController().getColRowFromPosition(GridLadang, e.getSceneX(), e.getSceneY());
                        int finalRowCount = Position[0];
                        int finalColCount = Position[1];
                        if( finalRowCount == -1 || finalColCount == -1 ){
                            newCard.setTranslateX(0);
                            newCard.setTranslateY(0);
                        } else{
                            try{
                                MainGUI.currentPlayer.place(finalRowCount,finalColCount,newCard.kartu, ladang);
                                ladang.remove(temprow, tempcol);
                                GridController.FillLadang(GridLadang,GridDeck, MainGUI.controlerHome.player1Coin, MainGUI.controlerHome.player2Coin, ladang, deck);
                                ladang.displayLadang();
                            } catch(Exception ex){
                                newCard.setTranslateX(0);
                                newCard.setTranslateY(0);
                                if(finalRowCount != temprow || finalColCount != tempcol){
                                    try {
                                        SceneController.Popup(e, GridLadang.getScene().getWindow(), ex.getMessage());
                                    } catch (IOException exc) {
                                        System.out.println(exc.getMessage());
                                    }
                                }
                            }
                        }
                    });
                    GridLadang.add(newCard, j,i);
                }
            }
        }
    }

    public static void FillDeck(GridPane GridLadang, GridPane GridDeck, Text Player1Coin, Text Player2Coin, Ladang ladang, Deck deck){
        deck.displayDeck();
        GridDeck.getChildren().clear();
        for(int i = 0; i < deck.getSize(); i++){
            try{
                Kartu card =  deck.getDeck().get(i);
                CardComponent newCard = new CardComponent(card.getImage(), card.getNama(),Player1Coin, Player2Coin, GridLadang, GridDeck, null, ladang, deck, card, i, 0, false,true, null);
                GridDeck.add(newCard, i,0);
                if(GridLadang != null){
                    newCard.setOnMouseReleased(e -> {
                        int[] Position = new GridController().getColRowFromPosition(GridLadang, e.getSceneX(), e.getSceneY());
                        int finalRowCount = Position[0];
                        int finalColCount = Position[1];
                        if( finalRowCount == -1 || finalColCount == -1 ){
                            newCard.setTranslateX(0);
                            newCard.setTranslateY(0);
                        } else{
                            try{
                                deck.removeKartu(newCard.kartu);
                                MainGUI.currentPlayer.place(finalRowCount,finalColCount,newCard.kartu, ladang);
                            } catch(Exception ex){
                                try {
                                    deck.addKartu(newCard.kartu);
                                } catch (Exception ignored) { }
                                newCard.setTranslateX(0);
                                newCard.setTranslateY(0);
                                try {
                                    SceneController.Popup(e, GridLadang.getScene().getWindow(), ex.getMessage());
                                } catch (IOException exc) {
                                    System.out.println(exc.getMessage());
                                }
                            }
                            GridController.FillLadang(GridLadang, GridDeck, Player1Coin, Player2Coin, ladang, deck);
                            GridController.FillDeck(GridLadang, GridDeck, Player1Coin, Player2Coin, ladang, deck);
                            ladang.displayLadang();
                            deck.displayDeck();
                        }
                    });
                } else{
                    newCard.setOnMouseReleased(e -> {
                        newCard.setTranslateX(0);
                        newCard.setTranslateY(0);
                    });
                }

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static int[] indexToColRow(int i, int colMax){
        int col = i % colMax;
        int row = i / colMax;
        return new int[]{row, col};
    }

    public static void FillToko(GridPane GridToko){
        int i = 0;
        GridToko.getChildren().clear();
        Toko toko = Game.getToko();
        for(Map.Entry<Produk, Integer> entry: toko.getData().entrySet()){
            Produk currProduk = entry.getKey();
            int[] ColRow = GridController.indexToColRow(i, 3);
            int row = ColRow[0];
            int col = ColRow[1];
            CardComponent newCard = new CardComponent(currProduk.getImage(), currProduk.getNama(), MainGUI.controlerHome.player1Coin , MainGUI.controlerHome.player2Coin , GridToko, null, null, null, null, currProduk, row, col, true,false, null);
            ShopComponent newshopcard = new ShopComponent(newCard);
            GridToko.add(newshopcard, col, row);
            i++;
        }
    }

    public static void FillShuffle(Window owner, Stage stage, GridPane GridLadang, GridPane GridDeck, GridPane GridShuffle, List<Kartu> shuffleResult){
        int i = 0;
        GridShuffle.getChildren().clear();
        for(Kartu kartu : shuffleResult){
            int[] ColRow = GridController.indexToColRow(i, 2);
            int row = ColRow[0];
            int col = ColRow[1];
            System.out.println(row);
            System.out.println(col);
            CardComponent newCard = new CardComponent(kartu.getImage(), kartu.getNama(), MainGUI.controlerHome.player1Coin, MainGUI.controlerHome.player2Coin, null, GridDeck, GridShuffle,null, MainGUI.currentPlayer.getDeckAktif(), kartu, row, col, false,false, shuffleResult);
            newCard.setOnMouseClicked(e -> {
                try {
                    MainGUI.currentPlayer.moveFromShuffle_to_Aktif(shuffleResult, kartu);
                    System.out.println("nahhhhh" + shuffleResult.size());
                    MainGUI.currentPlayer.getDeckAktif().displayDeck();
                    GridController.FillShuffle(owner, stage, GridLadang, GridDeck, GridShuffle, shuffleResult);
                    GridController.FillDeck(GridLadang,GridDeck, MainGUI.controlerHome.player1Coin, MainGUI.controlerHome.player2Coin, MainGUI.ladangPlayer, MainGUI.deckPlayer);
                    if(MainGUI.currentPlayer.getDeckAktif().isFull() || shuffleResult.isEmpty()){
                        stage.close();
                        owner.getScene().getRoot().setEffect(null);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
            GridShuffle.add(newCard, col, row);
            i++;
        }
    }
}
