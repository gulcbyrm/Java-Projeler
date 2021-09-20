
package eczaneotomasyon.controller;

import eczaneotomasyon.dao.DAOAbstract;
import eczaneotomasyon.dao.KimlikDao;
import eczaneotomasyon.model.EntityInterface;
import eczaneotomasyon.model.Kimlik;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


public class KimlikController  extends BaseController implements Initializable  {
    
    
    
    @FXML
    private TextField txtadi,txtSoyad, txtTc, txtDogumTarihi, txtDogumYeri;
    @FXML
    private Button btnSave, btnUpdate,btnDelete,btnCancel;
    @FXML
    private final DAOAbstract<Kimlik> dao;


     public KimlikController() {
        dao = new KimlikDao();
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
        ArrayList<Kimlik> list = dao.getAll();
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
        Kimlik entity = (Kimlik) table.getSelectionModel().getSelectedItem();
        if (entity != null) {
            txtID.setText(String.valueOf(entity.getId()));
            txtadi.setText(entity.getKAdi());
            txtSoyad.setText(entity.getKSoyadi());
            txtTc.setText(entity.getKimlikTC());
            txtDogumTarihi.setText(entity.getDogumTarihi());
            txtDogumYeri.setText(entity.getDogumYeri());
        }
    }

    /**
     * Bu metod form elemanlarını çekerek bir entity oluşturur.
     */
    @Override
    Kimlik fromForm() {
        return new Kimlik(txtadi.getText(), txtSoyad.getText(), txtTc.getText(), txtDogumTarihi.getText(), txtDogumYeri.getText());
    }

    /**
     * Bu metod form kutucuklarına girilen verilerde hatalı girişler olup
     * olmadığını tespit eder
     *
     * @return hata varsa true yoksa false
     */
    boolean isError() {
        //eğer tc girilmemişse
        if (txtTc.getText().trim().isEmpty()) {
            return true;
        }
        //eğer ad girilmemişse
        if (txtadi.getText().trim().isEmpty()) {
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
        JOptionPane.showMessageDialog(null, "Form components (TC  ve Adi) cant be empty..  !!");
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
        txtadi.setText("");
        txtSoyad.setText("");
        txtTc.setText("");
        txtDogumTarihi.setText("");
        txtDogumYeri.setText("");

    }
     
  
    

    //form ilk açıldığında yapılması istenen işlemler
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTableColumn();
        refreshTable();
    }
    
    
    /*KAdi varchar(30) NOT NULL,
	KSoyadi varchar(30)  ,
	KimlikTC varchar(11)  ,
	dogumTarihi varchar(11)  ,
	dogumYeri varchar(100)  */

    void createTableColumn() {
        //Col Names
        TableColumn<EntityInterface, String> KAdi = new TableColumn<>("Adı");
        TableColumn<EntityInterface, String> KSoyadi = new TableColumn<>("Soyadı");
        TableColumn<EntityInterface, String> KimlikTC = new TableColumn<>("TC No");
        TableColumn<EntityInterface, String> dogumTarihi = new TableColumn<>("Doğum Tarihi");
        TableColumn<EntityInterface, String> dogumYeri = new TableColumn<>("Doğum Yeri");

        //bind columns with entity values
        KAdi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("KAdi"));
        KSoyadi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("KSoyadi"));
        KimlikTC.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("KimlikTC"));
        dogumTarihi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("dogumTarihi"));
        dogumYeri.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("dogumYeri"));

        //add columns to table
        table.getColumns().add(KAdi);
        table.getColumns().add(KSoyadi);
        table.getColumns().add(KimlikTC);
        table.getColumns().add(dogumTarihi);
        table.getColumns().add(dogumYeri);

    }
}
