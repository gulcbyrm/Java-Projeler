
package eczaneotomasyon.dao;

import eczaneotomasyon.model.Musteri;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Alt tabakada olup iş yapan katmanlar DAO sınıfları olarak adlandırılır.
 *
 * Dao sınıfımız; entity tablosuna ait verilerin veritabanına kaydedilmesi,
 * silinmesi, güncellenmesi gibi üst katmanda bulunan kullanıcı arayüzü olan
 * view sınıflarının verdiği görevleri yerine getirir. kısaca projemizdeki dao
 * sınıfları veri tabanıyla view sınıfları arasındaki katmandır diyebiliriz.
 *
 */
public class MusteriDAO extends DAOAbstract<Musteri>{
 /**
     * veri tabanına kaydetme işlemini gerçekleştirir
     *
     * @param entity kaydedilecek dataların yüklü olduğu nesne(entity)
     * @throws SQLException
     */

    @Override
    void save_(Musteri entity) throws SQLException {
        //PreparedStatement nesnesi ile veritabanı CRUD işlemleri gerçekleştirilecektir.
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(
                "insert into Musteri (satinAlinanTarih,hastalikAdi,sigortaNo,saglikGuvenceTuru,durum,kimlikID) VALUES ( ?, ?, ?, ?,?,? )");

        pst.setString(1, entity.getSatinAlinanTarih());
        pst.setString(2, entity.getHastalikAdi());
        pst.setInt(3, entity.getSigortaNo());
        pst.setString(4, entity. getSaglikGuvenceTuru());
        pst.setString(5, entity.getDurum());
        pst.setInt(6, entity.getKimlikID().getId());


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
    Musteri isHave_(Musteri entity) throws SQLException {
        Musteri tmp = null;
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Musteri where id=?");
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
    ArrayList<Musteri> getAll_() throws SQLException {
        //kayıtların doldurulacağı liste ArrayList olarak
        ArrayList<Musteri> kayitlarList = new ArrayList<>();

        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Musteri");
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
    void update_(Musteri entity, int id) throws SQLException {
        String sqlQuery = "update  Musteri  set  satinAlinanTarih=?, hastalikAdi=?, sigortaNo=?, saglikGuvenceTuru=?,  durum=? ,kimlikID=? where id=?";
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement(sqlQuery);
       pst.setString(1, entity.getSatinAlinanTarih());
        pst.setString(2, entity.getHastalikAdi());
        pst.setInt(3, entity.getSigortaNo());
        pst.setString(4, entity. getSaglikGuvenceTuru());
        pst.setString(5, entity.getDurum());
        pst.setInt(6, entity.getKimlikID().getId());
        pst.setInt(7, id);
        pst.executeUpdate();
    }

    //verilen id ya sahip kayıt veritabanında mevcutsa, veritabanındaki kaydı nesne olarak döndürür
    @Override
    Musteri getById_(int id) throws SQLException {
        Musteri tmp = null;
        PreparedStatement pst = DAOAbstract.baglanti.prepareStatement("select * from Musteri where id=?");
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
            String sqlQuery = "delete from Musteri where id=?";
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
    private Musteri fillEntity(ResultSet rs) throws SQLException {
        Musteri tmp = new Musteri();
        tmp.setId(rs.getInt("id"));
        tmp.setSatinAlinanTarih(rs.getString("satinAlinanTarih"));
        tmp.setHastalikAdi(rs.getString("hastalikAdi"));
        tmp.setSigortaNo(rs.getInt("sigortaNo"));
        tmp.setSaglikGuvenceTuru(rs.getString("saglikGuvenceTuru"));
        tmp.setDurum(rs.getString("durum"));
        tmp.setKimlikID(new KimlikDao().getById_(rs.getInt("kimlikID")));


        return tmp;
    }}