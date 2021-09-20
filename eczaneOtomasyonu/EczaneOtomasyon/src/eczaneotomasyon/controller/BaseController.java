package eczaneotomasyon.controller;

import eczaneotomasyon.model.EntityInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Bütün controller sınıflarında ortak olan değişken ve metodlar kod tekrarının
 * azaltılması kod hakimiyetinin sağlanması tek yerden kontrol amacıyla yani
 * Nesne tabanlı tasarımın temellerinden olan SOLID prensiplerinden Single
 * Responsibility nin sağlanması amacıyla diğer controller sınıflarının extend
 * edeceği bu sınıfta toplanmıştır
 *
 *
 */
abstract class BaseController {

    @FXML
    protected Button btnSave, btnUpdate, btnDelete, btnCancel;
    @FXML
    protected TableView<EntityInterface> table;
    @FXML
    protected TextField txtID;


    //abstract methods
    abstract boolean isError(); 
    abstract boolean errorMsg();
    abstract void clearFormFields();
    abstract void createTableColumn();
    abstract EntityInterface  fromForm();
    
    
    //
    protected int getID() {
        return Integer.valueOf(txtID.getText().trim());
    }

    protected void changeButtons(boolean state) {
        btnSave.setDisable(state);
        btnDelete.setDisable(!state);
        btnUpdate.setDisable(!state);
        btnCancel.setDisable(!state);
    }
    

    protected boolean isNumber(String text){
        try {
            Double.valueOf(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
