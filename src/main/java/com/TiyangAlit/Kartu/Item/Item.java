package com.TiyangAlit.Kartu.Item;

import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Target;
import com.TiyangAlit.Kartu.Kartu;

public class Item extends Kartu {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Item(String nama) {
        super(nama);
    }

    // Lain-lain
    public <T extends Target> void apply(T obj) { obj.addEffect(this.nama); }
}
