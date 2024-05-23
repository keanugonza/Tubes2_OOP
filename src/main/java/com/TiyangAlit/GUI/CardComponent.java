package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Ladang.Ladang;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public boolean cancelDrag;

    public CardComponent(String imageURL, String description, GridPane GridLadang, GridPane GridDeck, Ladang ladang, Deck deck, Kartu kartu, int row, int col) {

        this.description = description;
        this.kartu = kartu;
        this.cancelDrag = false;

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
        this.text.setWrappingWidth(65);
        this.getChildren().add(this.text);

        this.setOnMousePressed(e ->{
            System.out.println("Pressed");
            this.toFront();
            this.cancelDrag = false;
            this.startX = e.getSceneX() - this.getTranslateX();
            this.startY = e.getSceneY() - this.getTranslateY();
        });

        this.setOnMouseDragged(e ->{
            this.cancelDrag = true;
            this.setTranslateX(e.getSceneX() - this.startX);
            this.setTranslateY(e.getSceneY() - this.startY);
        });

        this.setOnMouseClicked(e -> {
            if(!this.cancelDrag) {
                Stage onTop = new Stage();
                onTop.initOwner(this.getScene().getWindow());
                onTop.initModality(Modality.WINDOW_MODAL);

                AnchorPane newPane = new AnchorPane();
                newPane.setPrefHeight(248);
                newPane.setPrefWidth(500);
                newPane.setStyle("-fx-border-color: black; -fx-border-radius: 2;");

                Button newButton = new Button();
                newButton.setText("Kembali");
                newButton.setLayoutX(23);
                newButton.setLayoutY(15);
                newButton.setOnMouseClicked(ev -> {
                    onTop.close();
                });

                Button newButton2 = new Button();
                newButton2.setText("Panen");
                newButton2.setLayoutX(222);
                newButton2.setLayoutY(196);

                Text newText = new Text(this.description);
                newText.setFont(new Font("Arial", 20));

                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(500);
                textFlow.setLayoutY(30);
                textFlow.setTextAlignment(TextAlignment.valueOf("CENTER"));
                textFlow.getChildren().add(newText);

                Text newText2 = new Text("Berat : 5 (15)");
                newText2.setLayoutX(23);
                newText2.setLayoutY(110);
                newText2.setWrappingWidth(290);

                Text newText3 = new Text("Item Aktif : Accelerate (1), Delay(1), Protect(1), Trap(1)");
                newText3.setLayoutX(23);
                newText3.setLayoutY(140);
                newText3.setWrappingWidth(310);


                ImageView newImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(this.imageURL))));
                newImage.setFitHeight(121);
                newImage.setFitWidth(116);
                newImage.setLayoutX(362);
                newImage.setLayoutY(66);

                newPane.getChildren().add(newButton);
                newPane.getChildren().add(newButton2);
                newPane.getChildren().add(textFlow);
                newPane.getChildren().add(newText2);
                newPane.getChildren().add(newText3);
                newPane.getChildren().add(newImage);


                Scene newScene = new Scene(newPane);
                onTop.setScene(newScene);
                onTop.setResizable(false);
                onTop.showAndWait();
            }
        });

    }
}
