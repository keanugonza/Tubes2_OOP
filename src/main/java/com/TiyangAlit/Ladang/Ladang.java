package com.TiyangAlit.Ladang;

import com.TiyangAlit.Factory.FoF;
import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Kartu.Entity.Hewan.HewanExceptions.SalahTipeMakananException;
import com.TiyangAlit.Kartu.Item.Item;
import com.TiyangAlit.Kartu.Entity.Entity;
import com.TiyangAlit.Kartu.Entity.Hewan.Hewan;
import com.TiyangAlit.Kartu.Entity.Tanaman.Tanaman;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Ladang.LadangExceptions.LadangInvalidIndexException;
import com.TiyangAlit.Ladang.LadangExceptions.LadangSlotFullException;
import com.TiyangAlit.Ladang.LadangExceptions.LadangSlotKosongException;
import com.TiyangAlit.Ladang.LadangExceptions.InvalidKartuException;

public class Ladang {
    /*
     *  ATTRIBUTES
     */
    private final Matrix<Entity> data;

    /*
     *  ATTRIBUTES
     */
    public Ladang() {
        this.data = new Matrix<>(4, 5);
    }

    // Getter
    public Matrix<Entity> getData() { return this.data; }

    // Setter

    // Lain-lain
    public boolean isEmptySlot(int row, int col) { return data.getEl(row, col) == null; }

    public boolean isFullSlot(int row, int col) { return !isEmptySlot(row, col); }

    public boolean isIndexValid(int row, int col) { return (row >= 0 && col >= 0 && row < 4 && col < 5); }

    // Place
    public void place(int row, int col, Kartu obj) throws Exception {
        // Meletakkan kartu obj ke kolom (row, col) ladang.
        if (!isIndexValid(row, col))
            throw new LadangInvalidIndexException("Index ("  + row + ", " + col + ") invalid.");

        if (obj instanceof Entity) {
            if (isFullSlot(row, col))
                throw new LadangSlotFullException("Slot ladang (" + row + ", " + col + ") sudah penuh.");

            FoF fof = new FoF();
            KartuFactory factory = fof.createFactory(obj.getClass());

            Entity entity = (Entity) factory.createKartu(obj.getNama());
            if (entity != null)
                this.data.setEl(row, col, entity);
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
        item.apply(entity);
        this.data.setEl(row, col, entity);
    }

    public void placeProduk(int row, int col, Produk obj) throws InvalidKartuException, SalahTipeMakananException {
        Entity entity = this.data.getEl(row, col);
        if (entity instanceof Tanaman)
            throw new InvalidKartuException("Produk hanya dapat diberi ke hewan.");

        Hewan hewan = (Hewan) entity;
        hewan.makan(obj);
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