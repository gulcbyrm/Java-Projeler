
package nesneoop;

//tüm sınıflara başka paketlerden erişilememelidir.
class MuhasebeMuduru extends Muhasebe{

   
   
    
     @Override
    public String unvanYaz() {
 return "MuhasebeMuduru";    }
//Muhasebe Müdürü için: Toplam çalışma yılının 2 fazlası ile bir yıllık ikramiye ücreti çarpımı
    @Override
    public int ikramiyeHesapla(int toplamCalismaYili) {
         return (toplamCalismaYili+2)*getBirYillikIkramiyeUcreti();
    }
    
}
