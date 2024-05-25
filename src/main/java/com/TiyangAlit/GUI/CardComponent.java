package com.TiyangAlit.GUI;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.Ladang;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.util.List;
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

    public CardComponent(String imageURL, String description, Text Player1Coin, Text Player2Coin, GridPane GridLadang, GridPane GridDeck, GridPane GridShuffle, Ladang ladang, Deck deck, Kartu kartu, int row, int col, boolean isToko, boolean isDeck, List<Kartu> shuffleResult) {

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

        if(shuffleResult != null){
            this.hoverProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue){
                    this.setStyle("-fx-background-color: green");
                } else{
                    this.setStyle("-fx-background-color: skyblue");
                }
            });
        }

        if(GridShuffle == null){
            this.setOnMousePressed(e ->{
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
                    Stage onTop = new Stage(StageStyle.TRANSPARENT);
                    Window owner = this.getScene().getWindow();
                    onTop.setY(owner.getY() + 400);
                    onTop.setX(owner.getX() + 200);
                    onTop.initOwner(owner);
                    onTop.initModality(Modality.APPLICATION_MODAL);
                    owner.getScene().getRoot().setEffect(new GaussianBlur());

                    onTop.focusedProperty().addListener((observable, oldValue, newValue) -> {
                        if(!newValue){
                            onTop.close();
                            owner.getScene().getRoot().setEffect(null);
                        }
                    });

                    AnchorPane newPane = new AnchorPane();
                    newPane.setPrefHeight(248);
                    newPane.setPrefWidth(500);
                    newPane.setStyle("-fx-border-color: black; -fx-border-radius: 2;");

                    Button newButton = new Button();
                    newButton.setText("Kembali");
                    newButton.setFont(new Font("Continuum Medium", 15));
                    newButton.setLayoutX(23);
                    newButton.setLayoutY(15);
                    newButton.setOnMouseClicked(ev -> {
                        onTop.close();
                        owner.getScene().getRoot().setEffect(null);
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
                                    GridController.FillDeck(GridLadang, GridDeck, Player1Coin, Player2Coin, ladang, deck);
                                    GridController.FillLadang(GridLadang, GridDeck, Player1Coin, Player2Coin, ladang, deck);
                                    owner.getScene().getRoot().setEffect(null);
                                    onTop.close();
                                } catch (Exception ex) {
                                    VBox newPane2 = new VBox(5);
                                    newPane2.setPrefHeight(140);
                                    newPane2.setPrefWidth(500);
                                    newPane2.setStyle("-fx-border-color: black; -fx-border-radius: 2;");

                                    Label label = new Label(ex.getMessage());
                                    label.setFont(new Font("Continuum Medium", 20));
                                    label.setWrapText(true);
                                    label.setTextAlignment(TextAlignment.valueOf("CENTER"));
                                    newPane2.setAlignment(Pos.CENTER);
                                    newPane2.getChildren().add(label);

                                    Scene newScene = new Scene(newPane2);
                                    onTop.setScene(newScene);
                                    onTop.setResizable(false);
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
                            newButton2.setFont(new Font("Continuum Medium", 15));
                            newButton2.setOnMouseClicked( event -> {
                                try {
                                    MainGUI.currentPlayer.jual(this.kartu, Game.getToko());
                                    GridController.FillDeck(GridLadang, GridDeck, Player1Coin, Player2Coin,ladang, deck);
                                    GridController.FillLadang(GridLadang, GridDeck,Player1Coin, Player2Coin, ladang, deck);
                                    Player1Coin.setText(String.valueOf(MainGUI.currentPlayer.getUang()));
                                    Player2Coin.setText(String.valueOf(MainGUI.enemyPlayer.getUang()));
                                    onTop.close();
                                    owner.getScene().getRoot().setEffect(null);
                                } catch (Exception ex) {
                                    try {
                                        onTop.close();
                                        SceneController.Popup(event, owner, ex.getMessage());
                                    } catch (IOException exc) {
                                        System.out.println(exc.getMessage());
                                    }
                                }
                            });
                            if(this.kartu instanceof Produk){
                                newPane.getChildren().add(newButton2);
                            }
                        }
                    }


                    Text newText = new Text(this.description);
                    newText.setFont(new Font("Continuum Medium", 25));

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
                    newText2.setFont(new Font("Continuum Medium", 17));
                    newText2.setLayoutX(23);
                    newText2.setLayoutY(110);
                    newText2.setWrappingWidth(290);

                    Text newText3 = new Text();
                    if(this.kartu instanceof Entity){
                        newText3 = new Text("Efek: " + ((Entity) this.kartu).displayEffect());
                    }
                    newText3.setFont(new Font("Continuum Medium", 17));
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
