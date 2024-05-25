package com.TiyangAlit.Kartu.Entity;

import java.util.HashMap;

// Kartu yang dapat dipanen
public interface Harvestable {
    // Bobot: Tanaman --> umur
    //        Hewan --> berat

    // Getter & Setter
    int getBobot();
    int getBobotHarvest();
    boolean getStatus();
    String getProduk();

    void setBobot(int bobot);
    void setStatus(boolean status);
    void setEffects(HashMap<String, Integer> effects);
    void addBobot(int bobot);
    void reduceBobot(int bobot);

    // Lain-lain
    void updateStatus();

}
