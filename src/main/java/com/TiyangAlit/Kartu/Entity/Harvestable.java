package com.TiyangAlit.Kartu.Entity;

public interface Harvestable {
    // Bobot: Tanaman --> umur
    //        Hewan --> berat

    // Getter & Setter
    public int getBobot();
    public int getBobotHarvest();
    public boolean getStatus();

    public void setBobot(int bobot);
    public void setStatus(boolean status);

    // Lain-lain
    public void updateStatus();

}
