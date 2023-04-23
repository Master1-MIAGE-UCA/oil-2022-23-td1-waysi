package com.example.projetyams.joueur.controller;

import com.example.projetyams.joueur.service.JoueurService;
import com.example.projetyams.joueur.model.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueur/{id}")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Joueur> getJoueur() {
//        return ResponseEntity.ok(joueurService.getJoueur(id));
//    }
//    @PostMapping
//    public ResponseEntity<Joueur> createJoueur(Joueur joueur) {
//        return ResponseEntity.ok(joueurService.createJoueur(joueur));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Joueur> deleteJoueur(Long id) {
//        return ResponseEntity.ok(joueurService.deleteJoueur(id));
//    }

    /**
     * Exemple de joueur en dur pour créer le service, on utilisera la version en commentaire plus haut pour le projet
     */
    private Joueur joueur = new Joueur("joueurTest", (long) (Math.random() * 1000));
     @GetMapping
        public ResponseEntity<Joueur> getJoueur() {
            return ResponseEntity.ok(joueur);
        }
     @PutMapping
        public ResponseEntity<Joueur> updateJoueur(@RequestBody Joueur joueurUpdated) {
             joueur.setNom(joueurUpdated.getNom());
            return ResponseEntity.ok(joueur);
        }

}
