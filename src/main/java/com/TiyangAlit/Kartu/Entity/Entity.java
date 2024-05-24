package com.TiyangAlit.Kartu.Entity;

import com.TiyangAlit.Factory.ItemFactory.ItemFactory;
import com.TiyangAlit.Kartu.Kartu;

import java.util.HashMap;

// Entity adalah kartu yang dapat dimainkan pada
// petak ladang yang kosong.
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
    protected String produk;

    /*
     *   METHODS
     */
    public Entity(String nama, String image, int bobot, int bobotHarvest, String produk) {
        super(nama, image);
        this.bobotHarvest = bobotHarvest;
        this.bobot = bobot;
        this.status = (this.bobot >= this.bobotHarvest);
        this.effects = new HashMap<>();
        this.produk = produk;

        for (String item : ItemFactory.listItem)
            effects.put(item, 0);
    }

    // Getter & Setter
    public int getBobot() { return this.bobot; }
    public int getBobotHarvest() { return this.bobotHarvest; }
    public boolean getStatus() { return this.status; }
    public HashMap<String, Integer> getEffects() { return this.effects; }
    public String getProduk() { return this.produk; }

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
    public void reduceEffect(String effect) { this.effects.put(effect, this.effects.get(effect) - 1); }
    public void reduceBobot(int bobot) {
        this.bobot -= bobot;
        updateStatus();
    }

    // Lain-lain
    public void updateStatus() { this.status = (this.bobot >= this.bobotHarvest); }

    // Method to display and count all effects applied to the entity
    public String displayAndCountEffects() {
        StringBuilder effectsDisplay = new StringBuilder();
        int count = 0;
        for (HashMap.Entry<String, Integer> entry : this.effects.entrySet()) {
            int value = entry.getValue();
            if (value > 0) {
                count += value;
                for (int i = 0; i < value; i++) {
                    effectsDisplay.append(entry.getKey()).append(" ");
                }
            }
        }
        return count + " " + effectsDisplay.toString().trim().toUpperCase();
    }
}
