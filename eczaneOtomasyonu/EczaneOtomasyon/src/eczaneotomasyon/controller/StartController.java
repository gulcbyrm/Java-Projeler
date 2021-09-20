package eczaneotomasyon.controller;

import eczaneotomasyon.util.ShowForm;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class StartController {

   

    @FXML
    void showEczane(MouseEvent event) {
        showForm("eczane");

    }

    @FXML
    void showIlac(MouseEvent event) {
        showForm("ilac");
    }

    @FXML
    void showKimlik(MouseEvent event) {
        showForm("kimlik");
    }

    @FXML
    void showMusteri(MouseEvent event) {
        showForm("musteri");
    }

    @FXML
    void showSatis(MouseEvent event) {
        showForm("satis");
    }

    @FXML
    void showStok(MouseEvent event) {
        showForm("stok");
    }
    
      private void showForm(String fxmlFile){
        new ShowForm().showForm(new Stage(), "/eczaneotomasyon/view/"+fxmlFile+".fxml", false).showAndWait();
    }
    
    
    

    @FXML
    private void exit(MouseEvent event) {
        if(JOptionPane.showConfirmDialog(null, "Are You Sure?  Aborted The Program","EXIT",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
        System.exit(0);
    }
    
    

}
