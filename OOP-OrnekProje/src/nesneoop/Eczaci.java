
package nesneoop;
//sınıfa başka paketlerden erişilememelidir.
class Eczaci extends IlacBayiligi{

    public Eczaci(int toplamIlacIstenmeSayisi, int bayiKodu, int performansPuani) {
        super(toplamIlacIstenmeSayisi, bayiKodu, performansPuani);
    }

   

    @Override
    public String unvanYaz() {
        return "Eczacı";
    }

    //Eczacı için: Toplam çalışma yılının 3 katı ile bir yıllık ikramiye ücreti çarpımı
    @Override
    public int ikramiyeHesapla(int toplamCalismaYili) {
        return (toplamCalismaYili*3)*getBirYillikIkramiyeUcreti();
    }

    //Eczacı için: Performans puanının 2 katı
    @Override
    void performansSonucu() {
        System.out.println(" Eczacı performans sonucu: "+getPerformansPuani()*2);
    }

    @Override
    void ilacIstemeYetkisi() {
        //Eczacı için: Bayi kodu 500 ise “ilaç isteme yetkisi verilmiştir”, değilse “yetki iptal edilmiştir”
        if(getBayiKodu()==500){
            System.out.println("ilaç isteme yetkisi verilmiştir.");
        }else
        System.out.println("yetki iptal edilmiştir.");    }
    
}
