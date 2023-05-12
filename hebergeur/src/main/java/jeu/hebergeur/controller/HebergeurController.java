package jeu.hebergeur.controller;
import jeu.hebergeur.model.Hebergeur;
import jeu.hebergeur.service.HebergeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hebergeur")
public class HebergeurController {
    @Autowired
    private HebergeurService hebergeurService;
    @Autowired
    private Hebergeur hebergeur;
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/details")
    public @ResponseBody Hebergeur afficherDetailsHebergeur(){
        return this.hebergeur;
    }
    @PostMapping("/joueurs")
    public void ajouterJoueur(@RequestBody String urlJoueur){
        hebergeur.getJoueurs().add(urlJoueur);
        String nomJoueur = restTemplate.getForObject(urlJoueur + "/nom", String.class);
        System.out.println("Le joueur " + nomJoueur + " a été ajouté à l'hébergeur");
        if(hebergeur.getJoueurs().size() == hebergeur.getNbJoueurMax()){
            hebergeur.setIsFull(true);
        }
    }
}
