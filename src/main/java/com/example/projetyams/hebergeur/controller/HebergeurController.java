package com.example.projetyams.hebergeur.controller;

import com.example.projetyams.hebergeur.model.Hebergeur;
import com.example.projetyams.hebergeur.service.HebergeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HebergeurController {
    @Autowired
    private HebergeurService hebergeurService;
    @PostMapping("/hebergeur")
    public Hebergeur createHebergeur() {
        return hebergeurService.creerHebergeur();
    }
    @PostMapping("/hebergeur/{id}/joueur")
    public Hebergeur ajouterJoueur(Long idHebergeur) {
        return null;
    }
}
