package com.TiyangAlit.Kartu.Entity.Tanaman;

import com.TiyangAlit.Kartu.Entity.Entity;

public class Tanaman extends Entity {
    /*
     *   ATTRIBUTES
     */

    /*
     *   METHODS
     */
    public Tanaman(String nama, String image, int bobot, int bobotHarvest, String produk) {
        super(nama, image, bobot, bobotHarvest, produk);
    }

    // Lain-lain
    @Override
    public void updateStatus() {
        super.updateStatus();

//        Path currRelativePath;
        String currAbsolutePathString;
        if (this.status)
//          currRelativePath = Paths.get("GUI", "Assets", "Produk", getProduk() + ".png");
            currAbsolutePathString = "/Assets/Produk/" + getProduk() + ".png";
        else
//          currRelativePath = Paths.get("GUI", "Assets", "Tanaman", nama + ".png");
            currAbsolutePathString = "/Assets/Tanaman/" + this.nama + ".png";
//        String currAbsolutePathString = "\"" + currRelativePath.toAbsolutePath() + "\"";
        setImage(currAbsolutePathString);
    }
}
