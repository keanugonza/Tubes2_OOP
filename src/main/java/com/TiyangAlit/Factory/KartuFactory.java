package com.TiyangAlit.Factory;

import com.TiyangAlit.Kartu.Kartu;

public abstract class KartuFactory {
    /*
     *  METHODS
     */
    public abstract Kartu createKartu(String nama);
}
