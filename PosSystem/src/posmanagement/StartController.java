
package posmanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class StartController implements Initializable {
    
    private static final Logger logger = Logger.getLogger(Connector.class.getName());
    
    @FXML
    private Button closeIcon;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    //CLOSE THE WONDOW USING CLOSE BUTTON====
    public void close(){
        System.exit(0);
    }
    
    
    //DATABASE VARAIABLE
    private Connection conn;
    private PreparedStatement ps;
    ResultSet result;
    Alert alert;
    
    public void loginAdmin(){
        
        //DATABASE CONNECT=====
        String sqlQry = "SELECT * FROM admin WHERE username = ? and password = ?";
        conn = Connector.connectDb();
        
        //GET LOGIN INFO FROM DATABASE====
        String userName = username.getText();
        String passWord = password.getText();
        
        try {
            
            ps = conn.prepareStatement(sqlQry);            
            ps.setString(1, userName);
            ps.setString(2, passWord);
            result = ps.executeQuery();
            
            
            if(userName.isEmpty() || passWord.isEmpty()){
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Fill All Field");
                alert.showAndWait();
                
            } else {
                if(result.next()){
                    
                    //IF LOGIN INFO CORRECT===
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Success!!");
                    alert.showAndWait();
                    
                    //CLOSE LOGIN SCREEN
                    loginBtn.getScene().getWindow().hide(); 
                    
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    //OPEN DASHBOARD SCREEN
                    stage.setScene(scene);
                    stage.show();

                } else {
                    
                    //IF LOGIN INFO WRONG====
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                    
                }
            }
            
            
        } catch (IOException | SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database: {0}", e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
