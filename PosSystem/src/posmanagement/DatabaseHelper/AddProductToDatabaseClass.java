
package posmanagement.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import posmanagement.Utils.AlertUtils;

public class AddProductToDatabaseClass {

    private static final Logger logger = Logger.getLogger(AddProductToDatabaseClass.class.getName());

    public static boolean insertProduct(String prodId, String prodCat, String prodBnd,
                                        String prodNm, String prodPrc, String prodSts, String uri) {
        Connection conn = Connector.connectDb();
        PreparedStatement ps = null;

        String sqlInsert = "INSERT INTO productdb (product_id, category, brand, product_name, price, status, image, date) VALUES(?,?,?,?,?,?,?,?)";

        try {
            if (uri != null) {
                uri = uri.replace("\\", "\\\\");
            }

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            if (!prodId.isEmpty() && prodCat != null && !prodBnd.isEmpty() && !prodNm.isEmpty()
                    && !prodPrc.isEmpty() && prodSts != null && uri != null && !uri.isEmpty()) {
                ps = conn.prepareStatement(sqlInsert);

                ps.setString(1, prodId);
                ps.setString(2, prodCat);
                ps.setString(3, prodBnd);
                ps.setString(4, prodNm);
                ps.setString(5, prodPrc);
                ps.setString(6, prodSts);
                ps.setString(7, uri);
                ps.setString(8, String.valueOf(sqlDate));

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0; // Return true if at least one row was affected
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "ERROR MESSAGE", null, "Please fill The Form Correctly!");
                return false; // Return false if validation fails
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error", e);
            return false; // Return false if an exception occurs
        } finally {
            // Close resources in a finally block
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing resources", e);
            }
        }
    }
}
