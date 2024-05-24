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

    public static void FillLadang(GridPane GridLadang, GridPane GridDeck, Ladang ladang, Deck deck){
        ladang.displayLadang();
        GridLadang.getChildren().clear();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                if(ladang.isFullSlot(i,j)){
                    final int temprow = i;
                    final int tempcol = j;
                    Entity card = ladang.getData().getEl(i, j);
                    CardComponent newCard = new CardComponent(card.getImage(), card.getNama(), GridLadang, GridDeck, ladang, deck, card, i,j, false, false);
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
                                GridController.FillLadang(GridLadang,GridDeck, ladang, deck);
                                ladang.displayLadang();
                            } catch(Exception ex){
                                newCard.setTranslateX(0);
                                newCard.setTranslateY(0);
                            }
                        }
                    });
                    GridLadang.add(newCard, j,i);
                }
            }
        }
    }

    public static void FillDeck(GridPane GridLadang, GridPane GridDeck, Ladang ladang, Deck deck){
        deck.displayDeck();
        GridDeck.getChildren().clear();
        for(int i = 0; i < deck.getSize(); i++){
            try{
                Kartu card =  deck.getDeck().get(i);
                CardComponent newCard = new CardComponent(card.getImage(), card.getNama(), GridLadang, GridDeck, ladang, deck, card, i, 0, false, true);
                GridDeck.add(newCard, i,0);
                if(GridLadang != null){
                    newCard.setOnMouseReleased(e -> {
                        int[] Position = new GridController().getColRowFromPosition(GridLadang, e.getSceneX(), e.getSceneY());
                        int finalRowCount = Position[0];
                        int finalColCount = Position[1];
                        System.out.println("finalRowCount: " + finalRowCount);
                        System.out.println("finalColCount: " + finalColCount);
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
                                System.out.println("ini dari filldeck :" + ex.getMessage());
                            }
                            GridController.FillLadang(GridLadang, GridDeck, ladang, deck);
                            GridController.FillDeck(GridLadang, GridDeck, ladang, deck);
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

    public static int[] indexToColRow(int i){
        int col = i % 3;
        int row = i / 3;
        return new int[]{row, col};
    }

    public static void FillToko(GridPane GridToko){
        int i = 0;
        GridToko.getChildren().clear();
        Toko toko = Game.getToko();
        for(Map.Entry<Produk, Integer> entry: toko.getData().entrySet()){
            Produk currProduk = entry.getKey();
            int[] ColRow = GridController.indexToColRow(i);
            int row = ColRow[0];
            int col = ColRow[1];
            CardComponent newCard = new CardComponent(currProduk.getImage(), currProduk.getNama(), GridToko, null, null, null, currProduk, row, col, true, false);
            ShopComponent newshopcard = new ShopComponent(newCard);
            GridToko.add(newshopcard, col, row);
            i++;
            newCard.setOnMouseReleased(e -> {
                newCard.setTranslateX(0);
                newCard.setTranslateY(0);
            });
        }
    }
}
