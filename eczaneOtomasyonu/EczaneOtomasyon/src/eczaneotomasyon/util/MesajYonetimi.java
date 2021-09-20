
package eczaneotomasyon.util;

import javax.swing.JOptionPane;


public class MesajYonetimi {

    void hataMesaji(String title, String msj) {
        JOptionPane.showInternalMessageDialog(null,msj, title,JOptionPane.ERROR_MESSAGE);
    }
    
    
}
