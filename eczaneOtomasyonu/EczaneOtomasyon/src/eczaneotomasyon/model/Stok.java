package eczaneotomasyon.model;


public class Stok extends BaseEntity implements EntityInterface {

    //variable
    private String satinAlindigiFirma;
    private String sonKullanmaTarihi;
    private int miktar;
    private Ilac ilac;
    private Eczane eczane;

    //constructor
    public Stok() {
    }

    //constructor

    public Stok(String satinAlindigiFirma, String sonKullanmaTarihi, int miktar, Ilac ilac, Eczane eczane) {
        this.satinAlindigiFirma = satinAlindigiFirma;
        this.sonKullanmaTarihi = sonKullanmaTarihi;
        this.miktar = miktar;
        this.ilac = ilac;
        this.eczane = eczane;
    }
    
  
    public String getSatinAlindigiFirma() {
        return satinAlindigiFirma;
    }

    public void setSatinAlindigiFirma(String satinAlindigiFirma) {
        this.satinAlindigiFirma = satinAlindigiFirma;
    }

    public String getSonKullanmaTarihi() {
        return sonKullanmaTarihi;
    }

    public void setSonKullanmaTarihi(String sonKullanmaTarihi) {
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public Ilac getIlac() {
          if(ilac==null){
            ilac=new Ilac();
          }
        return ilac;
    }

    public void setIlac(Ilac ilac) {
        this.ilac = ilac;
    }

    public Eczane getEczane() {
        if(eczane==null){
            eczane=new Eczane();
        }
        return eczane;
    }

    //getter and setter
    public void setEczane(Eczane eczane) {
        this.eczane = eczane;
    }

    @Override
    public String toString() {
        return sonKullanmaTarihi;
             
               
                
    }

}
