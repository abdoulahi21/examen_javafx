package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Pane dynamiquePage;
    @FXML
    private Label message1;
    @FXML
    private Label message;
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
    @FXML
    void btnQuitter(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter");
        alert.setHeaderText("Voulez-vous vraiment quitter l'application?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK"))
        {
            Parent fxml= FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(fxml);
            Stage stage=(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con= bd.getConnection();
        message.setText(LoginController.userparams);
        message1.setText("Bienvenue dans votre espace de travail");

    }
}