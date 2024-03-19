package com.example.examen_javafx.repository;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategorieRepository {
    BD bd=new BD();
    Connection connection;
    public ObservableList<Categorie> getAllCategorie() {
        connection = bd.getConnection();
        ObservableList<Categorie> list = null;
        try {
            list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM categorie ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt(1));
                categorie.setLibelle(rs.getString(2));
                list.add(categorie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public Categorie get(int id) {
        connection = bd.getConnection();
        Categorie categorie = new Categorie();
        try {
            String sql = "SELECT * FROM categorie WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                categorie.setId(rs.getInt(1));
                categorie.setLibelle(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categorie;
    }
}
