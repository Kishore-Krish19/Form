package form;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Kishore E
 */
public class StudentForm extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception  {
//        System.out.println(getClass().getResource("Std_Form.fxml")); // Debug check
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Std_Form.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("Student Form");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
