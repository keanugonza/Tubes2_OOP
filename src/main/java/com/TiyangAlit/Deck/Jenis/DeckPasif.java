package com.TiyangAlit.Deck.Jenis;

import com.TiyangAlit.Deck.Deck;
import com.TiyangAlit.Deck.DeckExceptions.DeckFullException;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.OmnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Kartu.Kartu;

import java.util.*;

public class DeckPasif extends Deck {
    /*
     *  ATTRIBUTES
     */

    /*
     *  METHODS
     */
    public DeckPasif() {
        super(40);
    }

    // Getter & Setter
    public Kartu getKartu(int idx) { return getDeck().get(idx); }

    public void setDeckRandomly() {

    }

//    public void displayBarNgocok() {
//        for (int i = 0; i < 4; i++) {
//            Kartu kartu = getKartu(i);
//            System.out.println(kartu.getNama());
//        }
//    }

    public List<Kartu> shuffleKartu() {
        List<Kartu> copyDeck = new ArrayList<Kartu>(getDeck());
        Collections.shuffle(copyDeck);
        if(getDeck().size() < 4){
            return copyDeck.subList(0, getDeck().size());
        } else{
            return copyDeck.subList(0, 4);
        }
    }

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

    public void initDeck(int amt) throws DeckFullException {
        Random rand = new Random();
        int randomIndex;

        List<String> allKartu = new ArrayList<>();

        String[] kartuHerbivora = HerbivoraFactory.listHerbivora;
        allKartu.addAll(Arrays.asList(kartuHerbivora));

        String[] kartuKarnivora = KarnivoraFactory.listKarnivora;
        allKartu.addAll(Arrays.asList(kartuKarnivora));

        String[] kartuOmnivora = OmnivoraFactory.listOmnivora;
        allKartu.addAll(Arrays.asList(kartuOmnivora));

        String[] kartuItem = ItemFactory.listItem;
        allKartu.addAll(Arrays.asList(kartuItem));

        String[] kartuProdukHewan = ProdukHewanFactory.listProdukHewan;
        allKartu.addAll(Arrays.asList(kartuProdukHewan));

        String[] kartuProdukTanaman = ProdukTanamanFactory.listProdukTanaman;
        allKartu.addAll(Arrays.asList(kartuProdukTanaman));

        allKartu.remove("Beruang");

        // Asumsi amt <= 40
        if (amt == 40) {
            initDeck();
            return;
        }

        for (int i = 0; i < amt; i++) {
            randomIndex = rand.nextInt(allKartu.size());
            String randomValue = allKartu.get(randomIndex);

            KartuFactory factory;
            if (Arrays.stream(ItemFactory.listItem).anyMatch(s -> s.contains(randomValue)))
                factory = new ItemFactory();
            else if (Arrays.stream(HerbivoraFactory.listHerbivora).anyMatch(s -> s.contains(randomValue)))
                factory = new HerbivoraFactory();
            else if (Arrays.stream(KarnivoraFactory.listKarnivora).anyMatch(s -> s.contains(randomValue)))
                factory = new KarnivoraFactory();
            else if (Arrays.stream(OmnivoraFactory.listOmnivora).anyMatch(s -> s.contains(randomValue)))
                factory = new OmnivoraFactory();
            else if (Arrays.stream(ProdukTanamanFactory.listProdukTanaman).anyMatch(s -> s.contains(randomValue)))
                factory = new ProdukTanamanFactory();
            else if (Arrays.stream(ProdukHewanFactory.listProdukHewan).anyMatch(s -> s.contains(randomValue)))
                factory = new ProdukHewanFactory();
            else
                factory = new TanamanFactory();
            Kartu kartu = factory.createKartu(randomValue);

            addKartu(kartu);
        }
    }
}
