
package eczaneotomasyon.controller;

import eczaneotomasyon.dao.DAOAbstract;
import eczaneotomasyon.dao.IlacDao;
import eczaneotomasyon.model.EntityInterface;
import eczaneotomasyon.model.Ilac;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


public class IlacController extends BaseController implements Initializable  {

    

    @FXML
    private TextField txtAdi,txtTipi, txtUygulamaSekli,txtTanimi,txtFiyat, txtDoz;

  
    private final DAOAbstract<Ilac> dao;

    public IlacController() {
        dao = new IlacDao();
    }

    
    
    
    private void setBtnDisableds() {
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnUpdate.setDisable(true);
    }

    @FXML
    private void save() {

        setBtnDisableds();
        //eğer ad ve soyad boş değilse
        if (isError()) {
            errorMsg();
            return;
        }

        //eğer kaydetme esnasında hata oluşmadıysa
        if (dao.save(fromForm()) != null) {
            refreshTable();
            clearFormFields();
        }
    }

    /**
     * Güncelleme işlemini gerçekleştirir
     *
     */
    @FXML
    private void update() {
        if (isError()) {
            errorMsg();
            return;
        }

        //eğer güncelleme esnasında hata oluşmadıysa
        if (dao.update(fromForm(), getID()) != null) {
            refreshTable();
            clearFormFields();
        }
    }

    /**
     * Veritanından kayıt siler
     *
     */
    @FXML
    private void delete() {
        dao.delete(getID());
        refreshTable();
        clearFormFields();
    }

    /*  
    
    kutuları boşaltır
     */
    @FXML
    private void cancel() {
        clearFormFields();
    }
    

    private void refreshTable() {
        ArrayList<Ilac> list = dao.getAll();
        table.getItems().clear();
        table.getItems().addAll(list);
        table.refresh();
    }

    private void setBtnDisableds2() {
        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnCancel.setDisable(false);
        btnUpdate.setDisable(false);
    }
    
    

    //tablodaki alanı form kutucuklarına doldurur
    @FXML
    void toForm() {
        setBtnDisableds2();
        Ilac entity = (Ilac) table.getSelectionModel().getSelectedItem();
        if (entity != null) {
            txtID.setText(String.valueOf(entity.getId()));
            txtAdi.setText(entity.getIAdi());
            txtTipi.setText(entity.getTipi());
            txtTanimi.setText(entity.getTanimi());
            txtFiyat.setText(String.valueOf(entity.getFiyat()));
            txtDoz.setText(String.valueOf(entity.getDoz()));
            txtUygulamaSekli.setText(entity.getUygulamaSekli());
        }
    }

    /**
     * Bu metod form elemanlarını çekerek bir entity oluşturur.
     */
     
    @Override
    Ilac fromForm() {
        return new Ilac(txtAdi.getText(), txtTipi.getText(), txtTanimi.getText(),Integer.valueOf(txtFiyat.getText()), Float.valueOf(txtDoz.getText()), txtUygulamaSekli.getText());
    }

    /**
     * Bu metod form kutucuklarına girilen verilerde hatalı girişler olup
     * olmadığını tespit eder
     *
     * @return hata varsa true yoksa false
     */
    boolean isError() {
        //eğer ad girilmemişse
        if (txtDoz.getText().trim().isEmpty()) {
            return true;
        }
        //eğer soyad girilmemişse
        if (txtAdi.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Show the error message
     *
     * @return always true
     */
    boolean errorMsg() {
        JOptionPane.showMessageDialog(null, "Form components (Ilac adı ve Doz Miktarı) cant be empty..  !!");
        return true;
    }

    private void setBtnDisableds3() {
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnUpdate.setDisable(true);
    }

    /**
     * Form alanlarını temizler
     */
    void clearFormFields() {
        setBtnDisableds3();
        txtID.setText("");
        txtAdi.setText("");
        txtTipi.setText("");
        txtTanimi.setText("");
        txtFiyat.setText("");
        txtDoz.setText("");
        txtUygulamaSekli.setText("");


    }
     
  

    
    //form ilk açıldığında yapılması istenen işlemler
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTableColumn();
        refreshTable();
    }

    
    
      /* IAdi varchar(100) NOT NULL,
	tipi varchar(30) , 
	tanimi varchar (300) ,
	fiyat int ,
	doz float ,
	uygulamaSekli varchar(200) */

    void createTableColumn() {
        //Col Names
        TableColumn<EntityInterface, String> IAdi = new TableColumn<>("Ilac Adı");
        TableColumn<EntityInterface, String> tipi = new TableColumn<>("Tipi");
        TableColumn<EntityInterface, String> tanimi = new TableColumn<>("Tanımı");
        TableColumn<EntityInterface, String> fiyat = new TableColumn<>("Fiyat");
        TableColumn<EntityInterface, String> doz = new TableColumn<>("Doz");
        TableColumn<EntityInterface, String> uygulamaSekli = new TableColumn<>("Uygulama Şekli");

        //bind columns with entity values
        IAdi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("IAdi"));
        tipi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("tipi"));
        tanimi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("tanimi"));
        fiyat.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("fiyat"));
        doz.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("doz"));
        uygulamaSekli.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("uygulamaSekli"));

        //add columns to table
        table.getColumns().add(IAdi);
        table.getColumns().add(tipi);
        table.getColumns().add(tanimi);
        table.getColumns().add(fiyat);
        table.getColumns().add(doz);
        table.getColumns().add(uygulamaSekli);

    }

   

   
}




