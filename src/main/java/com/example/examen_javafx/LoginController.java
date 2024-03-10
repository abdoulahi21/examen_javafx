package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.User;
import com.example.examen_javafx.repository.UserImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    BD bd;
    Connection con=null;
    @FXML
    private TextField champLogin;

    @FXML
    private PasswordField champPassword;
    private UserImp userdao= new UserImp();

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void btnConnect(ActionEvent event) throws IOException {
        String login= champLogin.getText();
        String password=champPassword.getText();
        if(champLogin.equals("") || champPassword.equals(""))
        {
            System.out.println("Veuillez remplir tous les champs");
        }else {
           User user= userdao.getconn(login,password);
            if( user !=null )
            {
                //tacheparams=user.getId();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connexion réussie");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté");
                alert.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Accueil");
                stage.setScene(scene);
                stage.show();
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
