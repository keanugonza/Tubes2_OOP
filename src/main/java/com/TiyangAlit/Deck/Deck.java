package com.TiyangAlit.Deck;

import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Deck.Jenis.DeckPasif;
import com.TiyangAlit.Kartu.Kartu;

import java.util.*;
import java.util.ArrayList;

public abstract class Deck {
    /*
     *  ATTRIBUTES
     */
    private ArrayList<Kartu> deck;
    private final int maxSize;

    /*
     *  METHODS
     */
    public Deck(int maxSize) {
        this.deck = new ArrayList<>();
        this.maxSize = maxSize;
    }

    // Getter & Setter
    public ArrayList<Kartu> getDeck() { return this.deck; }
    public int getMaxSize() { return this.maxSize; }
    public int getSize() { return this.deck.size(); }

    public void setSize(int size) {
//        this.getSize() = size;
    }

    public void addKartu(Kartu kartu) throws DeckFullException {
        if (this.deck.size() == this.maxSize)
            throw new DeckFullException("Deck sudah penuh.");
        this.deck.add(kartu);
    }

    public void addKartu(Kartu kartu, int amt) throws DeckFullException {
        if (this.deck.size() + amt > this.maxSize)
            throw new DeckFullException("Deck sudah penuh.");

        for (int i = 0; i < amt; i++)
            this.deck.add(kartu);
    }

    public void removeKartu(Kartu kartu) { this.deck.remove(kartu); }

    public void removeKartuByIdx(int idx) { this.deck.remove(idx); }

    // Lain-lain
    public boolean deckContains(Kartu kartu) { return this.deck.contains(kartu); }

    public boolean isFull() { return this.deck.size() == this.maxSize; }

    public int getKartuCount() {
        return this.deck.size();
    }

    // Get all Kartu in the deck
    public ArrayList<Kartu> getAllKartu() { return new ArrayList<>(this.deck); }

    // TESTING
    public void displayDeck() {
        for (Kartu kartu : this.deck)
            System.out.print(kartu.getNama() + ", ");
        System.out.println();
    }

    // Get location and card name
    public String getLocationAndCardName() {
        StringBuilder result = new StringBuilder();
        int rowIndex = 0;
        int column = 1;
        for (int i = 0; i < this.deck.size(); i++) {
            char row = (char) ('A' + rowIndex);
            result.append(row)
                    .append(String.format("%02d", column))
                    .append(" ")
                    .append(this.deck.get(i).getNama())
                    .append("\n");
            rowIndex++;
        }
        return result.toString();
    }

}