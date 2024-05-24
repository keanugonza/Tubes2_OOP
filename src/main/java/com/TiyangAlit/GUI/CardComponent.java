package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import com.TiyangAlit.Player.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public CardComponent(String imageURL, String description, GridPane GridLadang, GridPane GridDeck, Ladang ladang, Deck deck, Kartu kartu, int row, int col, boolean isToko, boolean isDeck) {

        this.description = description;
        this.kartu = kartu;
        this.cancelDrag = false;

        this.prefHeight(80);
        this.prefWidth(66);
        this.maxHeight(80);
        this.maxWidth(66);

        this.imageURL = imageURL;
        this.image = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(imageURL))));
        this.image.setFitHeight(70);
        this.image.setFitWidth(70);
        this.image.setLayoutX(12);
        this.image.setLayoutY(10);
        this.getChildren().add(this.image);


        this.text =  new Text(description);
        this.text.setLayoutX(4);
        this.text.setLayoutY(95);
        this.text.setTextAlignment(TextAlignment.valueOf("CENTER"));
        this.text.setWrappingWidth(82);
        this.getChildren().add(this.text);

        if(!isToko){
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

                    if(ladang.getPemilikLadang().equals(Game.getCurrentPlayer())){
                            Button newButton2 = new Button();
                            newButton2.setLayoutX(222);
                            newButton2.setLayoutY(196);
                        if(!isDeck){
                            newButton2.setText("Panen");
                            newButton2.setOnMouseClicked( event -> {
                                try {
                                    MainGUI.currentPlayer.panen(row,col);
                                    GridController.FillDeck(GridLadang, GridDeck, ladang, deck);
                                    GridController.FillLadang(GridLadang, GridDeck, ladang, deck);
                                    onTop.close();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            });
                            if(this.kartu instanceof Entity){
                                if(!((Entity) this.kartu).getStatus()){
                                    newButton2.setDisable(true);
                                }
                            }
                            newPane.getChildren().add(newButton2);
                        } else{
                            newButton2.setText("Jual");
                            newButton2.setOnMouseClicked( event -> {
                                try {
                                    MainGUI.currentPlayer.jual(this.kartu, Game.getToko());
                                    GridController.FillDeck(GridLadang, GridDeck, ladang, deck);
                                    GridController.FillLadang(GridLadang, GridDeck, ladang, deck);
                                    onTop.close();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            });
                            if(this.kartu instanceof Produk){
                                newPane.getChildren().add(newButton2);
                            }
                        }
                    }


                    Text newText = new Text(this.description);
                    newText.setFont(new Font("Arial", 20));

                    TextFlow textFlow = new TextFlow();
                    textFlow.setPrefWidth(500);
                    textFlow.setLayoutY(30);
                    textFlow.setTextAlignment(TextAlignment.valueOf("CENTER"));
                    textFlow.getChildren().add(newText);

                    Text newText2 = new Text();
                    if(this.kartu instanceof Entity){
                        if(this.kartu instanceof Tanaman){
                            newText2 = new Text("Umur: " +((Entity) this.kartu).displayBobot());
                        } else{
                            newText2 = new Text("Berat : " + ((Entity) this.kartu).displayBobot());
                        }
                    }
                    newText2.setLayoutX(23);
                    newText2.setLayoutY(110);
                    newText2.setWrappingWidth(290);

                    Text newText3 = new Text();
                    if(this.kartu instanceof Entity){
                        newText3 = new Text("Efek: " + ((Entity) this.kartu).displayEffect());
                    }
                    newText3.setLayoutX(23);
                    newText3.setLayoutY(140);
                    newText3.setWrappingWidth(310);


                    ImageView newImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(this.imageURL))));
                    newImage.setFitHeight(121);
                    newImage.setFitWidth(116);
                    newImage.setLayoutX(362);
                    newImage.setLayoutY(66);

                    newPane.getChildren().add(newButton);
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
}
