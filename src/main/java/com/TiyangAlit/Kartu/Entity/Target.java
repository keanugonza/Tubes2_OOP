package com.TiyangAlit.Kartu.Entity;

import java.util.HashMap;

// Kartu yang dapat dikenai efek oleh Item.
public interface Target {
    // Getter & Setter
    HashMap<String, Integer> getEffects();
    void addEffect(String effect);
}
