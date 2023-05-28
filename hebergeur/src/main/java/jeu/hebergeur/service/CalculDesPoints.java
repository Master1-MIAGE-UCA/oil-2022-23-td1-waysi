package jeu.hebergeur.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CalculDesPoints {

    public int calculMineur(HashMap<Integer, Integer> des, int combinaison) {
        int score = 0;
        for (int valeur : des.values()) {
            if (valeur == combinaison)
                score += combinaison;
        }
        return score;
    }

    public int calculMemeValeur(HashMap<Integer, Integer> des, int combinaison, boolean full) {
        int score = 0;
        boolean figureValide = false;
        for (int valeur : des.values()) {
            int count = 0;
            for (int valeurAComparer : des.values()) {
                if (valeur == valeurAComparer) {
                    count++;
                    if (count >= combinaison) {
                        figureValide = true;
                        score = valeur * combinaison;
                    }
                }
            }
        }
        if (figureValide) {
            if (combinaison == 3 && full) {
                if (des.values().stream().distinct().count() == 2) { //une fois la hashmap transformée en stream, on compte le nombre de valeurs différentes
                    return 25;
                }
            } else if (combinaison == 5) {
                return 50;
            }
        }
        return score;
    }


    public int calculSuite(HashMap<Integer, Integer> des, int combinaison) {
        if (combinaison == 4){
            return estPetiteSuite(des) ? 30 : 0;
        }
        else if (combinaison == 5){
            return estGrandeSuite(des) ? 40 : 0;
        }
        return 0;
    }

    public int calculChance(HashMap<Integer,Integer> des){
        int score = 0;
        for (int valeur : des.values()) {
            score += valeur;
        }
        return score;
    }

    /**
     * Vérifie si la hashmap contient les valeurs 1,2,3,4 ou 2,3,4,5 ou 3,4,5,6 (heureusement que c'est un dé à 6 faces)
     * @param des
     * @return
     */
    private boolean estPetiteSuite(HashMap<Integer,Integer> des){
        return des.containsValue(1) && des.containsValue(2) && des.containsValue(3) && des.containsValue(4) ||
                des.containsValue(2) && des.containsValue(3) && des.containsValue(4) && des.containsValue(5) ||
                des.containsValue(3) && des.containsValue(4) && des.containsValue(5) && des.containsValue(6);
    }

    private boolean estGrandeSuite(HashMap<Integer,Integer> des){
        return des.containsValue(1) && des.containsValue(2) && des.containsValue(3) && des.containsValue(4) && des.containsValue(5) ||
                des.containsValue(2) && des.containsValue(3) && des.containsValue(4) && des.containsValue(5) && des.containsValue(6);
    }
}
