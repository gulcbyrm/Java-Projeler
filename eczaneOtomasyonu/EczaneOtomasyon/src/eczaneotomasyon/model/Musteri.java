package eczaneotomasyon.model;

 
 
public class Musteri extends BaseEntity implements EntityInterface {
    private String satinAlinanTarih;
    private String hastalikAdi;
    private int sigortaNo;
    private String saglikGuvenceTuru;
    private String durum;
    private Kimlik kimlik;

	     
    
    //constructure
    public Musteri() {
    }

    
    
    //constructure
    public Musteri(String satinAlinanTarih, String hastalikAdi, int sigortaNo, String saglikGuvenceTuru, String durum, Kimlik kimlik) {
        this.satinAlinanTarih = satinAlinanTarih;
        this.hastalikAdi = hastalikAdi;
        this.sigortaNo = sigortaNo;
        this.saglikGuvenceTuru = saglikGuvenceTuru;
        this.durum = durum;
        this.kimlik = kimlik;
    }
    
    //getters and setters
    public String getSatinAlinanTarih() {    
        return satinAlinanTarih;
    }

    public void setSatinAlinanTarih(String satinAlinanTarih) {
        this.satinAlinanTarih = satinAlinanTarih;
    }

    public String getHastalikAdi() {
        return hastalikAdi;
    }

    public void setHastalikAdi(String hastalikAdi) {
        this.hastalikAdi = hastalikAdi;
    }

    public int getSigortaNo() {
        return sigortaNo;
    }

    public void setSigortaNo(int sigortaNo) {
        this.sigortaNo = sigortaNo;
    }

    public String getSaglikGuvenceTuru() {
        return saglikGuvenceTuru;
    }

    public void setSaglikGuvenceTuru(String saglikGuvenceTuru) {
        this.saglikGuvenceTuru = saglikGuvenceTuru;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Kimlik getKimlikID() {
         if(kimlik==null){
            kimlik=new Kimlik();
        }
        return kimlik;
    }

    
    public void setKimlikID(Kimlik kimlik) {    
        this.kimlik = kimlik;
    }

    @Override
    public String toString() {
        return  
                "Sigorta No=" + sigortaNo 
                + ", Saglik Guvence Türü=" + saglikGuvenceTuru 
               ;
    }

  

}
