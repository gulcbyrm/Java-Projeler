
package eczaneotomasyon.dao;

import eczaneotomasyon.model.Eczane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Alt tabakada olup iş yapan katmanlar DAO sınıfları olarak adlandırılır.
 *
 * dao sınıfımız; * entity tablosuna ait verilerin veritabanına
 * kaydedilmesi, silinmesi, güncellenmesi gibi üst katmanda bulunan kullanıcı
 * arayüzü olan view sınıflarının verdiği görevleri yerine getirir. kısaca
 * projemizdeki dao sınıfları veri tabanıyla view sınıfları arasındaki katmandır
 * diyebiliriz.
 *
 */
public class EczaneDao extends DAOAbstract<Eczane> {
 /**
     * veri tabanına kaydetme işlemini gerçekleştirir
     *
     * @param entity kaydedilecek dataların yüklü olduğu nesne(entity)
     * @throws SQLException
     */
   
    @Override
    void save_(Eczane entity) throws SQLException {
        //PreparedStatement nesnesi ile veritabanı CRUD işlemleri gerçekleştirilecektir.
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(
                "insert into Eczane(EAdi,kurulusTarihi,kiminAdinaKayitli) VALUES ( ?,?,? )");

        pst.setString(1, entity.getEAdi());
        pst.setString(2, entity.getKurulusTarihi());
        pst.setString(3, entity.getKiminAdinaKayitli());
       

        //pst yukarıda hazırlandı içi dolduruldu. şimdi komutu çalıştır
        pst.executeUpdate();
    }

    /**
     * bilgileri verilen entitynin veri tabanında olup olmadığını kontrol eder.
     * T yani entity gönderip entity almak mantıksız gibi gelebilir ancak gelen
     * entity 0 id ye sahip ve var olup olmadığı kontrol ediliyor Eğer varsa
     * idsi ile birlikte return edilecektir
     *
     * @param entity aranan verilerin olduğu entity
     * @return full entity veya bulunamazsa NULL değeri
     * @throws SQLException
     */
    @Override
    Eczane isHave_(Eczane entity) throws SQLException {
        Eczane tmp = null;
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Eczane where id=?");
        pst.setInt(1, entity.getId());

        //veritabanından alınan verileri tutmak için ResultSet nesnesi kullanılır
        ResultSet rs = pst.executeQuery();

        //eğer aranan veritabanında varsa yani rs'in nexti mevcut ise
        if (rs.next()) {
            //veritabanından gelen data nesneye dönüştürülüyor
            tmp = fillEntity(rs);
        }
        //aranan bulunmadıysa null bulunduysa entity return edilecektir.
        return tmp;
    }

    /**
     * veri tabanındaki entity tablosunda bulunan tüm kayıtları çeker, listeye
     * doldurur
     *
     * @return bir arrayList return eder bu list: veritabanındaki entity lerle
     * doldurulmuştur
     * @throws SQLException hatası alınırsa ötelenecektir. bir üst sınıfa havale
     * edilmiştir.
     */
    @Override
    ArrayList<Eczane> getAll_() throws SQLException {
        //kayıtların doldurulacağı liste ArrayList olarak
        ArrayList<Eczane> kayitlarList = new ArrayList<>();

        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Eczane");
        //sonuçları rs değişkenine doldur
        ResultSet rs = pst.executeQuery();

        //rs te veri olduğu müddetçe
        while (rs.next()) {
            kayitlarList.add(fillEntity(rs));
        }
        return kayitlarList;
    }

    /**
     * güncelleme işlemini veritabınında gerçekleştirir
     *
     * @param entity güncellenecek verilerin olduğu nesne
     * @throws SQLException
     */
    
    @Override
    void update_(Eczane entity, int id) throws SQLException {
        String sqlQuery = "update  Eczane  set  EAdi=?, kurulusTarihi=?, kiminAdinaKayitli=? where id=?";
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(sqlQuery);

        pst.setString(1, entity.getEAdi());
        pst.setString(2, entity.getKurulusTarihi());
        pst.setString(3, entity.getKiminAdinaKayitli());      
        pst.setInt(4, id);
        pst.executeUpdate();
    }

    //verilen id ya sahip kayıt veritabanında mevcutsa, veritabanındaki kaydı nesne olarak döndürür
    @Override
    Eczane getById_(int id) throws SQLException {
        Eczane tmp = null;
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Eczane where id=?");
        pst.setInt(1, id);

        //veritabanından alınan verileri tutmak için ResultSet nesnesi kullanılır
        ResultSet rs = pst.executeQuery();

        //eğer aranan veritabanında varsa yani rs'in nexti mevcut ise
        if (rs.next()) {
            //nesne dolduruluyor
            tmp = fillEntity(rs);
        }
        //aranan bulunmadıysa null bulunduysa entity return edilecektir.
        return tmp;
    }

    /**
     * veri tabanından silme işlemini gerçekleştirir
     *
     * @param entity silinecek nesne
     * @throws SQLException
     */
    @Override
    void delete_(int id) throws SQLException {
        try {
            //sorgu sözcüğü
            String sqlQuery = "delete from Eczane where id=?";
            //baglanti değişkenimiz ile veri tabanına bağlantı kur ve sorguyu çalıştır
            PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(sqlQuery);
            //soru işaretli yere yani 1nci soru işaretine ID set et
            pst.setInt(1, id);
            //sorguyu çalıştır 2 çeşit çalıştırma mevcut 1.executeUpdate: ekle sil güncelle
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "an error occured when delete" + ex.getMessage());
        }
    }

  
    //Entity nesnesini doldurur
    private Eczane fillEntity(ResultSet rs) throws SQLException {
        Eczane tmp = new Eczane();
        tmp.setId(rs.getInt("id"));
        tmp.setEAdi(rs.getString("EAdi"));
        tmp.setKurulusTarihi(rs.getString("kurulusTarihi"));
        tmp.setKiminAdinaKayitli(rs.getString("kiminAdinaKayitli"));
        

        return tmp;
    }
    
}
