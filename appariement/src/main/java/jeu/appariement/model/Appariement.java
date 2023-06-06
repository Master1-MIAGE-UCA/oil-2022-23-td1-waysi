package jeu.appariement.model;

import java.util.ArrayList;
import java.util.List;

public class Appariement {
    private String nom;
    private List<String> Joueurs;
    private List<String> Hebergeurs;
    private List<String> Probas; // Nouveau champ pour stocker les probabilités

    public Appariement() {
        this.nom = "Appariement";
        this.Joueurs = new ArrayList<>();
        this.Hebergeurs = new ArrayList<>();
        this.Probas = new ArrayList<>(); // Initialisation de la liste des probabilités
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getJoueurs() {
        return Joueurs;
    }

    public void setJoueurs(List<String> joueurs) {
        Joueurs = joueurs;
    }

    public List<String> getHebergeurs() {
        return Hebergeurs;
    }

    public void setHebergeurs(List<String> hebergeurs) {
        Hebergeurs = hebergeurs;
    }

    public List<String> getProbas() {  // Getter pour les probabilités
        return Probas;
    }

    public void setProbas(List<String> probas) {  // Setter pour les probabilités
        Probas = probas;
    }
}
