
package nesneoop;

//tüm sınıflara başka paketlerden erişilememelidir.
class MuhasebeSefi extends Muhasebe{
    
 
     @Override
    public String unvanYaz() {
        return "unvan: Muhasebe Şefi"; 
    }

   //Muhasebe Şefi için: Çalışma yılının 2 eksiği ile bir yıllık ikramiye ücreti çarpımı
    @Override
    public int ikramiyeHesapla(int toplamCalismaYili) {
return (toplamCalismaYili-2)*getBirYillikIkramiyeUcreti();
    }
}
