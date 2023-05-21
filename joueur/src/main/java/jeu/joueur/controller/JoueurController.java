package jeu.joueur.controller;
import jeu.joueur.model.Joueur;
import jeu.joueur.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
