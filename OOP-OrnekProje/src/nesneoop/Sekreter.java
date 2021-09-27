package nesneoop;
//tüm sınıflara başka paketlerden erişilememelidir. 

class Sekreter extends Muhasebe {

    //unvanı yazar
    @Override
    public String unvanYaz() {
        return "unvan: Sekreter";
    }

    //Sekreter için: Çalışma yılı ile bir yıllık ikramiye ücreti çarpımı
    @Override
    public int ikramiyeHesapla(int toplamCalismaYili) {
        return toplamCalismaYili * getBirYillikIkramiyeUcreti();
    }

    void maasBilgilendirmesiYap(float odemeTutari, String unvanIsmi) {
        if (unvanIsmi.equals("Muhasebe Müdürü")) {
            System.out.println("Sayın " + unvanIsmi + " maaşınız " + odemeTutari + " tutarındadır");
        } else {
            System.out.println("Sayın " + unvanIsmi + " maaşınız " + (odemeTutari * 2) + " tutarındadır");
        }
    }
    
    
    
        /*
     Banka Ödeme Yap: Virgüllü sayı cinsindeki “ödeme tutarı” değerini parametre alan ve bu değerin yarısını
geri döndüren bir metot olmalıdır. Sekreter hariç Muhasebe birimindeki unvanlarda aynı işlevi
yapmaktadır. Bilgi İşlem birimindeki unvanlarda olmamalıdır.
▪ Sekreter için: Ödeme tutarı değerinin üçte biri
    */

    @Override
    double bankaOdemeYap(float tutar){
        float t=tutar/3;
        return t;
    }

}
