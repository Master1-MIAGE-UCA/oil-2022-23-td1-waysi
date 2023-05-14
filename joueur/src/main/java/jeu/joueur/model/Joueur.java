package jeu.joueur.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Joueur {
    private String nom;

    private int idJoueur;
    private ArrayList<String> hebergeurs = new ArrayList<>();
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public ArrayList<String> getHebergeurs() {
        return hebergeurs;
    }
    public void setHebergeurs(ArrayList<String> hebergeurs) {
        this.hebergeurs = hebergeurs;
    }
}
