/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package posmanagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hasan
 */
public class DashboardController implements Initializable {
    
    private static final Logger logger = Logger.getLogger(DashboardController.class.getName());
    
    

    @FXML
    private Button close;

    @FXML
    private BarChart<?, ?> dash_order_chart;

    @FXML
    private Label dash_product;

    @FXML
    private BarChart<?, ?> dash_reveniew_chart;

    @FXML
    private Label dash_revienue;

    @FXML
    private Label dash_today_order;

    @FXML
    private Button import_img;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_wrapper;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private Button nav_add_prod;

    @FXML
    private Button nav_dash;

    @FXML
    private Button nav_order;

    @FXML
    private TextField order_inv_amount;

    @FXML
    private Label order_inv_balance;

    @FXML
    private Button order_inv_balance_btn;

    @FXML
    private ComboBox<?> order_inv_brand;

    @FXML
    private ComboBox<?> order_inv_cat;

    @FXML
    private ComboBox<?> order_inv_name;

    @FXML
    private Button order_inv_pay_btn;

    @FXML
    private Spinner<?> order_inv_qty;

    @FXML
    private Button order_inv_reset_btn;

    @FXML
    private Label order_inv_total;

    @FXML
    private TableView<?> order_table;

    @FXML
    private TableColumn<?, ?> order_table_brand;

    @FXML
    private TableColumn<?, ?> order_table_cat;

    @FXML
    private TableColumn<?, ?> order_table_name;

    @FXML
    private TableColumn<?, ?> order_table_price;

    @FXML
    private TableColumn<?, ?> order_table_quantity;

    @FXML
    private Button prod_add_btn;

    @FXML
    private Button prod_detete_btn;

    @FXML
    private Button prod_reset_btn;

    @FXML
    private TableColumn<?, ?> prod_table_brand;

    @FXML
    private TableColumn<?, ?> prod_table_cat;

    @FXML
    private TableColumn<?, ?> prod_table_id;

    @FXML
    private TableColumn<?, ?> prod_table_name;

    @FXML
    private TableColumn<?, ?> prod_table_price;

    @FXML
    private TableColumn<?, ?> prod_table_status;

    @FXML
    private Button prod_update_btn;

    @FXML
    private TextField product_brand;

    @FXML
    private ComboBox<?> product_cat;

    @FXML
    private TextField product_id;

    @FXML
    private ImageView product_img;

    @FXML
    private TextField product_name;

    @FXML
    private TextField product_price;

    @FXML
    private TextField product_search;

    @FXML
    private TableView<?> product_show_table;

    @FXML
    private ComboBox<?> product_status;

    @FXML
    private AnchorPane section_addProduct;

    @FXML
    private AnchorPane section_dashboard;

    @FXML
    private AnchorPane section_order;

    @FXML
    private Label username;
    
    
    //ALert Variable =======================
    Alert alert;
    
    //SCENE MOVE VARAIABLE
    private double x = 0;
    private double y = 0;
    
    
    //CLOSE FUNCTION======
    public  void  close() {
        System.exit(0);
    }
    
    //MINIMIZE FUNCTION====
    public  void  minimize() {
        Stage stage = (Stage) main_wrapper.getScene().getWindow();
        stage.setIconified(true);
    }
    
    //LOGOUT FUNCTION=====
    public void logout(){
        try {
            //IF LOGIN INFO CORRECT, SHOW ALERT===
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText(null);
            alert.setContentText("Logout Success!!");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                
                logout.getScene().getWindow().hide(); 
                
                Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));         
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                root.setOnMousePressed((MouseEvent evt) -> {
                    x = evt.getSceneX();
                    y = evt.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent evt)->{
                    stage.setX(evt.getScreenX() - x);
                    stage.setY(evt.getScreenY()- y);
                    stage.setOpacity(0.9);
                });

                root.setOnMouseReleased((MouseEvent evt)->{
                    stage.setOpacity(01);
                });
                    
                stage.setScene(scene);
                stage.show();
                
            } else {
                return;
            }
            
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error connecting to the database: {0}", e.getMessage());
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
