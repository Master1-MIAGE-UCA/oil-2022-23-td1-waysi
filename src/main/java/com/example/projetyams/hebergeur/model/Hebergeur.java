package com.example.projetyams.hebergeur.model;

import com.example.projetyams.joueur.model.Joueur;

import java.util.ArrayList;

public class Hebergeur {
    private long idHebergeur;
    private int nbPartie;
    private int nbJoueur;
    private int nbJoueurMax;
    private ArrayList<Joueur> joueurs;

    //generate getter and setter
    public long getIdHebergeur() {
        return idHebergeur;
    }
    public void setIdHebergeur(long idHebergeur) {
        this.idHebergeur = idHebergeur;
    }
    public int getNbPartie() {
        return nbPartie;
    }
    public void setNbPartie(int nbPartie) {
        this.nbPartie = nbPartie;
    }
    public int getNbJoueur() {
        return nbJoueur;
    }
    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }
    public int getNbJoueurMax() {
        return nbJoueurMax;
    }
    public void setNbJoueurMax(int nbJoueurMax) {
        this.nbJoueurMax = nbJoueurMax;
    }
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

}
