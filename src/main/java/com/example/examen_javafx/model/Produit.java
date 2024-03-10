package com.example.examen_javafx.model;

public class Produit {
    private int id;
    private String libelle;
    private int quantite;
    private int prix;
    private int categorie_id;

    public Produit(int id, String libelle, int quantite, int prix, int categorie_id) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie_id = categorie_id;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
}
