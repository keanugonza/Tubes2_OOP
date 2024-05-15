package com.TiyangAlit.Kartu.Entity;

import java.util.HashMap;

public interface Target {
    // Getter & Setter
    public HashMap<String, Integer> getEffects();
    public void addEffect(String effect);
    public void removeEffect(String effect);
}
