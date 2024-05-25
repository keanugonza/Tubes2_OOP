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

    public static int parsePosition(String pos) {
        if (pos.length() != 3) {
            throw new IllegalArgumentException("Invalid position format: " + pos);
        }

        char row = pos.charAt(0);
        if (row < 'A' || row > 'Z') {
            throw new IllegalArgumentException("Invalid row character: " + row);
        }

        int rowIndex = row - 'A';

        int columnIndex;
        try {
            columnIndex = Integer.parseInt(pos.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid column number: " + pos.substring(1));
        }

        if (columnIndex != 0) {
            throw new IllegalArgumentException("Column number must be 01: " + pos.substring(1));
        }

        return rowIndex;
    }

    public void addKartuToPosisi(Kartu kartu, String pos) throws DeckFullException {
        // Parse the position string
        int index = parsePosition(pos);

        if (index < 0 || index > this.deck.size()) {
            throw new IndexOutOfBoundsException("Invalid position: " + pos);
        }

        if (this.deck.size() == this.maxSize) {
            throw new DeckFullException("Deck sudah penuh.");
        }

        this.deck.add(index, kartu);
    }

    public void removeKartu(Kartu kartu) { this.deck.remove(kartu); }

    public void removeKartuByIdx(int idx) { this.deck.remove(idx); }

    public boolean deckContains(Kartu kartu) { return this.deck.contains(kartu); }

    public boolean isFull() { return this.deck.size() == this.maxSize; }

    public int getKartuCount() {
        return this.deck.size();
    }

    public ArrayList<Kartu> getAllKartu() { return new ArrayList<>(this.deck); }

    public void displayDeck() {
        for (Kartu kartu : this.deck)
            System.out.print(kartu.getNama() + ", ");
        System.out.println();
    }

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