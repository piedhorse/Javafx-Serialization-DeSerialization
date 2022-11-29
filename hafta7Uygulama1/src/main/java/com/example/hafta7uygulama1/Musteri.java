package com.example.hafta7uygulama1;

import java.io.Serializable;
import java.time.LocalDate;


public class Musteri implements Serializable {
    private String adSoyad, meslek, urun;
    private  LocalDate dogumTarihi;
    private Integer kimlikNo, urunAdet;

    public Musteri(String adSoyad, String meslek, LocalDate dogumTarihi, String urun, Integer kimlikNo, Integer urunAdet) {
        super();
        this.adSoyad = adSoyad;
        this.meslek = meslek;
        this.dogumTarihi = dogumTarihi;
        this.urun = urun;
        this.kimlikNo = kimlikNo;
        this.urunAdet = urunAdet;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public LocalDate getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(LocalDate dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getUrun() {
        return urun;
    }

    public void setUrun(String urun) {
        this.urun = urun;
    }

    public Integer getKimlikNo() {
        return kimlikNo;
    }

    public void setKimlikNo(Integer kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public Integer getUrunAdet() {
        return urunAdet;
    }

    public void setUrunAdet(Integer urunAdet) {
        this.urunAdet = urunAdet;
    }
}
