package jeu.hebergeur.controller;
import jeu.hebergeur.model.Hebergeur;
import jeu.hebergeur.service.HebergeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hebergeur")
public class HebergeurController {
    @Autowired
    private HebergeurService hebergeurService;
    @Autowired
    private Hebergeur hebergeur;

    @GetMapping("/details")
    public @ResponseBody Hebergeur afficherDetailsHebergeur(){
        return this.hebergeur;
    }
    @PostMapping("/joueurs")
    public void ajouterJoueur(@RequestBody String urlJoueur){
        hebergeur.getJoueurs().add(urlJoueur);
        if(hebergeur.getJoueurs().size() == hebergeur.getNbJoueurMax()){
            hebergeur.setIsFull(true);
        }
    }
}
