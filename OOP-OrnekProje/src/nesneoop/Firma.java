
package nesneoop;


 abstract class Firma {
 //Değişkenler: “Bir Yıllık İkramiye Ücreti”, “Toplam İlaç İstenme Sayısı”, “Bayi Kodu” 
     //ve “Performans Puanı” değerlerini tutan dört tane değişken olmalıdır

//Bir Yıllık İkramiye Ücreti: Bu değişkenin değeri önceden 1000 olarak belirlenmiş olup, sonradan
//değiştirilemez olmalıdır. Tüm sınıflardan kullanılabilir bir değişkendir.
    final private  int birYillikIkramiyeUcreti=1000;

    //get metodu
    public int getBirYillikIkramiyeUcreti() {
        return birYillikIkramiyeUcreti;
    }
    

    /*Unvan Yaz: Parametre almayan ve unvan yazısını geri döndüren bir metot olmalıdır. Farklı yazılar geri
döndüren bu metot tüm unvan sınıflarında olmak zorundadır.*/
    abstract public String unvanYaz();
     /*İkramiye Hesapla: Tamsayı cinsindeki “toplam çalışma yılı” değerini parametre alan ve her unvan sınıfında
farklı bir hesaplama sonucunu geri döndüren bir metot olmalıdır.*/
     abstract public int ikramiyeHesapla(int toplamCalismaYili);
 }
