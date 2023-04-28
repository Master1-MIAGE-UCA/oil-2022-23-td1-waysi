package jeu.hebergeur.model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class Hebergeur {
    private long idHebergeur;
    private int nbPartie;
    private int nbJoueur;
    private int nbJoueurMax;
    private ArrayList<String> joueurs;

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
    public ArrayList<String> getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(ArrayList<String> joueurs) {
        this.joueurs = joueurs;
    }

}
