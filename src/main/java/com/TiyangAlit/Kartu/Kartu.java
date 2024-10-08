package com.TiyangAlit.Kartu;

public abstract class Kartu {
    /*
     *   ATTRIBUTES
     */
    protected String nama;
    protected String image;

    /*
     *   METHODS
     */
    public Kartu(String nama, String image) {
        this.nama = nama;
        this.image = image;
    }

    // Getter & Setter
    public String getNama() { return this.nama; }
    public String getImage() { return this.image; }

    public void setImage(String image) { this.image = image; }
    public void setNama(String nama) { this.nama = nama; }

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

    @Override
    public int hashCode() {
        return this.getNama().hashCode();
    }
}
