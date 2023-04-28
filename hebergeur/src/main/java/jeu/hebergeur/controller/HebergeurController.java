package jeu.hebergeur.controller;
import jeu.hebergeur.model.Hebergeur;
import jeu.hebergeur.service.HebergeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hebergeur")
public class HebergeurController {
    @Autowired
    private HebergeurService hebergeurService;
    @Autowired
    private Hebergeur hebergeur;
//    @PostMapping("/Creation")
//    public Hebergeur createHebergeur() {
//        return hebergeurService.creerHebergeur();
//    }
    @PostMapping("/{id}/joueur")
    public Hebergeur ajouterJoueur(Long idHebergeur) {
        return null;
    }
}
