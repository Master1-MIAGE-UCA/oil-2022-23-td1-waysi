package com.example.projetyams.joueur.controller;

import com.example.projetyams.joueur.service.JoueurService;
import com.example.projetyams.joueur.model.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueur")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

    private Joueur joueur = new Joueur("joueurTest", (long) (Math.random() * 1000));
    @GetMapping
    public Joueur getJoueur(){
        return joueur;
    }
     @PutMapping
        public ResponseEntity<Joueur> updateJoueur(@RequestBody Joueur joueurUpdated) {
             joueur.setNom(joueurUpdated.getNom());
            return ResponseEntity.ok(joueur);
        }

}
