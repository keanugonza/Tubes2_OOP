package com.TiyangAlit.Factory;

import com.TiyangAlit.Kartu.Kartu;

import java.util.HashMap;

public abstract class KartuFactory {
    /*
     *  METHODS
     */
    public abstract Kartu createKartu(String nama);
}
