package com.example.examen_javafx;

import com.example.examen_javafx.model.BD;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Desktop;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static org.apache.poi.sl.usermodel.PresetColor.Desktop;


public class DocumentController implements Initializable {
    BD bd;
    Connection connection;
    @FXML
    void buttonExcel(ActionEvent event) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Produits");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM produit");
            int row = 0;
            while (resultSet.next()) {
                org.apache.poi.ss.usermodel.Row excelRow = sheet.createRow(row++);
                excelRow.createCell(0).setCellValue(resultSet.getString(1));
                excelRow.createCell(1).setCellValue(resultSet.getString(2));
                excelRow.createCell(2).setCellValue(resultSet.getString(3));
                excelRow.createCell(3).setCellValue(resultSet.getString(4));
                excelRow.createCell(4).setCellValue(resultSet.getString(5));
            }
            FileOutputStream file = new FileOutputStream("products.xlsx");
            workbook.write(file);
            file.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


     @FXML
    void buttonPdf(ActionEvent event) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("products.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(5); // 5 colonnes pour vos 5 colonnes de base de données
            addTableHeader(table);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM produit");
            while (resultSet.next()) {
                table.addCell(resultSet.getString(1));
                table.addCell(resultSet.getString(2));
                table.addCell(resultSet.getString(3));
                table.addCell(resultSet.getString(4));
                table.addCell(resultSet.getString(5));
            }

            document.add(table);
            document.close();

        } catch (SQLException | IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Libelle", "Quantite", "Prix", "Idcategorie")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd = new BD();
        connection = bd.getConnection();
    }

}
