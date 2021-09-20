
package eczaneotomasyon.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ShowForm {
    public static Stage primaryStage;
   /**
     * Bu metod bir stageyi görüntülenmesini sağlar. 
     * @param stage  a form(stage) for show 
     * @param path patf of fxml file uzantısı dahil ör: xxx.fxml
     * @return stage
     */
    public Stage showForm(Stage stage, String path, boolean isPrimary) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //if form is main form
            if(isPrimary){
                primaryStage = stage;
                stage.setX(250);
                stage.setY(80);
            }
            else{
                stage.initOwner(primaryStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setX(500);
                stage.setY(180);
            }
        } catch (Exception e) {
            System.out.println("error when loading the form details:"+e.getMessage());
            e.printStackTrace();
            Platform.exit();
        }
        return stage;
    }
}
