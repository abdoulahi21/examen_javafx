module com.example.examen_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;
    requires itextpdf;
    requires org.apache.poi.ooxml;
    requires java.desktop;


    opens com.example.examen_javafx to javafx.fxml;
    exports com.example.examen_javafx;
    exports com.example.examen_javafx.model;
    exports com.example.examen_javafx.repository;
}