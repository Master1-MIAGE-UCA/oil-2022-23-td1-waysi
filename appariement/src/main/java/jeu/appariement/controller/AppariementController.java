package jeu.appariement.controller;
import jeu.appariement.model.Appariement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/appariement")
public class AppariementController {
    private RestTemplate restTemplate = new RestTemplate();

    Appariement appariement = new Appariement();
    @PostMapping("/joueurs")
    public void ajouterJoueurs(@RequestBody String url){
        appariement.getJoueurs().add(url);
        System.out.println(appariement.getJoueurs());
    }
    @PostMapping("hebergeurs")
    public void ajouterHebergeur(@RequestBody String url){
        appariement.getHebergeurs().add(url);
        System.out.println(appariement.getHebergeurs());
    }

    @PostMapping("/{idHebergeur}/ajouterJoueur")
    public void ajouterJoueur(@PathVariable String hebergeurUri, @RequestBody String joueurUrl){
        String urlHebergeur = hebergeurUri + "/joueurs";
        try{
            restTemplate.postForObject(urlHebergeur, joueurUrl, Void.class);
            System.out.println("Le joueur a bien été ajouté à l'hébergeur");
        }
        catch (Exception e){
            System.out.println("Erreur lors de l'ajout du joueur à l'hébergeur : " + e.getMessage());
        }

    }
    
}
