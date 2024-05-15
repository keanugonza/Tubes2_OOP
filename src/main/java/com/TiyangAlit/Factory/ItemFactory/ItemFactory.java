package com.TiyangAlit.Factory.ItemFactory;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;

public class ItemFactory extends KartuFactory {
    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        return new Item(nama);
    }
}
