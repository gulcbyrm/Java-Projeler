
package eczaneotomasyon.controller;

import eczaneotomasyon.dao.DAOAbstract;
import eczaneotomasyon.dao.EczaneDao;
import eczaneotomasyon.model.Eczane;
import eczaneotomasyon.model.EntityInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


public class EczaneController extends BaseController implements Initializable {
    
    @FXML
    private TextField txtAdi,txtKurulusTarihi, txtKiminAdinaKayitli;
  private final DAOAbstract<Eczane> dao;

    public EczaneController() {
        dao = new EczaneDao();
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
        ArrayList<Eczane> list = dao.getAll();
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
        Eczane entity = (Eczane) table.getSelectionModel().getSelectedItem();
        if (entity != null) {
            txtID.setText(String.valueOf(entity.getId()));
            txtAdi.setText(entity.getEAdi());
            txtKurulusTarihi.setText(entity.getKurulusTarihi());
            txtKiminAdinaKayitli.setText(entity. getKiminAdinaKayitli());
        }
    }

    /**
     * Bu metod form elemanlarını çekerek bir entity oluşturur.
     */
     
    @Override
    Eczane fromForm() {
        return new Eczane(txtAdi.getText(), txtKurulusTarihi.getText(), txtKiminAdinaKayitli.getText());
    }

    /**
     * Bu metod form kutucuklarına girilen verilerde hatalı girişler olup
     * olmadığını tespit eder
     *
     * @return hata varsa true yoksa false
     */
    boolean isError() {
        //eğer ad girilmemişse
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
        JOptionPane.showMessageDialog(null, "Form components (Eczane Adı) cant be empty..  !!");
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
        txtKurulusTarihi.setText("");
        txtKiminAdinaKayitli.setText("");
    
    }
     
  

    
    //form ilk açıldığında yapılması istenen işlemler
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTableColumn();
        refreshTable();
    }

  

    void createTableColumn() {
        //Col Names
        TableColumn<EntityInterface, String> EAdi = new TableColumn<>("Eczane Adı");
        TableColumn<EntityInterface, String> kurulusTarihi = new TableColumn<>("Kuruluş Tarihi");
        TableColumn<EntityInterface, String> kiminAdinaKayitli = new TableColumn<>("Kimin Adına Kayıtlı");
       

        //bind columns with entity values
        EAdi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("EAdi"));
        kurulusTarihi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("kurulusTarihi"));
        kiminAdinaKayitli.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("kiminAdinaKayitli"));
       

        //add columns to table
        table.getColumns().add(EAdi);
        table.getColumns().add(kurulusTarihi);
        table.getColumns().add(kiminAdinaKayitli);
       
    }
   
}
