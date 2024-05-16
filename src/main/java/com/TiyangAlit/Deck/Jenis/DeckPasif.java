package com.TiyangAlit.Deck.Jenis;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.OmnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;

public class DeckPasif extends Deck {
    /*
     *  ATTRIBUTES
     */

    /*
     *  METHODS
     */
    public DeckPasif() {
        super(40);
        initDeck();
    }

    // Getter & Setter

    // Lain-lain
    public void initDeck() {
        /*
         * Komposisi deck: 4 Ayam, 2 Sapi, 2 Domba, 1 Kuda, 1 Hiu Darat
         *                 5 Labu, 5 Susu
         *                 5 Biji Labu, 5 Biji Jagung, 4 Biji Stroberi
         *                 1 Accelerate, 1 Delay, 1 Instant Harvest, 1 Destroy, 1 Protect, 1 Trap
         */

        // Factory
        KartuFactory herbivoraFactory = new HerbivoraFactory();
        KartuFactory karnivoraFactory = new KarnivoraFactory();
        KartuFactory omnivoraFactory = new OmnivoraFactory();
        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory tanamanFactory = new TanamanFactory();
        KartuFactory itemFactory = new ItemFactory();

        try {
            // Hewan
            addKartu(omnivoraFactory.createKartu("Ayam"), 4);
            addKartu(herbivoraFactory.createKartu("Sapi"), 2);
            addKartu(herbivoraFactory.createKartu("Domba"), 2);
            addKartu(herbivoraFactory.createKartu("Kuda"), 1);
            addKartu(karnivoraFactory.createKartu("Hiu Darat"), 1);

            // Produk
            addKartu(produkTanamanFactory.createKartu("Labu"), 5);
            addKartu(produkHewanFactory.createKartu("Susu"), 5);

            // Tanaman
            addKartu(tanamanFactory.createKartu("Biji Labu"),5);
            addKartu(tanamanFactory.createKartu("Biji Jagung"),5);
            addKartu(tanamanFactory.createKartu("Biji Stroberi"),4);

            // Item
            addKartu(itemFactory.createKartu("Accelerate"));
            addKartu(itemFactory.createKartu("Delay"));
            addKartu(itemFactory.createKartu("Instant Harvest"));
            addKartu(itemFactory.createKartu("Destroy"));
            addKartu(itemFactory.createKartu("Protect"));
            addKartu(itemFactory.createKartu("Trap"));
        } catch (Exception ignored) { }
    }
}
