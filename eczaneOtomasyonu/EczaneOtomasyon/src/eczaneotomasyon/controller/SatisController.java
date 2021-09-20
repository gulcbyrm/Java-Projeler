package eczaneotomasyon.controller;

import eczaneotomasyon.dao.DAOAbstract;
import eczaneotomasyon.dao.IlacDao;
import eczaneotomasyon.dao.MusteriDAO;
import eczaneotomasyon.dao.SatisDao;
import eczaneotomasyon.model.EntityInterface;
import eczaneotomasyon.model.Ilac;
import eczaneotomasyon.model.Musteri;
import eczaneotomasyon.model.Satis;
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

public class SatisController extends BaseController implements Initializable {

    @FXML
    private TextField txtSatisTarihi;

    @FXML
    private ComboBox<Musteri> cmbMusteri;

    @FXML
    private ComboBox<Ilac> cmbIlac;

    private final DAOAbstract<Satis> dao;

    public SatisController() {
        dao = new SatisDao();

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
        ArrayList<Satis> list = dao.getAll();
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
        Satis entity = (Satis) table.getSelectionModel().getSelectedItem();
        if (entity != null) {
            txtID.setText(String.valueOf(entity.getId()));
            txtSatisTarihi.setText(entity.getSatisTarihi());
            cmbMusteri.setValue(entity.getMusteri());
            cmbIlac.setValue(entity.getIlac());
        }
    }

    /**
     * Bu metod form elemanlarını çekerek bir entity oluşturur.
     */
    @Override
    Satis fromForm() {
        return new Satis(txtSatisTarihi.getText(), cmbMusteri.getValue(), cmbIlac.getValue());
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
        txtSatisTarihi.setText("");
        cmbMusteri.setValue(null);
        cmbIlac.setValue(null);

    }

    // musteri comboboxı doldurur
    private void fillComboboxMusteri() {
        List<Musteri> musteriList = new MusteriDAO().getAll();
        cmbMusteri.getItems().clear();
        cmbMusteri.getItems().addAll(musteriList);
    }
    //ilac comboboxı doldurur

    private void fillComboboxIlac() {
        List<Ilac> ilacList = new IlacDao().getAll();
        cmbIlac.getItems().clear();
        cmbIlac.getItems().addAll(ilacList);
    }

    //form ilk açıldığında yapılması istenen işlemler
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTableColumn();
        refreshTable();
        fillComboboxMusteri();
        fillComboboxIlac();
    }

    void createTableColumn() {
        //Col Names
        TableColumn<EntityInterface, String> satisTarihi = new TableColumn<>("Satış Tarihi");
        TableColumn<EntityInterface, String> musteriID = new TableColumn<>("Müşteri ");
        TableColumn<EntityInterface, String> ilacID = new TableColumn<>("Ilac ");

        //bind columns with entity values
        satisTarihi.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("satisTarihi"));
        musteriID.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("musteri"));
        ilacID.setCellValueFactory(new PropertyValueFactory<EntityInterface, String>("ilac"));

        //add columns to table
        table.getColumns().add(satisTarihi);
        table.getColumns().add(musteriID);
        table.getColumns().add(ilacID);

    }

    @Override
    boolean isError() {
        if (txtSatisTarihi.getText().trim().isEmpty()) {
            return true;
        }
        return cmbIlac.getValue() == null;
    }

    @Override
    boolean errorMsg() {
        JOptionPane.showMessageDialog(null, "Form components (Satış Tarihi ) cant be empty..  !!");
        return true;
    }

}
