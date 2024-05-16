package com.TiyangAlit.Factory;

import com.TiyangAlit.Factory.EntityFactory.HewanFactory.HerbivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.KarnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.HewanFactory.OmnivoraFactory;
import com.TiyangAlit.Factory.EntityFactory.TanamanFactory.TanamanFactory;
import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Kartu.Kartu;

public class FoF {
    // Factory of Factory
    public KartuFactory createFactory(Class<? extends Kartu> kartuClass) {
        System.out.println(kartuClass.getName());
        return switch (kartuClass.getName()) {
            case "com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Herbivora" -> new HerbivoraFactory();
            case "com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Karnivora" -> new KarnivoraFactory();
            case "com.TiyangAlit.Kartu.Entity.Hewan.Jenis.Omnivora" -> new OmnivoraFactory();
            case "com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman" -> new TanamanFactory();
            case "com.TiyangAlit.Kartu.Item.Item" -> new ItemFactory();
            case "com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan" -> new ProdukHewanFactory();
            case "com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman" -> new ProdukTanamanFactory();
            default -> null;
        };
    }
}
