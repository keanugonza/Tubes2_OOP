package com.TiyangAlit.GUI;

import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Toko.Toko;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ShopComponent extends AnchorPane {

    public CardComponent card;

    ShopComponent(CardComponent card) {

        this.setPrefHeight(161);
        this.setPrefWidth(181);
        this.setStyle("-fx-background-color: lightgray;");
        this.card = card;
        if(this.card.kartu instanceof Produk){
            Toko toko = Game.getToko();
            int hargaBenda = ((Produk) this.card.kartu).getHarga();
            int jumlahBenda = toko.getJumlah(this.card.kartu);
            Text hargaHeader = new Text("Harga");
            hargaHeader.setLayoutX(112);
            hargaHeader.setLayoutY(34);

            Text hargaText = new Text(String.valueOf(hargaBenda));
            hargaText.setLayoutX(112);
            hargaText.setLayoutY(52);

            Text jumlahHeader = new Text("Jumlah");
            jumlahHeader.setLayoutX(112);
            jumlahHeader.setLayoutY(86);

            Text jumlahText = new Text(String.valueOf(jumlahBenda));
            jumlahText.setLayoutX(112);
            jumlahText.setLayoutY(104);

            Button buyButton = new Button("Beli");
            buyButton.setLayoutX(102);
            buyButton.setLayoutY(120);
            buyButton.setOnMouseClicked(e -> {
                try {
                    MainGUI.currentPlayer.beli((Produk)this.card.kartu, toko);
                    SceneController.SwitchToShop(e);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });

            this.card.setLayoutX(10);
            this.card.setLayoutY(11);
            this.card.setPrefHeight(130);

            this.getChildren().add(hargaHeader);
            this.getChildren().add(hargaText);
            this.getChildren().add(jumlahHeader);
            this.getChildren().add(jumlahText);
            this.getChildren().add(buyButton);
            this.getChildren().add(this.card);
        }
    }
}
