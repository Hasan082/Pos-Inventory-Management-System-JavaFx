
package posmanagement;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hasan
 */
public class PosManagement extends Application {

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    
    private double x = 0;
    private double y = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root));
        
        root.setOnMousePressed((MouseEvent evt) -> {
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent evt)->{
            primaryStage.setX(evt.getScreenX() - x);
            primaryStage.setY(evt.getScreenY()- y);
            primaryStage.setOpacity(0.9);
        });
        
        root.setOnMouseReleased((MouseEvent evt)->{
            primaryStage.setOpacity(01);
        });
        
        
        
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
