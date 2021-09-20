package eczaneotomasyon.model;



public class Eczane  extends BaseEntity   implements EntityInterface{
 
    //variable
    private String EAdi;
    private String kurulusTarihi;
    private String kiminAdinaKayitli;

    //constructor
    public Eczane() {
    }
    
    //constructor
    public Eczane(String EAdi, String kurulusTarihi, String kiminAdinaKayitli) {
        this.EAdi = EAdi;
        this.kurulusTarihi = kurulusTarihi;
        this.kiminAdinaKayitli = kiminAdinaKayitli;
    }
 //getter
    public String getEAdi() {
        return EAdi;
    }

    public String getKurulusTarihi() {
        return kurulusTarihi;
    }

    public String getKiminAdinaKayitli() {
        return kiminAdinaKayitli;
    }

    
     //setter
    public void setEAdi(String EAdi) {
        this.EAdi = EAdi;
    }

    public void setKurulusTarihi(String kurulusTarihi) {
        this.kurulusTarihi = kurulusTarihi;
    }

    public void setKiminAdinaKayitli(String kiminAdinaKayitli) {
        this.kiminAdinaKayitli = kiminAdinaKayitli;
    }

    @Override
    public String toString() {
        return EAdi ;
                  
                
    }
    
    
    
}
