package eczaneotomasyon.model;


public class Satis extends BaseEntity implements EntityInterface {
    //variable
    private String satisTarihi;
    private Musteri musteri;
    private Ilac ilac;

    //constructor         
    public Satis() {
    }

    //constructor         
    public Satis(String satisTarihi, Musteri musteri, Ilac ilac) {
        this.satisTarihi = satisTarihi;
        this.musteri = musteri;
        this.ilac = ilac;
    }

    //getter
    public String getSatisTarihi() {
        return satisTarihi;
    }

    public Musteri getMusteri() {
        if (musteri == null) {
            musteri = new Musteri();
        }
        return musteri;
    }

    public Ilac getIlac() {
        if (ilac == null) {
            ilac = new Ilac();
        }
        return ilac;
    }

    //setter
    public void setSatisTarihi(String satisTarihi) {
        this.satisTarihi = satisTarihi;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public void setIlac(Ilac ilac) {
        this.ilac = ilac;
    }

    @Override
    public String toString() {
        return satisTarihi;
    }

}
