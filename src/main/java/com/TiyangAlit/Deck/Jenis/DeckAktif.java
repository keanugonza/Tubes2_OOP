package com.TiyangAlit.Deck.Jenis;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Kartu.Kartu;

import java.util.ArrayList;

public class DeckAktif extends Deck {
    /*
     *  ATTRIBUTES
     */

    /*
     *  METHODS
     */
    public DeckAktif() { super(6); }

    // Getter & Setter

    // Lain-lain
    public ArrayList<Kartu> shuffleKartu(DeckPasif deck) {
        // TODO: Implement

        return null;
    }

    public void finishShuffle(ArrayList<Kartu> kartuShuffle, DeckPasif deckPasif) {
        // Keluarin kartu dari deck pasif &
        // masukin kartu dari shuffleKartu ke deck aktif.
        // Asumsi: jumlah kartu dalam deck aktif + kartu shuffle <= 6.
        for (Kartu kartu : kartuShuffle) {
            try {
                deckPasif.removeKartu(kartu);
                addKartu(kartu);
            } catch (Exception ignored) { }
        }
    }
}
