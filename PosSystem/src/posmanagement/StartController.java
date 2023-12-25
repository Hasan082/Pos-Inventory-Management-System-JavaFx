
package posmanagement;

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
import javafx.scene.control.Alert;
import posmanagement.Utils.AlertUtils;
import posmanagement.Utils.WindowUtils;


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
    
    //DATABASE VARAIABLE========
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
                //SHOW ALERT FOR MISSING FILED====
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Login Error", null, "Please enter both username and password.");
                
            } else {
                if(result.next()){                    
                    //IF LOGIN INFO CORRECT, SHOW ALERT===
                    AlertUtils.showAlert(Alert.AlertType.INFORMATION, "INFORMATION", null, "Login Success!!");
                    
                    //CLOSE LOGIN SCREEN==========
                    loginBtn.getScene().getWindow().hide(); 
                    
                    //OPEN NEW WINDOW ===========
                    WindowUtils windowUtils = new WindowUtils();
                    windowUtils.loadWindow("/posmanagement/dashboard.fxml", "Dashboard");

                } else {
                    //IF LOGIN INFO WRONG====
                    AlertUtils.showAlert(Alert.AlertType.ERROR, "ERROR MESSAGE", null, "Wrong username or password!");
                    
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database: {0}", e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
