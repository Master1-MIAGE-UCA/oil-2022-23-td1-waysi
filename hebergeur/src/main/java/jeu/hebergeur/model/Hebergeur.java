package jeu.hebergeur.model;
import jakarta.annotation.PostConstruct;
import main.java.jeu.hebergeur.model.GrilleDeScore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Hebergeur {
    private int nbPartie;
    private int nbJoueurMax;
    private ArrayList<String> joueurs = new ArrayList<>();
    private HashMap<Integer, GrilleDeScore> grilleDeScore = new HashMap<Integer, GrilleDeScore>();

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

    public HashMap<Integer, GrilleDeScore> getGrilleDeScore() {
        return grilleDeScore;
    }


    public void setGrilleDeScore(HashMap<Integer, GrilleDeScore> grilleDeScore) {
        this.grilleDeScore = grilleDeScore;
    }

    public void addGrilleDeScore(int idJoueur, GrilleDeScore grilleDeScoreJoueur) {
        this.grilleDeScore.put(idJoueur, grilleDeScoreJoueur);
        setGrilleDeScore(this.grilleDeScore);
    }
}
