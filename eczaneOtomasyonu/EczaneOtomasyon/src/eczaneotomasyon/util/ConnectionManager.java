package eczaneotomasyon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Bu sınıf veritabanı bağlantısını sağlar bağlatı postgreSql veir tabanı ile yapılacaktır.
 */
public class ConnectionManager {
   
    private Connection connection = null;
    
    
    /**
     * Connetion türünde yeni bir veri tabanı bağlantısı oluşturur. 
     * bu connection ile veri tabanında crud işlemleri gerçekleştirebiliriz.
     * @return 
     */
    private Connection connetToDatabase() {
        final String driverPath="org.postgresql.Driver";
        final String connectionPort="5432";
        final String database="eczaneotomasyon";
        final String loginName="postgres";
        final String password="1123";
        
        try {
            //driver clasını belirtiyoruz
            Class.forName(driverPath);
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:"+connectionPort+"/"+database, loginName, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("veri tabanına bağlanılamadı... hata kodu:" + ex.getMessage());
        }
        return connection;
    }
    
    
    
    /**
     * veritabanı bağlantısını oluşturan veriTabaninaBaglan() metodunu denetler
     * şöyleki bağlantının defalarca oluşturulmasını engellemek için bağlantı talep edildiğinde 
     * eğer bağlantı zaten varsa direk bağlantıyı aksi halde ise 
     * yeni bir bağlantıyı oluşturarak gönderir
     * 
     * @return veri tabanı bağlantısını return eder.
     */
    public Connection getConnection(){
        if(connection==null) connection=connetToDatabase();
        return connection;
    }
    
}
