package com.TiyangAlit.Kartu;

public abstract class Kartu {
    /*
     *   ATTRIBUTES
     */
    protected final String nama;

    /*
     *   METHODS
     */
    public Kartu(String nama) {
        this.nama = nama;
    }

    // Getter & Setter
    public String getNama() { return this.nama; }

    // Lain-lain
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Kartu k)) return false;

        return k.getNama().equals(this.getNama());
    }
}
