package jeu.joueur.controller;
import jeu.joueur.model.Joueur;
import jeu.joueur.service.JoueurService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


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
    public void ajouterHebergeur(@RequestBody String urlHebergeur){
        joueur.getHebergeurs().add(urlHebergeur);
        System.out.println("Vous avez été ajouté à l'hébergeur");
    }

    @PostMapping("/lancerDes")
    public void lancerDes(@RequestBody HashMap<Integer,Integer> listeDes){
        System.out.println("Les dés sont " + listeDes);
    }
    @PostMapping("/scoreRelance")
    public void scoreRelance(@RequestBody HashMap<Integer,Integer> listeDes){
        System.out.println("Les dés sont  " + listeDes+ " après relance");
    }
    @PostMapping("/relancerDes")
    public List<Integer> relancerDes(@RequestBody HashMap<Integer,Integer> listeDes){
        List<Integer> keys = new ArrayList<>(listeDes.keySet());

        //Choisir un nombre alétoire de dès à relancer
        Random random = new Random();
        int nbRelance = random.nextInt(5) + 1;
        List<Integer> desSelectionnes = new ArrayList<>();
        for(int i = 0; i < nbRelance; i++){
            int randomIndex = random.nextInt(keys.size());
            int keyChoisie = keys.get(randomIndex);
            desSelectionnes.add(keyChoisie);
            keys.remove(randomIndex);
        }
        return desSelectionnes;
    }

    @PostMapping("/choisirCombinaison")
    public String choisirCombinaison(@RequestBody HashMap<String, Boolean> combinaisons){
        Random random = new Random();
        int choixCombinaison = random.nextInt(combinaisons.size());
        List<String> cles = new ArrayList<>(combinaisons.keySet());
        System.out.printf("Vous avez choisi la combinaison %s\n", cles.get(choixCombinaison));
        return cles.get(choixCombinaison);
    }

    @PostMapping("/score")
    public void score(@RequestBody int score){
        System.out.println("Votre score est de " + score);
    }
}
