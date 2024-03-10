package com.example.examen_javafx;
import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.Categorie;
import com.example.examen_javafx.repository.CategorieRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategorieController implements Initializable {
    BD bd;
    Connection con=null;
    @FXML
    private TextField champLibelle;

    @FXML
    private TableColumn<Categorie,Integer> cid;

    @FXML
    private TableColumn<Categorie,String> clibelle;
    @FXML
    private TableView<Categorie> table;

    @FXML
    void btnAdd(ActionEvent event) {
       String sql="insert into categorie(libelle) values(?)";
       try {
              PreparedStatement statement=con.prepareStatement(sql);
              statement.setString(1,champLibelle.getText());
              statement.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       affiche();
       btnAnnuler(event);
    }

    @FXML
    void btnAnnuler(ActionEvent event) {
       champLibelle.setText("");
    }

    @FXML
    void btnDelete(ActionEvent event) {
         String sql="delete from categorie where id=?";
         try {
              PreparedStatement statement=con.prepareStatement(sql);
              statement.setInt(1,1);
              statement.executeUpdate();
         } catch (SQLException e) {
              throw new RuntimeException(e);
         }
         affiche();
    }
    @FXML
    void charge(MouseEvent event) {
        Categorie categorie=(Categorie) table.getSelectionModel().getSelectedItem();
        if (event.getClickCount()==2){
            champLibelle.setText(categorie.getLibelle());
        }
    }
    public void affiche(){
        CategorieRepository categorieRepository=new CategorieRepository();
        ObservableList<Categorie> list=categorieRepository.getAllCategorie();
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        clibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con=bd.getConnection();
        affiche();

    }
}
