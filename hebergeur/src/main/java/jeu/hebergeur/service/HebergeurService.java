package jeu.hebergeur.service;

import jeu.hebergeur.model.Hebergeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class HebergeurService {
    @Autowired
    private Hebergeur hebergeur;

    RestTemplate restTemplate = new RestTemplate();

    public HashMap<Integer, Integer> lancerDes() {
        HashMap<Integer, Integer> des = new HashMap<>();
        for (int i = 0; i < 6; i++)
            des.put(i, (int) (Math.floor(Math.random() * 6) + 1));
        return des;
    }
    public void envoyerScore(String urlJoueur, HashMap<Integer, Integer> des){
        restTemplate.postForEntity(urlJoueur + "/lancerDes", des, Void.class);
    }

    public HashMap<Integer, Integer> relancerDes(String urlJoueur, HashMap<Integer, Integer> des){
        ArrayList desSelectionnes = restTemplate.postForObject(urlJoueur + "/relancerDes", des, ArrayList.class);
        return null;
    }

    public void jouerTour() {
        HashMap<String, HashMap<Integer, Integer>> mainJoueurs = new HashMap<>();
        for (String urlJoueur : hebergeur.getJoueurs()) {
            /**
             * Lancer les dés pour chaque joueur
             */
            System.out.println("Lancement des dés pour le joueur " + urlJoueur);
            HashMap<Integer, Integer> des = lancerDes();
            System.out.println("Les dés sont : " + des);
            mainJoueurs.put(urlJoueur, des);
            /**
             * Envoyer les résultats des dés à chaque joueur
             */
            System.out.println("Envoi des dés au joueur " + urlJoueur);
            envoyerScore(urlJoueur, des);
            /**
             * Demande de relance des dés
             */

        }
    }
}
