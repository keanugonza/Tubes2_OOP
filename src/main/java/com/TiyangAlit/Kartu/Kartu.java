package com.TiyangAlit.Kartu;

public abstract class Kartu {
    /*
     *   ATTRIBUTES
     */
    public final static String[] listHewan = new String[]{"Hiu Darat", "Sapi", "Domba", "Kuda", "Ayam", "Beruang"};                                                      // 0 - 5
    public final static String[] listProduk = new String[]{"Labu", "Jagung", "Stroberi", "Susu", "Telur", "Sirip Hiu", "Daging Kuda", "Daging Domba", "Daging Beruang"}; // 6 - 14
    public final static String[] listTanaman = new String[]{"Biji Labu", "Biji Jagung", "Biji Stroberi"};                                                                // 15 - 17
    public final static String[] listItem = new String[]{"Accelerate", "Delay", "Instant Harvest", "Destroy", "Protect", "Trap"};                                        // 18 - 23

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
