package jeu.appariement.controller;
import jeu.appariement.model.Appariement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AppariementController {

    Appariement appariement = new Appariement();
//    @GetMapping("/appariement")
//    public ArrayList<String> getAppariement() {
//        String url = System.getProperty("joueur.url");
//        System.out.println(url);
//        appariement.getJoueurs().add(url);
//        System.out.println(appariement.getJoueurs());
//        return appariement.getJoueurs();
//    }
//    @GetMapping("/appariement/nom")
//    public String getNom() {
//        return "caca";
//    }
    @PostMapping("/appariement/joueurs")
    public void ajouterJoueurs(@RequestBody String url){
        appariement.getJoueurs().add(url);
        System.out.println(appariement.getJoueurs());
    }
    @PostMapping("/appariement/hebergeurs")
    public void ajouterHebergeur(@RequestBody String url){
        appariement.getHebergeurs().add(url);
        System.out.println(appariement.getHebergeurs());
    }
    
}
