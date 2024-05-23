package com.TiyangAlit.Kartu.Entity.Tanaman;

import com.TiyangAlit.Kartu.Entity.Entity;

public class Tanaman extends Entity {
    /*
     *   ATTRIBUTES
     */
    private final String namaAwal;

    /*
     *   METHODS
     */
    public Tanaman(String nama, String image, int bobot, int bobotHarvest, String produk) {
        super(nama, image, bobot, bobotHarvest, produk);
        this.namaAwal = nama;
    }

    public String getNameAwal() { return this.namaAwal; }

    // Lain-lain
    @Override
    public void updateStatus() {
        super.updateStatus();

//        Path currRelativePath;
        String currAbsolutePathString;
        if (this.status)
        {
            currAbsolutePathString = "/Assets/Produk/" + getProduk() + ".png";
            setNama(getProduk());
//          currRelativePath = Paths.get("GUI", "Assets", "Produk", getProduk() + ".png");
        }
        else
        {
//          currRelativePath = Paths.get("GUI", "Assets", "Tanaman", nama + ".png");
            setNama(getNameAwal());
            currAbsolutePathString = "/Assets/Tanaman/" + this.nama + ".png";
        }

//        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";
        setImage(currAbsolutePathString);
    }
}
