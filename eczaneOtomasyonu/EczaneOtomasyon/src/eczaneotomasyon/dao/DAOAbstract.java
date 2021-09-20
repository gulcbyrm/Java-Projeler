package eczaneotomasyon.dao;

import eczaneotomasyon.model.EntityInterface;
import eczaneotomasyon.util.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * Interface olarak yazılan bu sınıf iş yapan katman olan yani view paketindeki
 * viewerlerin crud işlerini yerine getiren dao sınıflarının ortak bir arayüzden
 * üretilmesi ve işlemlerin düzenli olması için yazıldı
 *
 * bütün dao sınıfları DAOAbstract sınıfına extend edeceklerdir
 *
 */
public abstract class DAOAbstract<T extends EntityInterface> {

    //veritabanı bağlantısını sağlayan değişkendir. 
    //her talep edildiğinde ConnectionManager() sınıfının üretilmemesi için   static ve final olarak oluşturuldu
    public static final Connection baglanti = new ConnectionManager().getConnection();

    
     
    /*extend eden sınıfların kodlayacakları abstract metodlar aşağıda*/
    //save metodu
    abstract void save_(T entity) throws SQLException;

    //Entity veritabanında mevcut mu?
    abstract T isHave_(T entity) throws SQLException;

    //tüm Kayıtlari listele
    abstract ArrayList<T> getAll_() throws SQLException;

    //güncelleme
    abstract void update_(T entity, int id) throws SQLException;

    //İd ile tüm entitynin getirilmesi
    abstract T getById_(int id) throws SQLException;
    
    
    //Entity veritabanında mevcut mu?
    abstract void delete_(int id) throws SQLException;
    
    
    /**
     * kaydetme işlemi için çağrılacak metodtur. aşağıda dikkat edilirse ayrıca
     * bir tane de save metodu bulunmakta kaydetme işini bun kaydet metodu
     * yönetir ve save metodunu çağırır esas işi aslında save metodu yapar.
     *
     * try-cath kalabalığından kurtarmak ve try-catch bloklarını sadece 1 kez
     * kullanmak için bu şekilde hem kaydet hem save metodu yazıldı. aksi
     * taktirde her sınıfın save metodunda try-catch kullanılacak kod sayısı
     * artacak okunabilirliği düşecek ve kodlar düzensiz görünecekti. oysa bu
     * şekilde sadece bu sınıfta ve sadece 1 defa try cath yazılmış oldu kod
     * hakimiyeti arttı.
     *
     * @param entity kaydedilecek entity
     * @return eğer kaydetme işlemi başarılı ise entitynin kendisi değilse null
     * olarak döner
     */
    public T save(T entity) {
        try {
            save_(entity);
            return entity;
        } catch (SQLException ex) {
            msgErrror("save", ex);
            return null;
        }
    }

    //bazı veriler verilerek kaydın veritabanında olup olmadığını döndürür,
    //örneğin kullanıcı adı ve şifre nin veritabanında kayıtlı olup olmadığı sorgulanır
    public T isHave(T entity) {
        try {
            return isHave_(entity);
        } catch (SQLException ex) {
            msgErrror("if database have entity", ex);
            return null;
        }
    }

    
    
    //veritabanındaki bir tabloda bulunan bütün kayıtları Array Liste çeker
    public ArrayList<T> getAll() {
        try {
            return getAll_();
        } catch (SQLException ex) {
            msgErrror("get all entity list", ex);
            return null;
        }
    }

    
    
    public T getById(int id) {
        try {
            return getById_(id);
        } catch (SQLException ex) {
            msgErrror("get entity by id", ex);
            return null;
        }
    }
    
    
    
    
    public void delete(int id) {
        try {
            delete_(id);
        } catch (SQLException ex) {
            msgErrror("delete", ex);
        }
    }

    
    
    public T update(T entity, int id) {
        try {
            update_(entity, id);
            return entity;
        } catch (SQLException ex) {
            msgErrror("update", ex);
            return null;
        }
    }
    

    
    
    private void msgErrror(String msg,SQLException ex){
        JOptionPane.showMessageDialog(null, "an error occured when "+msg + ex.getMessage());
    }
    

    
   
}
