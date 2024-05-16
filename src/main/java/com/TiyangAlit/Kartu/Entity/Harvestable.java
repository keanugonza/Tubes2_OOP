package com.TiyangAlit.Kartu.Entity;

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
    void addBobot(int bobot);
    void reduceBobot(int bobot);

    // Lain-lain
    void updateStatus();

}
