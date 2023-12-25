package posmanagement.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WindowUtils {

    private static final Logger logger = Logger.getLogger(WindowUtils.class.getName());

    private double x = 0;
    private double y = 0;

    public void loadWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Make the window draggable
            root.setOnMousePressed((MouseEvent evt) -> {
                x = evt.getSceneX();
                y = evt.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent evt) -> {
                stage.setX(evt.getScreenX() - x);
                stage.setY(evt.getScreenY() - y);
                stage.setOpacity(0.9);
            });

            root.setOnMouseReleased((MouseEvent evt) -> {
                stage.setOpacity(1);
            });

            // Remove system-generated close or minimize
            stage.initStyle(StageStyle.TRANSPARENT);

            // Set the window title
            stage.setTitle(title);

            // Open the window
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading FXML file: {0}", e.getMessage());
        }
    }
}
