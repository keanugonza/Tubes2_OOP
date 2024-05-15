package com.TiyangAlit.Kartu.Entity;

import com.TiyangAlit.Kartu.Kartu;

import java.util.HashMap;

// Entity adalah kartu yang dapat dimainkan pada
// petak kosong.
// Entity dapat mendapatkan status effect dan
// dapat dipanen.
public abstract class Entity extends Kartu implements Harvestable, Target {
    /*
     *   ATTRIBUTES
     */
    protected final int bobotHarvest;
    protected int bobot; // Berat hewan / Umur tumbuhan
    protected boolean status;
    protected HashMap<String, Integer> effects;

    /*
     *   METHODS
     */
    public Entity(String nama, int bobot, int bobotHarvest) {
        super(nama);
        this.bobotHarvest = bobotHarvest;
        this.bobot = bobot;
        this.status = (this.bobot >= this.bobotHarvest);
        this.effects = new HashMap<>();

        for (String item : Kartu.listItem)
            effects.put(item, 0);
    }

    // Getter & Setter
    public int getBobot() { return this.bobot; }
    public int getBobotHarvest() { return this.bobotHarvest; }
    public boolean getStatus() { return this.status; }
    public HashMap<String, Integer> getEffects() { return this.effects; }

    public void setBobot(int bobot) {
        this.bobot = bobot;
        updateStatus();
    }
    public void setStatus(boolean status) { this.status = status; }
    public void addEffect(String effect) { this.effects.put(effect, this.effects.get(effect) + 1); }
    public void addBobot(int bobot) {
        this.bobot += bobot;
        updateStatus();
    }
    public void removeEffect(String effect) { this.effects.put(effect, this.effects.get(effect) - 1); }

    // Lain-lain
    public void updateStatus() { this.status = (this.bobot >= this.bobotHarvest); }
}
