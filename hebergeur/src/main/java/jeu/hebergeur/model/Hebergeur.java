package jeu.hebergeur.model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class Hebergeur {
    private int nbPartie;
    private int nbJoueurMax;
    private ArrayList<String> joueurs;

    private boolean isFull = false;

    public int getNbPartie() {
        return nbPartie;
    }
    public void setNbPartie(int nbPartie) {
        this.nbPartie = nbPartie;
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

    public boolean getIsFull(){
        return isFull;
    }
    public void setIsFull(boolean isFull){
        this.isFull = isFull;
    }

}
