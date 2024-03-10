package com.example.examen_javafx.repository;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitRepository {
    BD bd=new BD();
    Connection connection;
    public ObservableList<Produit> getAllProduit() {
        connection = bd.getConnection();
        // List<Medecin> liste = null;
        ObservableList<Produit> list = null;
        try {
            list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit ";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt(1));
                produit.setLibelle(rs.getString(2));
                produit.setQuantite(rs.getInt(3));
                produit.setPrix(rs.getInt(4));
                produit.setCategorie_id(rs.getInt(5));
                list.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
