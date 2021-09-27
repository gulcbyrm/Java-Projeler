
package nesneoop;

//kural:nesne oluşturulamamali olmalı sınıfa başka paketlerden erişilememelidir. 
//İlaç bayiliği biriminde; “Eczacı” ve “Veteriner” isimlerinde iki tane alt unvan (sınıf) olmalıdır.
abstract class IlacBayiligi extends Firma{
    //sınıf değişkenlerinin tümü dışarıdan doğrudan erişime kapalı olmalıdır.(private olmali)
    
    //Toplam İlaç İstenme Sayısı: Her ilaç istendiğinde bu değişkenin değeri 1 artmalıdır. Sadece İlaç bayiliği
//birimindekiler ilaç isteyebilmektedir.
    private int  toplamIlacIstenmeSayisi;
    
    /*Bayi Kodu: Bu değişkenin değeri sıfırdan küçük olmamalıdır ve bayi kodu olarak girilen değerin 10 katı
bayi kodu değeri olarak alınmalıdır. Sadece ilaç bayiliği birimindekilerin bayi kodu bulunmaktadır.*/
    private int bayiKodu;
    
   /* Performans Puanı: Bu değişkenin aldığı değer 200’den büyük olmamalıdır. Sadece İlaç bayiliği
birimindekilerin performans puanı vardır.*/
    private int performansPuani;

    public IlacBayiligi(int toplamIlacIstenmeSayisi, int bayiKodu, int performansPuani) {
        this.toplamIlacIstenmeSayisi = toplamIlacIstenmeSayisi;
        this.bayiKodu = bayiKodu;
        this.performansPuani = performansPuani;
    }

    
    
    //getter setter
    public int getPerformansPuani() {
        return performansPuani;
    }

    public void setPerformansPuani(int performansPuani) {
        if(performansPuani>200) 
            this.performansPuani=200;
        else this.performansPuani = performansPuani;
    }

    public int getToplamIlacIstenmeSayisi() {
        return toplamIlacIstenmeSayisi;
    }

    public void setToplamIlacIstenmeSayisi(int toplamIlacIstenmeSayisi) {
        this.toplamIlacIstenmeSayisi = toplamIlacIstenmeSayisi;
    }

    public int getBayiKodu() {
        return bayiKodu;
    }

    public void setBayiKodu(int bayiKodu) {
        this.bayiKodu = bayiKodu;
    }
    
    
    
    /*Performans Sonucu: Parametre almayan ve ekrana mesaj veren bir metot olmalıdır. Farklı mesajlar veren
bu metot İlaç Bayiliği birimindeki unvanlarda olmak zorundadır. Muhasebe birimindeki unvanlarda
olmamalıdır.*/
    abstract void performansSonucu();
    
    /*İlaç İste: Yazı cinsindeki “unvan ismi” değerini parametre alan ve toplam ilaç istenme sayısını 1 arttırıp
ekrana mesaj veren bir metot olmalıdır. İlaç bayiliği birimindeki unvanlarda aynı işlevi yapmaktadır.
Muhasebe birimindeki unvanlarda olmamalıdır.*/
    void ilacISte(String unvanIsmi){
        toplamIlacIstenmeSayisi=toplamIlacIstenmeSayisi+1;
        System.out.println(unvanIsmi+"  ilac istedi"+"toplam ilac isteme sayisi "+toplamIlacIstenmeSayisi);
    }
    /*İlaç İsteme Yetkisi: Parametre almayan ve “bayi kodu” değerine göre ekrana mesaj veren bir metot
olmalıdır. Farklı mesajlar veren bu metot İlaç Bayiliği birimindeki unvanlarında olmak zorundadır.
Muhasebe birimindeki unvanlarda olmamalıdır.*/
    abstract void ilacIstemeYetkisi();
        
    
}

