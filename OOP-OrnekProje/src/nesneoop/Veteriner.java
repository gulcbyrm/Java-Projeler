
package nesneoop;

//sınıfa başka paketlerden erişilememelidir.
class Veteriner extends IlacBayiligi{

    public Veteriner(int toplamIlacIstenmeSayisi, int bayiKodu, int performansPuani) {
        super(toplamIlacIstenmeSayisi, bayiKodu, performansPuani);
    }

    @Override
    public String unvanYaz() {
return "Veteriner";}

    //Veteriner için: Toplam çalışma yılının üçte biri ile bir yıllık ikramiye ücreti çarpımı
    @Override
    public int ikramiyeHesapla(int toplamCalismaYili) {
        return (toplamCalismaYili*1/3)*getBirYillikIkramiyeUcreti();
    } 

    // Veteriner için: Performans puanının yarısı
    @Override
    void performansSonucu() {
        System.out.println(" Veteriner performans sonucu: "+getPerformansPuani()/2);
    }

    @Override
    void ilacIstemeYetkisi() {
        //Veteriner için: Bayi kodu 800 ise “ilaç isteme yetkisi verilmiştir”, değilse “yetki iptal edilmiştir”
        if(getBayiKodu()==800){
            System.out.println("ilaç isteme yetkisi verilmiştir");
        }else
 System.out.println("yetki iptal edilmiştir");     
    }
}
