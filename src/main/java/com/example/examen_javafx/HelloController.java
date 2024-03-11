package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    BD bd;
    Connection con=null;
    @FXML
    void pageAccueil(ActionEvent event) throws IOException {

    }

    @FXML
    void pageCategorie(ActionEvent event) {

    }

    @FXML
    void pageProduit(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("produit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pagedocument(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con= bd.getConnection();
    }
}