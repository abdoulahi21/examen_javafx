package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<?, ?> barChart;
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
    public void afficheBarChart() {
        ObservableList<XYChart.Data<String, Number>> barChartData = FXCollections.observableArrayList(
                new XYChart.Data<>("Janvier", 10),
                new XYChart.Data<>("Février", 20),
                new XYChart.Data<>("Mars", 15),
                new XYChart.Data<>("Avril", 25),
                new XYChart.Data<>("Mai", 30),
                new XYChart.Data<>("Juin", 35),
                new XYChart.Data<>("Juillet", 40),
                new XYChart.Data<>("Août", 45),
                new XYChart.Data<>("Septembre", 50),
                new XYChart.Data<>("Octobre", 55),
                new XYChart.Data<>("Novembre", 60),
                new XYChart.Data<>("Décembre", 65)
        );
        XYChart.Series series = new XYChart.Series(barChartData);
        barChart.getData().add(series);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd = new BD();
        con = bd.getConnection();
        affiche();
        afficheBarChart();

    }
}
