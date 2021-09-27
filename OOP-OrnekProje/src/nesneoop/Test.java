
package nesneoop;

public class Test {

  
    public static void main(String[] args) {
        Eczaci eczaci=new Eczaci(5, 50,90 );
        MuhasebeMuduru muhasebeMuduru=new MuhasebeMuduru();
/*Eczacı
İkramiye Tutarı: 15000.0 //Toplam Çalışma Yılı: 5 girildiğinde
Performans Sonucu: 180.0 //Performans Puanı: 90 girildiğinde
Eczacı ilaç istedi. Toplam ilaç istenme sayısı: 1
İlaç isteme yetkisi verildi //Bayi Kodu 50 girildiğinde*/
System.out.println("Eczacı\n");
int ikramiyeTutari=eczaci.ikramiyeHesapla(5);

        System.out.println("İkramiye Tutarı: "+ikramiyeTutari);
        System.out.print("Performans Sonucu:");
        eczaci.performansSonucu();
        eczaci.ilacISte("Eczaci");
        eczaci.ilacIstemeYetkisi();

        
        /*Veteriner
İkramiye Tutarı: 1666.6666666666667 //Toplam Çalışma Yılı: 5 girildiğinde
Performans Sonucu: 100 //Performans Puanı: 250 girildiğinde
Veteriner ilaç istedi. Toplam ilaç istenme sayısı: 2
İlaç isteme yetkisi iptal edildi //Bayi Kodu 81 girildiğinde
Eczacı ilaç istedi. Toplam ilaç istenme sayısı: 3 //Eczacı tekrar ilaç istedi*/
        Veteriner v=new Veteriner(5, 81, 250);
        System.out.println("Veteriner\n");
int ikramiyeTutari2=eczaci.ikramiyeHesapla(5);

        System.out.println("İkramiye Tutarı: "+ikramiyeTutari2);
        System.out.print("Performans Sonucu:");
        eczaci.performansSonucu();
        eczaci.ilacISte("Veteriner");
        eczaci.ilacIstemeYetkisi();

        
       
        
        
        
        
        /*Sekreter
İkramiye Tutarı: 5000.0 //Toplam Çalışma Yılı: 5 girildiğinde
Aylık Şirket Cirosu: 180000 //Gelir: 300000, Gider: 120000 girildiğinde
Sayın Muhasebe Müdürü maaşınız 5000.0 tutarındadır. //Maaş tutarı 5000 girildiğinde
Sayın Muhasebe Şefi maaşınız 2500.0 tutarındadır. //Maaş tutarı 5000 girildiğinde
Sayın Sekreter maaşınız 2500.0 tutarındadır. //Maaş tutarı 5000 girildiğinde
Bankaya Yapılan Ödeme: 1666.6666666666667 //Ödeme tutarı 5000 girildiğinde*/
        
        
        Sekreter s=new Sekreter();
       int ikramiyeTutari1=s.ikramiyeHesapla(5);
          System.out.println("İkramiye Tutarı: "+ikramiyeTutari1);
          
    }
    
   
}
