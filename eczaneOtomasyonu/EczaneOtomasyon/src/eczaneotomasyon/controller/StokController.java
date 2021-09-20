
package eczaneotomasyon.controller;

import eczaneotomasyon.dao.DAOAbstract;
import eczaneotomasyon.dao.EczaneDao;
import eczaneotomasyon.dao.IlacDao;
import eczaneotomasyon.dao.StokDao;
import eczaneotomasyon.model.Eczane;
import eczaneotomasyon.model.EntityInterface;
import eczaneotomasyon.model.Ilac;
import eczaneotomasyon.model.Stok;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 */

public class StokController extends BaseController implements Initializable {

    
    @FXML
    private ComboBox<Eczane> cmbEczane;

    @FXML
    private ComboBox<Ilac> cmbIlac;

    @FXML
    private TextField txtSAFirma, txtMiktari,txtSonKulTarihi;
    private final DAOAbstract<Stok> dao;

    public StokController() {
        dao = new StokDao();
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
        ArrayList<Stok> list = dao.getAll();
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
        Stok entity = (Stok) table.getSelectionModel().getSelectedItem();
        if (entity != null) {
            txtID.setText(String.valueOf(entity.getId()));
            txtSAFirma.setText(entity.getSatinAlindigiFirma());
            txtMiktari.setText(String.valueOf(entity.getMiktar()));
            txtSonKulTarihi.setText(entity.getSonKullanmaTarihi());
           cmbIlac.setValue(entity.getIlac());
            cmbEczane.setValue(entity.getEczane());
        }
    }

    /**
     * Bu metod form elemanlarını çekerek bir entity oluşturur.
     */
     
    @Override
    Stok fromForm() {
        return new Stok(txtSAFirma.getText(),txtSonKulTarihi.getText(), Integer.valueOf(txtMiktari.getText()),cmbIlac.getValue(),cmbEczane.getValue());
    }

    /**
     * Bu metod form kutucuklarına girilen verilerde hatalı girişler olup
     * olmadığını tespit eder
     *
     * @return hata varsa true yoksa false
     */
    boolean isError() {
        //eğer ad girilmemişse
        if (txtSonKulTarihi.getText().trim().isEmpty()) return true;
            return cmbEczane.getValue()==null;
          
    }

    /**
     * Show the error message
     *
     * @return always true
     */
    boolean errorMsg() {
        JOptionPane.showMessageDialog(null, "Form components (Son Kullanma Tarihi ve Eczane Id ) cant be empty..  !!");
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
        txtSAFirma.setText("");
        txtMiktari.setText("");
        txtSonKulTarihi.setText("");
        cmbEczane.setValue(null);
        cmbIlac.setValue(null);

    }
     
  
    
    //comboboxı doldurur
    private void fillComboboxEczane(){
        List<Eczane> eczaneList = new EczaneDao().getAll();
        cmbEczane.getItems().clear();
        cmbEczane.getItems().addAll(eczaneList);
    }
    
      //comboboxı doldurur
    private void fillComboboxIlac(){
        List<Ilac> ilacList = new IlacDao().getAll();
        cmbIlac.getItems().clear();
        cmbIlac.getItems().addAll(ilacList);
    }
    
    //form ilk açıldığında yapılması istenen işlemler
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTableColumn();
        refreshTable();
        fillComboboxEczane();
        fillComboboxIlac();
    }
    
    
    
    

    void createTableColumn() {
        //Col Names
        TableColumn<EntityInterface, String> satinAlindigiFirma = new TableColumn<>("Satın Alındığı Firma");
        TableColumn<EntityInterface, String> miktari = new TableColumn<>("Miktarı");
        TableColumn<EntityInterface, String> sonKullanmaTarihi = new TableColumn<>("Son Kullanma Tarihi");
        TableColumn<EntityInterface, String> ilacID = new TableColumn<>("Ilac");
        TableColumn<EntityInterface, String> eczaneID = new TableColumn<>("Eczane");

        //bind columns with entity values
        satinAlindigiFirma.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("satinAlindigiFirma"));
        miktari.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("miktar"));
        sonKullanmaTarihi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("sonKullanmaTarihi"));
        ilacID.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("ilac"));
        eczaneID.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("eczane"));

        //add columns to table
        table.getColumns().add(satinAlindigiFirma);
        table.getColumns().add(miktari);
        table.getColumns().add(sonKullanmaTarihi);
        table.getColumns().add(ilacID);
        table.getColumns().add(eczaneID);

    }

}
 