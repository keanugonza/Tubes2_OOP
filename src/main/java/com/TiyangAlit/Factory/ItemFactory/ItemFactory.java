package com.TiyangAlit.Factory.ItemFactory;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Kartu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

//        Path currRelativePath = Paths.get("src","main", "java", "com", "TiyangAlit", "Resources", "Assets", "Item", nama + ".png");
//        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";

        String currAbsolutePathString = "/Assets/Item/" + nama + ".png";

        return new Item(nama, currAbsolutePathString);
    }
}
