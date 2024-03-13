package com.example.examen_javafx;

import com.example.examen_javafx.repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocumentController implements Initializable {

    @FXML
    void buttonExcel(ActionEvent event) {


    }

    @FXML
    void buttonPdf(ActionEvent event) {
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\LICENCE3GL\\produits.pdf");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
