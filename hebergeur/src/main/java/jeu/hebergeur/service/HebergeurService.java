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

    public HashMap<String, Boolean> remplirCombinaisons() {
        HashMap<String, Boolean> combinaisons = new HashMap<>();
        combinaisons.put("as", false);
        combinaisons.put("deux", false);
        combinaisons.put("trois", false);
        combinaisons.put("quatre", false);
        combinaisons.put("cinq", false);
        combinaisons.put("six", false);
        combinaisons.put("brelan", false);
        combinaisons.put("carre", false);
        combinaisons.put("full", false);
        combinaisons.put("petiteSuite", false);
        combinaisons.put("grandeSuite", false);
        combinaisons.put("yahtzee", false);
        combinaisons.put("chance", false);
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

    public String choisirCombinaison(String urlJoueur, HashMap<String, Boolean> combinaisons) {
        String combinaisonChoisie = restTemplate.postForObject(urlJoueur + "/choisirCombinaison", combinaisons, String.class);
        System.out.println("La combinaison choisie est " + combinaisonChoisie);
        return combinaisonChoisie;
    }

    public int calculerScore(String urlJoueur, String combinaisonChoisie, HashMap<Integer, Integer> des) {
        return switch (combinaisonChoisie) {
            case "as" -> calculDesPoints.calculMineur(des, 1);
            case "deux" -> calculDesPoints.calculMineur(des, 2);
            case "trois" -> calculDesPoints.calculMineur(des, 3);
            case "quatre" -> calculDesPoints.calculMineur(des, 4);
            case "cinq" -> calculDesPoints.calculMineur(des, 5);
            case "six" -> calculDesPoints.calculMineur(des, 6);
            default -> 0;
        };
    }

    public void jouerTour() {
        HashMap<String, HashMap<Integer, Integer>> mainJoueurs = new HashMap<>();
        for (String urlJoueur : hebergeur.getJoueurs()) {
            /**
             * Lancer les dés pour chaque joueur
             */
            System.out.println("Lancement des dés pour le joueur " + urlJoueur);
            HashMap<Integer, Integer> des = lancerDes();
            HashMap<String, Boolean> combinaisons = remplirCombinaisons();

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
            String combinaisonChoisie = choisirCombinaison(urlJoueur, combinaisons);
            combinaisons.put(combinaisonChoisie, true);
            calculerScore(urlJoueur, combinaisonChoisie, des);
        }
    }
}
