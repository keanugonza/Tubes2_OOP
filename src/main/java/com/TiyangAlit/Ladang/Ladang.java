package com.TiyangAlit.Ladang;

import com.TiyangAlit.Factory.FoF;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.HewanExceptions.SalahTipeMakananException;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.LadangExceptions.*;
import com.TiyangAlit.Player.Player;

public class Ladang {
    /*
     *  ATTRIBUTES
     */
    private final Matrix<Entity> data;
    private final Player pemilikLadang;

    /*
     *  ATTRIBUTES
     */
    public Ladang(Player pemilikLadang) {
        this.data = new Matrix<>(4, 5);
        this.pemilikLadang = pemilikLadang;
    }

    // Getter
    public Matrix<Entity> getData() { return this.data; }
    public Player getPemilikLadang() { return this.pemilikLadang; }

    // Setter

    // Lain-lain
    public boolean isEmptySlot(int row, int col) { return data.getEl(row, col) == null; }

    public boolean isFullSlot(int row, int col) { return !isEmptySlot(row, col); }

    public boolean isIndexInvalid(int row, int col) { return (row < 0 || col < 0 || row >= 4 || col >= 5); }
    
    public void remove(int row, int col){
        this.data.removeEl(row, col);
    }

    // Place
    public void place(int row, int col, Kartu obj) throws Exception {
        // Meletakkan kartu obj ke kolom (row, col) ladang.
        if (isIndexInvalid(row, col))
            throw new LadangInvalidIndexException("Index ("  + row + ", " + col + ") invalid.");

        if (obj instanceof Entity) {
            if (isFullSlot(row, col))
                throw new LadangSlotFullException("Slot ladang (" + row + ", " + col + ") sudah penuh.");

            int oldBobot = ((Entity) obj).getBobot();

            FoF fof = new FoF();
            KartuFactory factory = fof.createFactory(obj.getClass());

            Entity entity;
            if (obj instanceof Tanaman)
                entity = (Entity) factory.createKartu(((Tanaman) obj).getNameAwal());
            else
                entity = (Entity) factory.createKartu(obj.getNama());

            if (entity != null) {
                entity.setBobot(oldBobot);
                this.data.setEl(row, col, entity);
            }
        } else {
            if (isEmptySlot(row, col))
                throw new LadangSlotKosongException("Slot ladang (" + row + ", " + col + ") kosong.");

            if (obj instanceof Item)
                placeItem(row, col, (Item) obj);
            else
                placeProduk(row, col, (Produk) obj);
        }
    }

    public void placeItem(int row, int col, Item item) {
        Entity entity = this.data.getEl(row, col);

        switch (item.getNama()) {
            case "Destroy" -> {
                // Hapus kartu dari ladang jika tidak memiliki efek protect.
                if (entity.getEffects().get("Protect") >= 1)
                    entity.reduceEffect("Protect");
                else
                    this.data.removeEl(row, col);
            }
            case "Instant Harvest" -> {
                // Panen kartu
                try {
                    item.apply(entity);
                    this.pemilikLadang.panen(row, col);
                } catch (Exception ignored) {
                    System.out.println(ignored.getMessage());
                }
            }
            case "Delay" -> {
                // Kurangi bobot kartu jika tidak memiliki efek protect.
                if (entity.getEffects().get("Protect") >= 1)
                    entity.reduceEffect("Protect");
                else
                    item.apply(entity);
            }
            default -> {
                item.apply(entity);
                this.data.setEl(row, col, entity);
            }
        }
    }

    public void placeProduk(int row, int col, Produk obj) throws InvalidKartuException, SalahTipeMakananException {
        Entity entity = this.data.getEl(row, col);
        if (entity instanceof Tanaman)
            throw new InvalidKartuException("Produk hanya dapat diberi ke hewan.");

        Hewan hewan = (Hewan) entity;
        hewan.makan(obj);
    }

    // Panen
    public Produk panen(int row, int col) throws Exception {
        if (isIndexInvalid(row, col))
            throw new LadangInvalidIndexException("Index ("  + row + ", " + col + ") invalid.");
        else if (isEmptySlot(row, col))
            throw new LadangSlotKosongException("Index (" + row + ", " + col + ") kosong.");

        Entity entity = this.data.getEl(row, col);
        if (!entity.getStatus())
            throw new InvalidPanenException(entity.getNama() + " belum siap panen.");
        this.data.removeEl(row, col);

        FoF fof = new FoF();
        KartuFactory factory;
        if (entity instanceof Hewan)
            factory = fof.createFactory(ProdukHewan.class);
        else
            factory = fof.createFactory(ProdukTanaman.class);
        return (Produk) factory.createKartu(entity.getProduk());
    }

    public int getJumlahKartu() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.data.getEl(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }


    // Next Turn
    public void tambahUmur() {
        // Tambah umur semua tanaman dalam ladang
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Entity currentEntity = this.data.getEl(i, j);
                if (currentEntity instanceof Tanaman)
                    currentEntity.addBobot(1);
                this.data.setEl(i, j, currentEntity);
            }
        }
    }

    // Output lokasi_kartu and kartu in ladang
    public String getLocationCardNameBobotItemsApplied() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Entity currentEntity = this.data.getEl(i, j);
                if (currentEntity != null) {
                    char row = (char) ('A' + i);
                    String location = row + String.format("%02d", j + 1);
                    String effectsDisplay = currentEntity.displayAndCountEffects();
                    result.append(location)
                            .append(" ")
                            .append(currentEntity.getNama())
                            .append(" ")
                            .append(currentEntity.getBobot())
                            .append(" ")
                            .append(effectsDisplay)
                            .append("\n");
                }
            }
        }
        return result.toString();
    }

    // TESTING
    public void displayLadang() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print((data.getEl(i, j) == null ? "NULL" : data.getEl(i, j).getNama()) + " ");
            System.out.println();
        }
    }
}