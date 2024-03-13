package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Pane dynamiquePage;


    BD bd;
    Connection con=null;

    @FXML
    void pageCategorie(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("categorie.fxml"));
        dynamiquePage.getChildren().removeAll();
        dynamiquePage.getChildren().setAll(fxml);

    }

    @FXML
    void pageProduit(ActionEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(getClass().getResource("produit.fxml"));
        dynamiquePage.getChildren().removeAll();
        dynamiquePage.getChildren().setAll(fxml);
    }

    @FXML
    void pagedocument(ActionEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(getClass().getResource("document.fxml"));
        dynamiquePage.getChildren().removeAll();
        dynamiquePage.getChildren().setAll(fxml);

    }
    @FXML
    void pageDashboard(ActionEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(getClass().getResource("statistique.fxml"));
        dynamiquePage.getChildren().removeAll();
        dynamiquePage.getChildren().setAll(fxml);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con= bd.getConnection();

    }
}