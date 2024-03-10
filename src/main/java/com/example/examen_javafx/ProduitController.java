package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.Categorie;
import com.example.examen_javafx.model.Produit;
import com.example.examen_javafx.repository.CategorieRepository;
import com.example.examen_javafx.repository.ProduitRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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

public class ProduitController implements Initializable {
    BD bd;
    Connection con=null;
    @FXML
    private TableColumn<Produit, Integer> cCategorie;

    @FXML
    private TableColumn<Produit, Integer> cId;

    @FXML
    private TableColumn<Produit, String> cLibelle;

    @FXML
    private TableColumn<Produit, Integer> cPrix;

    @FXML
    private TableColumn<Produit, Integer> cQuantite;
    @FXML
    private TableView<Produit> table;
    @FXML
    private TextField champLibelle;

    @FXML
    private TextField champPrix;

    @FXML
    private TextField champQuantite;

    @FXML
    private ComboBox<Categorie> combo;

    @FXML
    void btnAdd(ActionEvent event) {
        String sql="insert into produit(libelle,quantite,prix,idcategorie) values(?,?,?,?)";
        try {
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,champLibelle.getText());
            statement.setString(2,champQuantite.getText());
            statement.setString(3,champPrix.getText());
            statement.setString(4,combo.getValue().toString());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
        btnReset(event);
    }

    @FXML
    void btnReset(ActionEvent event) {
        champLibelle.setText("");
        champPrix.setText("");
        champQuantite.setText("");
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        int id = this.table.getSelectionModel().getSelectedItem().getId();
        String sql = "UPDATE produit SET libelle=?,quantite=?,prix=?,idcategorie=? WHERE id=?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, cLibelle.getText());
            statement.setInt(2, Integer.parseInt(champQuantite.getText()));
            statement.setInt(3, Integer.parseInt(cPrix.getText()));
            statement.setString(4, String.valueOf(combo.getValue()));
            statement.setInt(4, id);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
        btnReset(event);
    }
    @FXML
    void btnDelete(ActionEvent event) {
        int id=this.table.getSelectionModel().getSelectedItem().getId();
        try {
            String sql=" DELETE FROM categorie WHERE id = ?";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();

        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        affiche();
    }

    @FXML
    void charge(MouseEvent event) {
        Produit produit=new Produit();
        produit=table.getSelectionModel().getSelectedItem();
      if (event.getClickCount()==2)
      {
         champLibelle.setText(produit.getLibelle());
         champQuantite.setText(String.valueOf(produit.getQuantite()));
         champPrix.setText(String.valueOf(produit.getPrix()));

      }
    }
    public void affiche()
    {
        ProduitRepository produitRepository=new ProduitRepository();
        ObservableList<Produit> list = produitRepository.getAllProduit();
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        cQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        cPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        cCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        table.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd=new BD();
        con=bd.getConnection();
        affiche();
        CategorieRepository categorieRepository=new CategorieRepository();
        ObservableList<Categorie> categorie = categorieRepository.getAllCategorie();
        categorie.addAll(categorieRepository.getAllCategorie());
        combo.setItems(categorie);

    }
}
