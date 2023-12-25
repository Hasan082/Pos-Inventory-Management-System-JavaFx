
package posmanagement.Model;

//product_id category brand product_name price status image date	

import java.sql.*;



public class ProductData {
    
    private Integer product_id;
    private String category;
    private String brand;
    private String product_name;
    private double price;
    private String status;
    private String image;
    private Date date;

    public ProductData(Integer product_id, String category, String brand, String product_name, double price, String status, String image, Date date) {
        this.product_id = product_id;
        this.category = category;
        this.brand = brand;
        this.product_name = product_name;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
    
    
    
}
