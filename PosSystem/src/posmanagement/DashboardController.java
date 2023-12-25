
package posmanagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import posmanagement.Model.ProductData;
import posmanagement.Utils.WindowUtils;


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
    private TableColumn<ProductData, String> prod_table_brand;

    @FXML
    private TableColumn<ProductData, String> prod_table_cat;

    @FXML
    private TableColumn<ProductData, String> prod_table_id;

    @FXML
    private TableColumn<ProductData, String> prod_table_name;

    @FXML
    private TableColumn<ProductData, String> prod_table_price;

    @FXML
    private TableColumn<ProductData, String> prod_table_status;

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
    private TableView<ProductData> product_show_table;

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
    
    @FXML
    private Button order_inv_add;
    
    //ALert Variable =======================
    Alert alert;

 
    
    //OBERSRVALE PRODUCT DATA LIST=========
    private Connection conn;
    private PreparedStatement ps;
    ResultSet result;

    
    public ObservableList<ProductData> addProductListData(){
        ObservableList<ProductData> productList = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM productdb";
        conn = Connector.connectDb();
        try {
            ps = conn.prepareStatement(sql);            
            result = ps.executeQuery();
            
            //CREATE A OBJECT FROM PRODUCT DATA MODEL CLASS
            ProductData productD;
            
            while (result.next()) {
                productD = new ProductData(
                    result.getInt("product_id"), 
                    result.getString("category"), 
                    result.getString("brand"),
                    result.getString("product_name"),
                    result.getDouble("price"),
                    result.getString("status"), 
                    result.getString("image"), 
                    result.getDate("date")
                );
                
                //ADD PRODUCT TO PRODUCTLIST ARRAY=====
                productList.add(productD);
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erorr is:", e);
        }
        
        return productList;
        
    }
    
    
    //CREATE INSTANCE FOR ObservableList SHOW DATA===============
    
    private ObservableList<ProductData> addProductsList;
    
    public void showProductDataToTable(){
        //ADD PRODUCT ARRAY LIST TO THE INSTANCE=================
        addProductsList = addProductListData();
        //ADD DATA TO THE RESPECTIVE TABLE CELL============
        prod_table_id.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        prod_table_cat.setCellValueFactory(new PropertyValueFactory<>("category"));
        prod_table_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        prod_table_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        prod_table_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        prod_table_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        product_show_table.setItems(addProductsList);
    }
    
    
    
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
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get().equals(ButtonType.OK)){
            
            //HIDE THE DASHBORD AND RETURN TO LOGIN SCREN=====
            logout.getScene().getWindow().hide();
            
            //OPEN NEW WINDOW ===========
            WindowUtils windowUtils = new WindowUtils();
            windowUtils.loadWindow("/posmanagement/Start.fxml", "Login");
            
        }
    }
    
    
    
    
    
    //SCREEN SWITCHER FUNCTION=================================
    public void switchScreen(ActionEvent evt){
        if(evt.getSource() == nav_dash){
            //SET VISIBLE AND HIDDEN
            section_dashboard.setVisible(true);
            section_addProduct.setVisible(false);
            section_order.setVisible(false);
            
            //MAKE ACTIVE SELECTED===
            nav_dash.setStyle("-fx-background-color:linear-gradient(to right, #03A9F4, #073ABB);");
            //MAKE OTHER TRANSPARENT====
            nav_add_prod.setStyle("-fx-background-color: transparent");
            nav_order.setStyle("-fx-background-color: transparent");
            
        }else if(evt.getSource() == nav_add_prod) {
            //SET VISIBLE AND HIDDEN
            section_addProduct.setVisible(true);
            section_dashboard.setVisible(false);            
            section_order.setVisible(false);
            
            //MAKE ACTIVE SELECTED===
            nav_add_prod.setStyle("-fx-background-color:linear-gradient(to right, #03A9F4, #073ABB);");
            //MAKE OTHER TRANSPARENT====
            nav_dash.setStyle("-fx-background-color: transparent");
            nav_order.setStyle("-fx-background-color: transparent");
            
            //CALL THE METHOD FOPR SHOW DATA TO TABLE=======
            showProductDataToTable();
            
        }else if(evt.getSource() == nav_order) {
            //SET VISIBLE AND HIDDEN
            section_order.setVisible(true);
            section_addProduct.setVisible(false);
            section_dashboard.setVisible(false);
            
            nav_order.setStyle("-fx-background-color:linear-gradient(to right, #03A9F4, #073ABB);");
            //MAKE OTHER TRANSPARENT====
            nav_add_prod.setStyle("-fx-background-color: transparent");
            nav_dash.setStyle("-fx-background-color: transparent");
            
        } 
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //CALL THE METHOD FOPR SHOW DATA TO TABLE=======
        showProductDataToTable();
        
    }    
    
}
