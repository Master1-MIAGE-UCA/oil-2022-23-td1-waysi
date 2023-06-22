package jeu.hebergeur.service;
import jeu.hebergeur.model.Hebergeur;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import com.example.shared.Figures;

@Service
public class HebergeurService {
    public Hebergeur creerHebergeur() {
        Hebergeur hebergeur = new Hebergeur();
//        hebergeur.setIdHebergeur((long) (Math.random() * 1000));
        return hebergeur;
    }
//    public Joueur ajouterJoueur(Long idHebergeur, Joueur joueur) {
//        //A ajouter quand on aura un repository
//        /*Hebergeur hebergeur = hebergeurRepository.findById(idHebergeur);
//        if(hebergeur.getNbJoueur() < hebergeur.getNbJoueurMax()) {
//            hebergeur.getJoueurs().add(joueur);
//            hebergeur.setNbJoueur(hebergeur.getNbJoueur() + 1);
//            return joueur;
//        } */
//        return null;
//    }

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

    public Figures choisirCombinaison(String urlJoueur, HashMap<Figures, Boolean> combinaisons) {
        HashMap<Figures, Boolean> combinaisonLibre = new HashMap<>();
        for (Figures combinaison : combinaisons.keySet()) {
            if (!combinaisons.get(combinaison)) {
                combinaisonLibre.put(combinaison, false);
            }
        }
        System.out.println(combinaisonLibre);
        Figures combinaisonChoisie = restTemplate.postForObject(urlJoueur + "/choisirCombinaison", combinaisonLibre, Figures.class);
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
        int scoreActuel = hebergeur.getScoreJoueur(urlJoueur);
        scoreActuel += score;
        hebergeur.setScoreJoueur(urlJoueur, scoreActuel);
        return scoreActuel;
    }

    public void jouerTour() {
        HashMap<String, HashMap<Integer, Integer>> mainJoueurs = new HashMap<>();
        for (String urlJoueur : hebergeur.getJoueurs()) {
            if(hebergeur.getNbTour() == 1){
                HashMap<Figures, Boolean> combinaisons = remplirCombinaisons();
                hebergeur.setCombinaisonsJoueur(urlJoueur, combinaisons);
                hebergeur.setScoreJoueur(urlJoueur, 0);
            }
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
            Figures combinaisonChoisie = choisirCombinaison(urlJoueur, hebergeur.getCombinaisonsJoueur(urlJoueur));
            hebergeur.getCombinaisonsJoueur(urlJoueur).put(combinaisonChoisie, true);


            int score = calculerScore(urlJoueur, combinaisonChoisie, des);
            System.out.println("Le score du joueur " + urlJoueur + " est " + score);
            /**
             * Envoi du score au joueur
             */
            restTemplate.postForEntity(urlJoueur + "/score", score, Void.class);
        }
    }
}
