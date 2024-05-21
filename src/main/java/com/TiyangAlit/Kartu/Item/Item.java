package com.TiyangAlit.Kartu.Item;

import com.TiyangAlit.Kartu.Entity.Harvestable;
import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Entity.Target;
import com.TiyangAlit.Kartu.Kartu;

public class Item extends Kartu {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Item(String nama, String image) {
        super(nama, image);
    }

    // Lain-lain
    public <T extends Target & Harvestable> void apply(T obj) {
        obj.addEffect(this.nama);
        switch (this.nama) {
            case "Accelerate" -> accelerate(obj);
            case "Delay" -> delay(obj);
            case "Instant Harvest" -> instantHarvest(obj);
        }
    }

    public <T extends Target & Harvestable> void accelerate(T obj) {
        int addedBobot = 2;
        if (obj instanceof Hewan)
            addedBobot = 8;
        obj.addBobot(addedBobot);
    }

    public <T extends Target & Harvestable> void delay(T obj) {
        int reducedBobot = 2;
        if (obj instanceof Hewan)
            reducedBobot = 5;
        reducedBobot = (Math.min(reducedBobot, obj.getBobot()));
        obj.reduceBobot(reducedBobot);
    }

    public <T extends Target & Harvestable> void instantHarvest(T obj) { obj.addBobot(9999); }
}
