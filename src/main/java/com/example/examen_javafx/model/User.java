package com.example.examen_javafx.model;

public class User {
    private int id;
    private String nomcomplet;
    private double telephone;
    private String login;
    private String password;
    private String email;

    public User(int id,String nomcomplet, String tephone, String login, String password, String email) {
        this.id = id;
        this.nomcomplet = nomcomplet;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public double getTelephone() {
        return telephone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public void setTelephone(double telephone) {
        this.telephone = telephone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
