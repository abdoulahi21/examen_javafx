package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    BD bd;
    Connection con=null;
    @FXML
    private TextField champTelephone;
    @FXML
    private TextField champLogin;
    @FXML
    private TextField champNomComplet;
    @FXML
    private PasswordField champPassword;
    @FXML
    private TextField champEmail;
    @FXML
    void btnAnnuler(ActionEvent event)  {
    }

    @FXML
    void btnValider(ActionEvent event) throws IOException {
      String sql="insert into user(nomcomplet,telephone,email,login,password) values(?,?,?,?,?)";
            try{
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1,champNomComplet.getText());
                statement.setString(2,champTelephone.getText());
                statement.setString(3,champEmail.getText());
                statement.setString(4,champLogin.getText());
                statement.setString(5,champPassword.getText());

                statement.execute();
                //Navigation vers la page login
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage=new Stage();
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            champNomComplet.setText("");
            champTelephone.setText("");
            champLogin.setText("");
            champPassword.setText("");
            champEmail.setText("");
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con= bd.getConnection();
    }
}
