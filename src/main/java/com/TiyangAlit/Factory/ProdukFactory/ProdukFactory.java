package com.TiyangAlit.Factory.ProdukFactory;

import com.TiyangAlit.Factory.KartuFactory;

import java.util.HashMap;

public abstract class ProdukFactory extends KartuFactory {
    /*
     *  ATTRIBUTES
     */
    protected final static HashMap<String, Integer> mapHarga = new HashMap<>();
    protected final static HashMap<String, Integer> mapBerat = new HashMap<>();
}
