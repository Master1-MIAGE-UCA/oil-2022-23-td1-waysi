package jeu.hebergeur.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CalculDesPoints {

    public int calculMineur(HashMap<Integer, Integer> des, int combinaison) {
        int score = 0;
        for(int valeur : des.values()) {
            if(valeur == combinaison)
                score += combinaison;
        }
        return score;
    }

    public int calculMemeValeur(HashMap<Integer, Integer> des, int combinaison, boolean full) {
        int score = 0;
        boolean figureValide = false;
        boolean estPaire = false;
        for(int valeur : des.values()) {
            int count = 0;
            for(int valeurAComparer : des.values()) {
                if(valeur == valeurAComparer){
                    count++;
                    if(count >= combinaison){
                        figureValide = true;
                        score = valeur * combinaison;
                    }
                    else if(count == 2 && full){
                        estPaire = true;
                    }
                }
            }
        }
        if(figureValide && estPaire){
            return 25;
        }
        return score;
    }

    public int calculFull(HashMap<Integer, Integer> des) {
    }

    public int calculSuite(HashMap<Integer, Integer> des, int i) {
    }
}
