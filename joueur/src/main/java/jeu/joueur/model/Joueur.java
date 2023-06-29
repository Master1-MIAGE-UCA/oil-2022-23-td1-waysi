package jeu.joueur.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Joueur {
    private String nom;
    private ArrayList<String> hebergeurs = new ArrayList<>();
    private DifficulteJoueur difficulteJoueur;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<String> getHebergeurs() {
        return hebergeurs;
    }
    public void setHebergeurs(ArrayList<String> hebergeurs) {
        this.hebergeurs = hebergeurs;
    }
    public void setDifficulteJoueur(DifficulteJoueur difficulteJoueur) {
        this.difficulteJoueur = difficulteJoueur;
    }
    public DifficulteJoueur getDifficulteJoueur() {
        return difficulteJoueur;
    }
}
