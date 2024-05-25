package com.TiyangAlit.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.OmnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Game.Game;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora;
import com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.LadangExceptions.LadangInvalidIndexException;
import com.TiyangAlit.Player.Player;
import com.TiyangAlit.Toko.Toko;

public class Muat {
    private String FILE_NAME;

    public Muat (String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFILE_NAME() {
        return this.FILE_NAME;
    }

    public static void loadGameState(String FILE_NAME) {
        try {
            Toko toko = Game.getToko();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            // Read current turn count
            int currentTurn = Integer.parseInt(reader.readLine().trim());
            Game.setTurnCnt(currentTurn);

            // Read shop data count
            int shopItemCount = Integer.parseInt(reader.readLine().trim());


            // Read shop items and their quantities
            for (int i = 0; i < shopItemCount; i++) {
                String[] line = reader.readLine().split(" ", 2);
                if (line.length == 2) {
                    String productName = line[0];
                    if (productName.equals("Sirip Hiu") || productName.equals("Susu") || productName.equals("Daging Domba") || productName.equals("Daging Kuda") || productName.equals("Telur") || productName.equals("Daging Beruang")) {
                        KartuFactory produkHewanFactory = new ProdukHewanFactory();
                        Produk prod = (ProdukHewan) produkHewanFactory.createKartu(productName);
                        int quantity = Integer.parseInt(line[1].trim());
                        toko.getData().put(prod, quantity);
                    } else if (productName.equals("Jagung") || productName.equals("Labu") || productName.equals("Stroberi")) {
                        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
                        Produk prod = (ProdukTanaman) produkTanamanFactory.createKartu(productName);
                        int quantity = Integer.parseInt(line[1].trim());
                        toko.getData().put(prod, quantity);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading game state from " + FILE_NAME);
            e.printStackTrace();
        }
    }


    public static void loadPlayer(String FILE_NAME) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            Player player = Game.getCurrentPlayer();

            // BACA JUMLAH GULDEN
            int guldens = Integer.parseInt(reader.readLine().trim());
//            player.setUang(guldens);

            // BACA JUMLAH DECK
            int deckSize = Integer.parseInt(reader.readLine().trim());

            // BACA JUMLAH DECK AKTIV
            int activeDeckSize = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < activeDeckSize; i++) {
                // BACA LOKASI (A01) DAN NAMA KARTU
                String[] line = reader.readLine().split(" ", 2);
                if (line.length == 2) {
                    // AMBIL LOKASINYA
                    String location = line[0];

                    // AMBIL NAMA KARTUNYA
                    String cardName = line[1];

                    // Make new Kartu berdasarkan nama
                    // KARTU PRODUK HEWAN
                    if (cardName.equals("Sirip Hiu") || cardName.equals("Susu") || cardName.equals("Daging Domba") || cardName.equals("Daging Kuda") || cardName.equals("Telur") || cardName.equals("Daging Beruang")) {
                        KartuFactory produkHewanFactory = new ProdukHewanFactory();
                        Produk prod = (ProdukHewan) produkHewanFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    } //  KARTU PRODUK TANAMAN
                    else if (cardName.equals("Jagung") || cardName.equals("Labu") || cardName.equals("Stroberi")) {
                        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
                        Produk prod = (ProdukTanaman) produkTanamanFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU ITEMS
                    else if (cardName.equals("Accelerate") || cardName.equals("Delay") || cardName.equals("Instant Harvest") || cardName.equals("Destroy") || cardName.equals("Protect") || cardName.equals("Trap")) {
                        KartuFactory itemFactory = new ItemFactory();
                        Item prod = (Item) itemFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU TANAMAN
                    else if (cardName.equals("Biji Jagung") || cardName.equals("Biji Labu") || cardName.equals("Biji Stroberi")) {
                        KartuFactory tanamanFactory = new TanamanFactory();
                        Tanaman prod = (Tanaman) tanamanFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU HERBIVORA
                    else if (cardName.equals("Sapi") || cardName.equals("Domba") || cardName.equals("Kuda")) {
                        KartuFactory herbivoraFactory = new HerbivoraFactory();
                        Herbivora prod = (Herbivora) herbivoraFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU KARNIVORA
                    else if (cardName.equals("Hiu Darat")) {
                        KartuFactory karnivoraFactory = new KarnivoraFactory();
                        Karnivora prod = (Karnivora) karnivoraFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU OMNIVORA
                    else if (cardName.equals("Ayam") || cardName.equals("Beruang")) {
                        KartuFactory omnivoraFactory = new OmnivoraFactory();
                        Omnivora prod = (Omnivora) omnivoraFactory.createKartu(cardName);
                        try {
                            player.getDeckAktif().addKartuToPosisi(prod, location);
                        } catch (DeckFullException e) {
                            System.out.println("FULL NDES");
                        }
                    }
                }
            }

            // Read the amount of cards in the ladang
            int ladangSize = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < ladangSize; i++) {
                String[] line = reader.readLine().split(" ");
                if (line.length >= 4) {
                    String location = line[0];
                    if (location.length() != 3) {
                        throw new IllegalArgumentException("Invalid location format: " + location);
                    }
                    char rowChar = location.charAt(0);
                    if (rowChar < 'A' || rowChar > 'D') {
                        throw new IllegalArgumentException("Invalid row character: " + rowChar);
                    }
                    int row = rowChar - 'A';
                    int col;
                    try {
                        col = Integer.parseInt(location.substring(1)) - 1;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid column number: " + location.substring(1));
                    }
                    if (col < 0 || col >= 5) {
                        throw new IllegalArgumentException("Column number must be between 01 and 05: " + location.substring(1));
                    }

                    String cardName = line[1];
                    int umurOrBobot = Integer.parseInt(line[2]);

                    // KARTU TANAMAN
                    if (cardName.equals("Biji Jagung") || cardName.equals("Biji Labu") || cardName.equals("Biji Stroberi")) {
                        KartuFactory tanamanFactory = new TanamanFactory();
                        Tanaman prod = (Tanaman) tanamanFactory.createKartu(cardName);
                        try {
                            player.getLadang().addCardToLadang(location, prod, umurOrBobot);
                            int jumlahItem = Integer.parseInt(line[3]);
                            int itemIndex = 4;
                            // Read and add each item applied to the card in ladang
                            for (int j = 0; j < jumlahItem; j++) {
                                // Assuming the format is ITEM_NAME followed by ITEM_NAME_COUNT
                                String itemName = line[itemIndex++];
                                int itemCount = Integer.parseInt(line[itemIndex++]);
                                KartuFactory itemFactory = new ItemFactory();
                                Item item = (Item) itemFactory.createKartu(cardName);
                                // Assuming you have a method to apply items to cards in ladang
                                player.getLadang().placeItem(row, col, item);
                            }
                        } catch (Exception e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU HERBIVORA
                    else if (cardName.equals("Sapi") || cardName.equals("Domba") || cardName.equals("Kuda")) {
                        KartuFactory herbivoraFactory = new HerbivoraFactory();
                        Herbivora prod = (Herbivora) herbivoraFactory.createKartu(cardName);
                        try {
                            player.getLadang().addCardToLadang(location, prod, umurOrBobot);
                            int jumlahItem = Integer.parseInt(line[3]);
                            int itemIndex = 4;
                            // Read and add each item applied to the card in ladang
                            for (int j = 0; j < jumlahItem; j++) {
                                // Assuming the format is ITEM_NAME followed by ITEM_NAME_COUNT
                                String itemName = line[itemIndex++];
                                int itemCount = Integer.parseInt(line[itemIndex++]);
                                KartuFactory itemFactory = new ItemFactory();
                                Item item = (Item) itemFactory.createKartu(cardName);
                                // Assuming you have a method to apply items to cards in ladang
                                player.getLadang().placeItem(row, col, item);
                            }
                        } catch (Exception e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU KARNIVORA
                    else if (cardName.equals("Hiu Darat")) {
                        KartuFactory karnivoraFactory = new KarnivoraFactory();
                        Karnivora prod = (Karnivora) karnivoraFactory.createKartu(cardName);
                        try {
                            player.getLadang().addCardToLadang(location, prod, umurOrBobot);
                            int jumlahItem = Integer.parseInt(line[3]);
                            int itemIndex = 4;
                            // Read and add each item applied to the card in ladang
                            for (int j = 0; j < jumlahItem; j++) {
                                // Assuming the format is ITEM_NAME followed by ITEM_NAME_COUNT
                                String itemName = line[itemIndex++];
                                int itemCount = Integer.parseInt(line[itemIndex++]);
                                KartuFactory itemFactory = new ItemFactory();
                                Item item = (Item) itemFactory.createKartu(cardName);
                                // Assuming you have a method to apply items to cards in ladang
                                player.getLadang().placeItem(row, col, item);
                            }
                        } catch (Exception e) {
                            System.out.println("FULL NDES");
                        }
                    } // KARTU OMNIVORA
                    else if (cardName.equals("Ayam") || cardName.equals("Beruang")) {
                        KartuFactory omnivoraFactory = new OmnivoraFactory();
                        Omnivora prod = (Omnivora) omnivoraFactory.createKartu(cardName);
                        try {
                            player.getLadang().addCardToLadang(location, prod, umurOrBobot);
                            int jumlahItem = Integer.parseInt(line[3]);
                            int itemIndex = 4;
                            // Read and add each item applied to the card in ladang
                            for (int j = 0; j < jumlahItem; j++) {
                                // Assuming the format is ITEM_NAME followed by ITEM_NAME_COUNT
                                String itemName = line[itemIndex++];
                                int itemCount = Integer.parseInt(line[itemIndex++]);
                                KartuFactory itemFactory = new ItemFactory();
                                Item item = (Item) itemFactory.createKartu(cardName);
                                // Assuming you have a method to apply items to cards in ladang
                                player.getLadang().placeItem(row, col, item);
                            }
                        } catch (Exception e) {
                            System.out.println("FULL NDES");
                        }
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading player data from " + FILE_NAME);
            e.printStackTrace();
        }
    }



}
