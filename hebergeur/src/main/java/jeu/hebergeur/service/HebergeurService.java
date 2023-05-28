package jeu.hebergeur.service;

import jeu.hebergeur.model.Hebergeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HebergeurService {
    @Autowired
    private Hebergeur hebergeur;

    @Autowired
    CalculDesPoints calculDesPoints;

    RestTemplate restTemplate = new RestTemplate();

    public HashMap<Integer, Integer> lancerDes() {
        HashMap<Integer, Integer> des = new HashMap<>();
        for (int i = 0; i < 6; i++)
            des.put(i, (int) (Math.floor(Math.random() * 6) + 1));
        return des;
    }

    public HashMap<Figures, Boolean> remplirCombinaisons() {
        HashMap<Figures, Boolean> combinaisons = new HashMap<>();
        combinaisons.put(Figures.AS, false);
        combinaisons.put(Figures.DEUX, false);
        combinaisons.put(Figures.TROIS, false);
        combinaisons.put(Figures.QUATRE, false);
        combinaisons.put(Figures.CINQ, false);
        combinaisons.put(Figures.SIX, false);
        combinaisons.put(Figures.BRELAN, false);
        combinaisons.put(Figures.CARRE, false);
        combinaisons.put(Figures.FULL, false);
        combinaisons.put(Figures.PETITE_SUITE, false);
        combinaisons.put(Figures.GRANDE_SUITE, false);
        combinaisons.put(Figures.YAHTZEE, false);
        combinaisons.put(Figures.CHANCE, false);
        return combinaisons;
    }

    public void envoyerScore(String urlJoueur, HashMap<Integer, Integer> des, boolean premierlance) {
        if (premierlance) {
            restTemplate.postForEntity(urlJoueur + "/lancerDes", des, Void.class);
        } else {
            restTemplate.postForEntity(urlJoueur + "/scoreRelance", des, Void.class);
        }
    }

    public HashMap<Integer, Integer> relancerDes(String urlJoueur, HashMap<Integer, Integer> des) {
        List<Integer> desSelectionnes = restTemplate.postForObject(urlJoueur + "/relancerDes", des, List.class);
        for (Integer desSelectionne : desSelectionnes) {
            //attribue une nouvelle valeur au dé selectionné
            des.put(desSelectionne, (int) (Math.floor(Math.random() * 6) + 1));
        }
        System.out.println("Les dés relancés sont " + desSelectionnes);
        System.out.println("Liste des dés après relance " + des);
        return des;
    }

    public String choisirCombinaison(String urlJoueur, HashMap<Figures, Boolean> combinaisons) {
        String combinaisonChoisie = restTemplate.postForObject(urlJoueur + "/choisirCombinaison", combinaisons, String.class);
        System.out.println("La combinaison choisie est " + combinaisonChoisie);
        return combinaisonChoisie;
    }

    public int calculerScore(String urlJoueur, Figures combinaisonChoisie, HashMap<Integer, Integer> des) {
        int score = 0;
         switch (combinaisonChoisie) {
             case AS -> score = calculDesPoints.calculMineur(des, 1);
             case DEUX -> score = calculDesPoints.calculMineur(des, 2);
             case TROIS -> score = calculDesPoints.calculMineur(des, 3);
             case QUATRE -> score = calculDesPoints.calculMineur(des, 4);
             case CINQ ->score =  calculDesPoints.calculMineur(des, 5);
             case SIX -> score = calculDesPoints.calculMineur(des, 6);
             case BRELAN -> score = calculDesPoints.calculMemeValeur(des, 3, false);
             case CARRE ->score =  calculDesPoints.calculMemeValeur(des, 4, false);
             case FULL -> score = calculDesPoints.calculMemeValeur(des, 3, true);
             case PETITE_SUITE -> score = calculDesPoints.calculSuite(des, 4);
             case GRANDE_SUITE -> score = calculDesPoints.calculSuite(des, 5);
             case YAHTZEE  -> score = calculDesPoints.calculMemeValeur(des, 5, false);
             case CHANCE -> score = calculDesPoints.calculChance(des);
        };
         return score;
    }

    public void jouerTour() {
        HashMap<String, HashMap<Integer, Integer>> mainJoueurs = new HashMap<>();
        for (String urlJoueur : hebergeur.getJoueurs()) {
            /**
             * Lancer les dés pour chaque joueur
             */
            System.out.println("Lancement des dés pour le joueur " + urlJoueur);
            HashMap<Integer, Integer> des = lancerDes();
            HashMap<Figures, Boolean> combinaisons = remplirCombinaisons();

            System.out.println("Les dés sont : " + des);
            mainJoueurs.put(urlJoueur, des);
            /**
             * Envoyer les résultats des dés à chaque joueur
             */
            System.out.println("Envoi des dés au joueur " + urlJoueur);
            envoyerScore(urlJoueur, des, true);
            /**
             * Demande de relance des dés
             */
            des = relancerDes(urlJoueur, des);
            envoyerScore(urlJoueur, des, false);
            /**
             * Choix de la combinaison
             */
            System.out.println("Choix de la combinaison pour le joueur " + urlJoueur);
            Figures combinaisonChoisie = Figures.valueOf(choisirCombinaison(urlJoueur, combinaisons));
            combinaisons.put(combinaisonChoisie, true);
            int score = calculerScore(urlJoueur, combinaisonChoisie, des);
            System.out.println("Le score du joueur " + urlJoueur + " est " + score);
            /**
             * Envoi du score au joueur
             */
            restTemplate.postForEntity(urlJoueur + "/score", score, Void.class);
        }
    }
}
