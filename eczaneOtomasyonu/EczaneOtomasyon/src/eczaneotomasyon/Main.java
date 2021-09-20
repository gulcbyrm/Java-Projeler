package eczaneotomasyon;


import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.stage.Stage;
import eczaneotomasyon.util.ShowForm;

 
public class Main extends Application{
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         new ShowForm().showForm(new Stage(), "/eczaneotomasyon/view/start.fxml", true).show();
    }
    
}
