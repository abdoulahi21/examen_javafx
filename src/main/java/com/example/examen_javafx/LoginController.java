package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.User;
import com.example.examen_javafx.repository.ProduitRepository;
import com.example.examen_javafx.repository.UserImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    BD bd;
    Connection con=null;
    public static String userparams;

    @FXML
    private TextField champLogin;

    @FXML
    private PasswordField champPassword;
    private UserImp userdao= new UserImp();

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage=(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void btnConnect(ActionEvent event) throws IOException {
        String login= champLogin.getText();
        String password=champPassword.getText();
        if(login.isEmpty() || password.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }else {
           User user= userdao.getconn(login,password);
            if( user !=null )
            {
                userparams="HI! "+ user.getLogin();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connexion réussie");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté");
                alert.showAndWait();
                //Redirection vers la page d'accueil
                Parent fxml= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(fxml);
                Stage stage=(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Accueil");
                stage.setScene(scene);
                stage.show();
                //Alert de produit en rupture de stock
                ProduitRepository produitRepository=new ProduitRepository();
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Produit en rupture de stock");
                alert.setHeaderText(null);
                alert.setContentText(produitRepository.recupererQuantiteInferieur().toString());
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Login ou mot de pass incorrect");
                alert.showAndWait();
            }
        }
        champLogin.setText("");
        champPassword.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con= bd.getConnection();
    }
}
