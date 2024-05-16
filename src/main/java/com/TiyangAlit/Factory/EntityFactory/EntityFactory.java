package com.TiyangAlit.Factory.EntityFactory;

import com.TiyangAlit.Factory.KartuFactory;

import java.util.HashMap;

public abstract class EntityFactory extends KartuFactory {
    /*
     *  ATTRIBUTES
     */
    protected final static HashMap<String, Integer> mapBobotHarvest = new HashMap<>();
    protected final static HashMap<String, String> mapProduk = new HashMap<>();
}
