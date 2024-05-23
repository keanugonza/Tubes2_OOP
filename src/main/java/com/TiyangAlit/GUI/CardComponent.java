package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Ladang.Ladang;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class CardComponent extends AnchorPane {
    public String imageURL;
    public String description;
    public ImageView image;
    public Text text;
    public double startX;
    public double startY;
    public int col;
    public int row;
    public Kartu kartu;

    public CardComponent(String imageURL, String description, GridPane GridLadang, GridPane GridDeck, Ladang ladang, Deck deck, Kartu kartu, int row, int col) {

        this.description = description;
        this.kartu = kartu;

        this.prefHeight(80);
        this.prefWidth(66);
        this.setStyle("-fx-border-color: black; -fx-border-radius: 15; -fx-background-color: lightgray; -fx-background-radius: 15;");
        this.maxHeight(80);
        this.maxWidth(66);

        this.imageURL = imageURL;
        this.image = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(imageURL))));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
        this.image.setLayoutX(5);
        this.image.setLayoutY(10);
        this.getChildren().add(this.image);


        this.text =  new Text(description);
        this.text.setLayoutX(15);
        this.text.setLayoutY(105);
        this.text.setTextAlignment(TextAlignment.valueOf("CENTER"));
        this.text.setWrappingWidth(50);
        this.getChildren().add(this.text);

        this.setOnMousePressed(e ->{
            this.toFront();
            this.startX = e.getSceneX() - this.getTranslateX();
            this.startY = e.getSceneY() - this.getTranslateY();
        });

        this.setOnMouseDragged(e ->{
            this.setTranslateX(e.getSceneX() - this.startX);
            this.setTranslateY(e.getSceneY() - this.startY);
        });

//        if(ladang != null){
//            this.setOnMouseReleased(e -> {
//                System.out.println("Ladang");
//                int[] Position = new GridController().getColRowFromPosition(GridLadang, e.getSceneX(), e.getSceneY());
//                int finalRowCount = Position[0];
//                int finalColCount = Position[1];
//                if( finalRowCount == -1 || finalColCount == -1 ){
//                    this.setTranslateX(0);
//                    this.setTranslateY(0);
//                } else{
//                    try{
//                        ladang.place(finalRowCount,finalColCount,this.kartu);
//                        ladang.remove(row, col);
//                        GridController.FillLadang(GridLadang,GridDeck, ladang, deck);
//                        ladang.displayLadang();
//                    } catch(Exception ex){
//                        this.setTranslateX(0);
//                        this.setTranslateY(0);
//                    }
//                }
//            });
//        }
//        if(deck != null){
//            this.setOnMouseReleased(e -> {
//                System.out.println("Deck");
//                int[] Position = new GridController().getColRowFromPosition(GridLadang, e.getSceneX(), e.getSceneY());
//                int finalRowCount = Position[0];
//                int finalColCount = Position[1];
//                System.out.println("finalRowCount: " + finalRowCount);
//                System.out.println("finalColCount: " + finalColCount);
//                if( finalRowCount == -1 || finalColCount == -1 ){
//                    this.setTranslateX(0);
//                    this.setTranslateY(0);
//                } else{
//                    try{
//                        ladang.place(finalRowCount,finalColCount,this.kartu);
//                        deck.removeKartu(this.kartu);
//                        GridController.FillLadang(GridLadang, GridDeck, ladang, deck);
//                        GridController.FillDeck(GridLadang, GridDeck, ladang, deck);
//                        ladang.displayLadang();
//                        deck.displayDeck();
//                    } catch(Exception ex){
//                        this.setTranslateX(0);
//                        this.setTranslateY(0);
//                        System.out.println(ex.getMessage());
//                    }
//                }
//            });
//        }

    }
}
