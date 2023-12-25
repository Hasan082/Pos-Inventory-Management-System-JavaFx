
package posmanagement.DatabaseHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import posmanagement.Model.ProductData;

public class ProductDataDAO {

    private static final Logger logger = Logger.getLogger(ProductDataDAO.class.getName());

    public static ObservableList<ProductData> getProductListData() {
        ObservableList<ProductData> productList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM productdb";
        Connection conn = Connector.connectDb();
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeQuery();

            while (result.next()) {
                ProductData productD = new ProductData(
                        result.getInt("product_id"),
                        result.getString("category"),
                        result.getString("brand"),
                        result.getString("product_name"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date")
                );

                productList.add(productD);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error:", e);
        } finally {
            closeResources(ps, result, conn);
        }

        return productList;
    }

    private static void closeResources(PreparedStatement ps, ResultSet rs, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error closing resources:", e);
        }
    }
}
