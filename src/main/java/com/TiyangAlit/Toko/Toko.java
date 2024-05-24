package com.TiyangAlit.Toko;

import com.TiyangAlit.Factory.KartuFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukHewanFactory;
import com.TiyangAlit.Factory.ProdukFactory.JenisProdukFactory.ProdukTanamanFactory;
import com.TiyangAlit.Kartu.Kartu;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukHewan;
import com.TiyangAlit.Kartu.Produk.JenisProduk.ProdukTanaman;
import com.TiyangAlit.Kartu.Produk.Produk;
import com.TiyangAlit.Toko.TokoException.InvalidProdukTokoException;
import com.TiyangAlit.Toko.TokoException.NoSuchProdukOnToko;

import java.util.HashMap;
import java.util.Map;

public class Toko {
    private final HashMap<Produk, Integer> mapData;

    public Toko() {
        this.mapData = new HashMap<>();
        // Bikin factory
        KartuFactory produkHewanFactory = new ProdukHewanFactory();
        KartuFactory produkTanamanFactory = new ProdukTanamanFactory();

        Produk SiripHiu = (ProdukHewan) produkHewanFactory.createKartu("Sirip Hiu");
        Produk Susu = (ProdukHewan) produkHewanFactory.createKartu("Susu");
        Produk DagingDomba = (ProdukHewan) produkHewanFactory.createKartu("Daging Domba");
        Produk DagingKuda = (ProdukHewan) produkHewanFactory.createKartu("Daging Kuda");
        Produk Telur = (ProdukHewan) produkHewanFactory.createKartu("Telur");
        Produk DagingBeruang = (ProdukHewan) produkHewanFactory.createKartu("Daging Beruang");

        Produk Jagung = (ProdukTanaman) produkTanamanFactory.createKartu("Jagung");
        Produk Labu = (ProdukTanaman) produkTanamanFactory.createKartu("Labu");
        Produk Stroberi = (ProdukTanaman) produkTanamanFactory.createKartu("Stroberi");

        this.mapData.put(SiripHiu, 0);
        this.mapData.put(Susu, 0);
        this.mapData.put(DagingDomba, 0);
        this.mapData.put(DagingKuda, 0);
        this.mapData.put(Telur, 0);
        this.mapData.put(DagingBeruang, 0);
        this.mapData.put(Jagung, 0);
        this.mapData.put(Labu, 0);
        this.mapData.put(Stroberi, 0);
    }

    public HashMap<Produk, Integer> getData() {
        return mapData;
    }

    public int getSize() {
        return getData().size();
    }

    public int getLength() {
        int count = 0;
        for (Map.Entry<Produk, Integer> entry : mapData.entrySet()) {
            if (entry.getValue() > 0) {
                count++;
            }
        }
        return count;
    }

    public void tambahKartu(Kartu kartu) throws Exception {
        if (kartu instanceof Produk produk) {
            Integer currentCount = mapData.get(produk);
            mapData.put(produk, currentCount + 1);
        } else {
            throw new InvalidProdukTokoException("Kartu harus berupa produk");
        }
    }

    public void ambilKartu(Kartu kartu) throws Exception {
        if (kartu instanceof Produk produk) {
            if (mapData.get(produk) == 0) {
                throw new NoSuchProdukOnToko("Produk tidak ada dalam toko");
            } else{
                mapData.put(produk, mapData.get(produk) - 1);
            }
        }
    }


    //TESTING
    public void printMapData() {
        mapData.forEach((produk, jumlah) -> {
            System.out.println(produk.getNama() + ": " + jumlah);
        });
    }

}
