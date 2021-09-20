package eczaneotomasyon.controller;

import eczaneotomasyon.dao.DAOAbstract;
import eczaneotomasyon.dao.KimlikDao;
import eczaneotomasyon.dao.MusteriDAO;
import eczaneotomasyon.model.EntityInterface;
import eczaneotomasyon.model.Kimlik;
import eczaneotomasyon.model.Musteri;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;




/**
 * FXML Controller class
 *
 */
public class MusteriController extends BaseController implements Initializable {

    @FXML
    private TextField txtID, txtTarih, txtHastalikAdi, txtSigortaNo, txtDurum;
    @FXML
    private TextArea txtSAglikGuvencesiTuru;
    @FXML
    private ComboBox<Kimlik> cmbKimlik;

    private final DAOAbstract<Musteri> dao;

    public MusteriController() {
        dao = new MusteriDAO();
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
        ArrayList<Musteri> list = dao.getAll();
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
        Musteri entity = (Musteri) table.getSelectionModel().getSelectedItem();
        if (entity != null) {
            txtID.setText(String.valueOf(entity.getId()));
            txtHastalikAdi.setText(entity.getHastalikAdi());
            txtTarih.setText(entity.getSatinAlinanTarih());
            txtDurum.setText(entity.getDurum());
            txtSigortaNo.setText(String.valueOf(entity.getSigortaNo()));
            txtSAglikGuvencesiTuru.setText(entity.getSaglikGuvenceTuru());
            cmbKimlik.setValue(entity.getKimlikID());
        }
    }

    /**
     * Bu metod form elemanlarını çekerek bir entity oluşturur.
     */
    @Override
    Musteri fromForm() {
        return new Musteri(txtTarih.getText(), txtHastalikAdi.getText(), Integer.valueOf(txtSigortaNo.getText()), txtSAglikGuvencesiTuru.getText(), txtDurum.getText(), cmbKimlik.getValue());
    }

    /**
     * Bu metod form kutucuklarına girilen verilerde hatalı girişler olup
     * olmadığını tespit eder
     *
     * @return hata varsa true yoksa false
     */
    boolean isError() {
        //eğer ad girilmemişse
        if (txtHastalikAdi.getText().trim().isEmpty()) return true;
            return cmbKimlik.getValue()==null;
    
    }

    /**
     * Show the error message
     *
     * @return always true
     */
    boolean errorMsg() {
        JOptionPane.showMessageDialog(null, "Form components (Hastalık Adi ve Kimlik Id ) cant be empty..  !!");
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
        txtHastalikAdi.setText("");
        txtTarih.setText("");
        txtDurum.setText("");
        txtSAglikGuvencesiTuru.setText("");
        txtSigortaNo.setText("");
        cmbKimlik.setValue(null);

    }
     
  
    
    //comboboxı doldurur
    private void fillComboboxKimlik(){
        List<Kimlik> kimlikList = new KimlikDao().getAll();
        cmbKimlik.getItems().clear();
        cmbKimlik.getItems().addAll(kimlikList);
    }
    
    //form ilk açıldığında yapılması istenen işlemler
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTableColumn();
        refreshTable();
        fillComboboxKimlik();
    }

    void createTableColumn() {
        //Col Names
        TableColumn<EntityInterface, String> satinAlinanTarih = new TableColumn<>("Satın Alınan Tarih");
        TableColumn<EntityInterface, String> hastalikAdi = new TableColumn<>("Hastalık Adı");
        TableColumn<EntityInterface, String> sigortaNo = new TableColumn<>("Sigorta No");
        TableColumn<EntityInterface, String> saglikGuvenceTuru = new TableColumn<>("Sağlık Güvence Türü");
        TableColumn<EntityInterface, String> durum = new TableColumn<>("Durum");//emeklimi vs
        TableColumn<EntityInterface, String> kimlik = new TableColumn<>("Kimlik");

        //bind columns with entity values
        satinAlinanTarih.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("satinAlinanTarih"));
        hastalikAdi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("hastalikAdi"));
        sigortaNo.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("sigortaNo"));
        saglikGuvenceTuru.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("saglikGuvenceTuru"));
        durum.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("durum"));
        kimlik.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("kimlikID"));

        //add columns to table
        table.getColumns().add(satinAlinanTarih);
        table.getColumns().add(hastalikAdi);
        table.getColumns().add(sigortaNo);
        table.getColumns().add(saglikGuvenceTuru);
        table.getColumns().add(durum);
        table.getColumns().add(kimlik);

    }

}
