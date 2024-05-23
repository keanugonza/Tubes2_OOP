package com.TiyangAlit.Deck.Jenis;

import java.util.Scanner;
import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
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
    public void getFromDeckPasif(DeckPasif pasif, int idx) {
        Kartu sekopasif = pasif.getKartu(idx);
        try {
            this.addKartu(sekopasif);
            pasif.removeKartu(sekopasif);
        } catch (DeckFullException e) {
            System.out.println("FULL NDES");
        }
    }

    public int getSLotKosong(){
        return this.getMaxSize() - this.getSize();
    }

//    public void mlebuBarDikocok(DeckPasif pasif) throws Exception {
//        pasif.shuffleKartu(); //ndegbug
//        pasif.displayDeck(); // ngge ndebug
////        pasif.displayBarNgocok(); // ndebug
//        System.out.print("Pilih ndes : ");
//        try
//        {
//            Scanner maca = new Scanner(System.in);
//            int ndi = maca.nextInt();
//            if (ndi < 0 || ndi > 3) {
//                throw new Exception("Input harus antara 0-3");
//            }
//            this.getFromDeckPasif(pasif, ndi);
//            this.displayDeck();
//        }
//        catch (Exception exe)
//        {
//            System.out.println(exe);
//            return;
//        }
//    }
}
