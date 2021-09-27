
package nesneoop;

//Muhasebe sınıfından nesne oluşturulabilmeli ve başka paketlerden erişilememelidir.
//Muhasebe biriminde; “Muhasebe Müdürü”, “Muhasebe Şefi” ve “Sekreter” isimlerinde üç tane alt unvan (sınıf) olmalıdır.
 class Muhasebe extends Firma{
    

    @Override
    public String unvanYaz() {
        return "MuhasebeBirimi";
    }

    @Override
    public int ikramiyeHesapla(int toplamCalismaYili) {
        return 0;
    }
    
    int  aylikSirketCirosu(int gelir, int gider){
        return gelir-gider;
    }
    
    double bankaOdemeYap(float tutar){
        float t=tutar/2;
        return t;
    }
}
