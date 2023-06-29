package jeu.joueur.controller;

import jakarta.annotation.PostConstruct;
import jeu.joueur.JoueurApp;
import jeu.joueur.model.DifficulteJoueur;
import jeu.joueur.model.Joueur;
import jeu.joueur.service.JoueurAbstrait;
import jeu.joueur.service.JoueurAleatoire;
import jeu.joueur.service.JoueurIntelligent;
import jeu.joueur.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.example.shared.Figures;


@RestController
@RequestMapping("/joueur")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;
    @Autowired
    private Joueur joueur;

    @GetMapping("/nom")
    public String getNom() {
        return joueur.getNom();
    }

    @PostMapping("/hebergeur")
    public void ajouterHebergeur(@RequestBody String urlHebergeur) {
        joueur.getHebergeurs().add(urlHebergeur);
        System.out.println("Vous avez été ajouté à l'hébergeur");
    }

    @PostMapping("/lancerDes")
    public void lancerDes(@RequestBody HashMap<Integer, Integer> listeDes) {
        System.out.println("Les dés sont " + listeDes);
    }

    @PostMapping("/scoreRelance")
    public void scoreRelance(@RequestBody HashMap<Integer, Integer> listeDes) {
        System.out.println("Les dés sont  " + listeDes + " après relance");
    }

    @PostMapping("/relancerDes")
    public List<Integer> relancerDes(@RequestBody HashMap<Integer, Integer> listeDes) {
        return JoueurApp.joueurAbstrait.relancerDes(listeDes);
    }

    @PostMapping("/choisirCombinaison")
    public Figures choisirCombinaison(@RequestBody HashMap<Figures, Boolean> combinaisons) {
        return JoueurApp.joueurAbstrait.choisirCombinaison(combinaisons);
    }

    @PostMapping("/score")
    public void score(@RequestBody int score) {
        System.out.println("Votre score est de " + score);
    }
}
