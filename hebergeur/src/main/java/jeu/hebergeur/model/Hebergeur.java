package jeu.hebergeur.model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.Figures;

@Component
public class Hebergeur {
    private int nbPartie;
    private int nbJoueurMax;

    private int nbTour = 0;
    private ArrayList<String> joueurs = new ArrayList<>();
    private HashMap<String, Integer> scoreJoueurs = new HashMap<>();
    private HashMap<String, HashMap<Figures,Boolean>> combinaisonsJoueur = new HashMap<>();

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

    public int getNbTour(){
        return nbTour;
    }
    public void setNbTour(int nbTour){
        this.nbTour = nbTour;
    }

    public int getScoreJoueur(String joueur){
        return scoreJoueurs.get(joueur);
    }
    public void setScoreJoueur(String joueur, int score){
        scoreJoueurs.put(joueur, score);
    }
    public HashMap<Figures, Boolean> getCombinaisonsJoueur(String joueur){
        return combinaisonsJoueur.get(joueur);
    }
    public void setCombinaisonsJoueur(String joueur, HashMap<Figures, Boolean> combinaisons){
        combinaisonsJoueur.put(joueur, combinaisons);
    }

}