package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {
    @FXML
    private PieChart pieChart;
    BD bd;
    Connection con=null;
    public void affiche() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            String sql = "select count(*) as nb, categorie.libelle from produit, categorie where produit.idcategorie=categorie.id group by categorie.libelle";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                pieChartData.add(new PieChart.Data(resultSet.getString("libelle"), resultSet.getInt("nb")));
            }
            pieChart.setData(pieChartData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd = new BD();
        con = bd.getConnection();
        affiche();

    }
}
