package eczaneotomasyon.model;

public class Ilac extends BaseEntity implements EntityInterface {

    //variable
    private String IAdi;
    private String tipi;
    private String tanimi;
    private int fiyat;
    private float doz;
    private String uygulamaSekli;

    //constructor
    public Ilac() {
    }

    //constructor
    public Ilac(String IAdi, String tipi,String tanimi, int fiyat, float doz, String uygulamaSekli) {
        this.IAdi = IAdi;
        this.tipi = tipi;
        this.tanimi=tanimi;
        this.fiyat = fiyat;
        this.doz = doz;
        this.uygulamaSekli = uygulamaSekli;
    }

   

    //getter and setter
    public String getIAdi() {
        return IAdi;
    }

    public void setIAdi(String IAdi) {
        this.IAdi = IAdi;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public float getDoz() {
        return doz;
    }

    public void setDoz(float doz) {
        this.doz = doz;
    }

    public String getUygulamaSekli() {
        return uygulamaSekli;
    }

    public void setUygulamaSekli(String uygulamaSekli) {
        this.uygulamaSekli = uygulamaSekli;
    }

    public String getTanimi() {
        return tanimi;
    }

    public void setTanimi(String tanimi) {
        this.tanimi = tanimi;
    }

    @Override
    public String toString() {
        return IAdi;
    }

  
}
