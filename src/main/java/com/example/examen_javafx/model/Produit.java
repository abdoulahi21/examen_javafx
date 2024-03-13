package com.example.examen_javafx.model;

public class Produit {
    private int id;
    private String libelle;
    private int quantite;
    private int prix;
    private Integer idcategorie;

    public Produit(int id, String libelle, int quantite, int prix, Integer idcategorie) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
        this.prix = prix;
        this.idcategorie = idcategorie;
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

    public Integer getIdcategorie() {
        return idcategorie;
    }
    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    @Override
    public String toString() {
        return "Libelle: "+libelle + ", Quantite: "+quantite+"\n" ;
    }
}
