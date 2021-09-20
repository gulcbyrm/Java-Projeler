
package eczaneotomasyon.model;


public class Kimlik extends BaseEntity implements EntityInterface{
   //varible
    private String KAdi;
    private String KSoyadi;
    private String KimlikTC;
    private String dogumTarihi;
    private String  dogumYeri;
    
    
    //Constructor
    public Kimlik() {
    }
    
    //Constructor
    public Kimlik(String KAdi, String KSoyadi, String KimlikTC, String dogumTarihi, String dogumYeri) {
        this.KAdi = KAdi;
        this.KSoyadi = KSoyadi;
        this.KimlikTC = KimlikTC;
        this.dogumTarihi = dogumTarihi;
        this.dogumYeri = dogumYeri;
    }

    public String getKAdi() {
        return KAdi;
    }

    public void setKAdi(String KAdi) {
        this.KAdi = KAdi;
    }

    public String getKSoyadi() {
        return KSoyadi;
    }

    public void setKSoyadi(String KSoyadi) {
        this.KSoyadi = KSoyadi;
    }

    public String getKimlikTC() {
        return KimlikTC;
    }

    public void setKimlikTC(String KimlikTC) {
        this.KimlikTC = KimlikTC;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getDogumYeri() {
        return dogumYeri;
    }

    public void setDogumYeri(String dogumYeri) {
        this.dogumYeri = dogumYeri;
    }

    
    
  
      public String getName() {
        return getKAdi()+ " " + getKSoyadi();
    }

      @Override
    public String toString() {
        return  getName() ;
    }


 
}
