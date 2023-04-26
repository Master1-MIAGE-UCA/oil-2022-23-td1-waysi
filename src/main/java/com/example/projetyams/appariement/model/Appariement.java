package com.example.projetyams.appariement.model;

import java.util.ArrayList;

public class Appariement {
    private String nom;
    private ArrayList<String> Joueurs;
    private ArrayList<String> Hebergeurs;
    public Appariement() {
        this.nom = "Appariement";
        this.Joueurs = new ArrayList<>();
        this.Hebergeurs = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public ArrayList<String> getJoueurs() {
        return Joueurs;
    }
    public void setJoueurs(ArrayList<String> joueurs) {
        Joueurs = joueurs;
    }
    public ArrayList<String> getHebergeurs() {
        return Hebergeurs;
    }
    public void setHebergeurs(ArrayList<String> hebergeurs) {
        Hebergeurs = hebergeurs;
    }
}
