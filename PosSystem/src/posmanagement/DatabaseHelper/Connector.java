
package posmanagement.DatabaseHelper;


import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Connector {
    
   private static final Logger logger = Logger.getLogger(Connector.class.getName());
   
   
    public static Connection connectDb() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/posmanagement", "root", "");
            logger.info("Database Connected Succesfully");
            return  conn;
        } catch(SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database: {0}", e.getMessage());
            return  null;
        }        
    }
}
