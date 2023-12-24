
module PosSystem {
    requires javafx.swt;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires fontawesomefx;
    requires mysql.connector.j;
    requires java.sql;  
    requires java.logging;
    requires java.desktop;
    
    opens posmanagement to javafx.fxml;
    
    exports posmanagement;
}
