package com.TiyangAlit.Deck;

import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Kartu.Kartu;

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

    // Lain-lain
    public boolean deckContains(Kartu kartu) { return this.deck.contains(kartu); }

    public boolean isFull() { return this.deck.size() == this.maxSize; }

    // TESTING
    public void displayDeck() {
        for (Kartu kartu : this.deck)
            System.out.print(kartu.getNama() + ", ");
        System.out.println();
    }
}