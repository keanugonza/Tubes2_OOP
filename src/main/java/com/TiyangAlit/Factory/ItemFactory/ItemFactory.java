package com.TiyangAlit.Factory.ItemFactory;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;

import java.util.Arrays;

public class ItemFactory extends KartuFactory {
    /*
     *  ATTRIBUTES
     */
    public final static String[] listItem = new String[]{"Accelerate", "Delay", "Instant Harvest", "Destroy", "Protect", "Trap"};

    /*
     *  METHODS
     */
    @Override
    public Kartu createKartu(String nama) {
        if (!Arrays.asList(listItem).contains(nama))
            return null;
        return new Item(nama);
    }
}
