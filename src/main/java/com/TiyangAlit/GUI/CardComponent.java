package com.TiyangAlit.GUI;

import com.TiyangAlit.Kartu.Kartu;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Objects;

public class CardComponent extends AnchorPane {
    public String imageURL;
    public Text description;
    public ImageView image;
    public double startX;
    public double startY;
    public Kartu kartu;

    public CardComponent(String imageURL, String description, GridPane Grid, int colCount, int rowCount) {
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


        this.description =  new Text(description);
        this.description.setLayoutX(15);
        this.description.setLayoutY(105);
        this.description.setTextAlignment(TextAlignment.valueOf("CENTER"));
        this.description.setWrappingWidth(59);
        this.getChildren().add(this.description);

        this.setOnMousePressed(e ->{
            this.toFront();
            this.startX = e.getSceneX() - this.getTranslateX();
            this.startY = e.getSceneY() - this.getTranslateY();
            System.out.println("-----------------------");
        });

        this.setOnMouseDragged(e ->{
            this.setTranslateX(e.getSceneX() - this.startX);
            this.setTranslateY(e.getSceneY() - this.startY);
        });


        this.setOnMouseReleased(e -> {
            int[] Position = new GridController().getColRowFromPosition(e.getSceneX(), e.getSceneY());
            int finalRowCount = Position[0];
            int finalColCount = Position[1];
            if( finalRowCount == -1 || finalColCount == -1 ){
                this.setTranslateX(0);
                this.setTranslateY(0);
            } else{
                Grid.getChildren().remove(this);
                Grid.add(new CardComponent("/StardewValley.png", "Daging Babi lah", Grid, colCount, rowCount), finalColCount, finalRowCount);
            }
        });
    }
}
