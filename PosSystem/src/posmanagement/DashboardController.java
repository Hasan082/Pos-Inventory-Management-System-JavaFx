
package posmanagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import posmanagement.Utils.WindowUtils;


public class DashboardController implements Initializable {  
    

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
        //IF LOGIN INFO CORRECT, SHOW ALERT===
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get().equals(ButtonType.OK)){
            
            logout.getScene().getWindow().hide();
            
            //OPEN NEW WINDOW ===========
            WindowUtils windowUtils = new WindowUtils();
            windowUtils.loadWindow("/posmanagement/Start.fxml", "Login");
            
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
