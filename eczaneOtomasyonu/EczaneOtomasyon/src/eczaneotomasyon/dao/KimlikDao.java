package eczaneotomasyon.dao;

import eczaneotomasyon.model.Kimlik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Alt tabakada olup iş yapan katmanlar DAO sınıfları olarak adlandırılır.
 *
 * DAO sınıfımız;* entity tablosuna ait verilerin veritabanına kaydedilmesi,
 * silinmesi, güncellenmesi gibi üst katmanda bulunan kullanıcı arayüzü olan
 * view sınıflarının verdiği görevleri yerine getirir. kısaca projemizdeki dao
 * sınıfları veri tabanıyla view sınıfları arasındaki katmandır diyebiliriz.
 *
 */
public class KimlikDao extends DAOAbstract<Kimlik> {

    /**
     * veri tabanına kaydetme işlemini gerçekleştirir
     *
     * @param entity kaydedilecek dataların yüklü olduğu nesne(entity)
     * @throws SQLException
     */

    @Override
    void save_(Kimlik entity) throws SQLException {
        //PreparedStatement nesnesi ile veritabanı CRUD işlemleri gerçekleştirilecektir.
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(
                "insert into Kimlik (KAdi,KSoyadi,KimlikTC,dogumTarihi,dogumYeri) VALUES ( ?, ?, ?, ?,? )");

        pst.setString(1, entity.getKAdi());
        pst.setString(2, entity.getKSoyadi());
        pst.setString(3, entity.getKimlikTC());
        pst.setString(4, entity.getDogumTarihi());
        pst.setString(5, entity.getDogumYeri());

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
    Kimlik isHave_(Kimlik entity) throws SQLException {
        Kimlik tmp = null;
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Kimlik where id=?");
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
    ArrayList<Kimlik> getAll_() throws SQLException {
        //kayıtların doldurulacağı liste ArrayList olarak
        ArrayList<Kimlik> kayitlarList = new ArrayList<>();

        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Kimlik");
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
    void update_(Kimlik entity, int id) throws SQLException {
        String sqlQuery = "update  Kimlik  set  KAdi=?, KSoyadi=?, KimlikTC=?, dogumTarihi=?,  dogumYeri=? where id=?";
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(sqlQuery);
        pst.setString(1, entity.getKAdi());
        pst.setString(2, entity.getKSoyadi());
        pst.setString(3, entity.getKimlikTC());
        pst.setString(4, entity.getDogumTarihi());
        pst.setString(5, entity.getDogumYeri());
        pst.setInt(6, id);
        pst.executeUpdate();
    }

    //verilen id ya sahip kayıt veritabanında mevcutsa, veritabanındaki kaydı nesne olarak döndürür
    @Override
    Kimlik getById_(int id) throws SQLException {
        Kimlik tmp = null;
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Kimlik where id=?");
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
            String sqlQuery = "delete from Kimlik where id=?";
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
    private Kimlik fillEntity(ResultSet rs) throws SQLException {
        Kimlik tmp = new Kimlik();
        tmp.setId(rs.getInt("id"));
        tmp.setKAdi(rs.getString("KAdi"));
        tmp.setKSoyadi(rs.getString("KSoyadi"));
        tmp.setKimlikTC(rs.getString("KimlikTC"));
        tmp.setDogumTarihi(rs.getString("dogumTarihi"));
        tmp.setDogumYeri(rs.getString("dogumYeri"));

        return tmp;
    }
}
