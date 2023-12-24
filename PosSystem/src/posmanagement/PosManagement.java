
package posmanagement;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import posmanagement.Utils.WindowUtils;


public class PosManagement extends Application {

    
    @Override
    public void start(Stage stage) throws Exception{
        
        //OPEN NEW WINDOW ===========
        WindowUtils windowUtils = new WindowUtils();
        windowUtils.loadWindow("/posmanagement/Start.fxml", "Dashboard");

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
