package com.example.projetyams.joueur.service;

import com.example.projetyams.joueur.model.Joueur;
import org.springframework.stereotype.Service;

@Service
 public class JoueurService {

    public Joueur createJoueur(Joueur joueur) {
        joueur.setId((long) (Math.random() * 1000));
        return joueur;
    }
    public Joueur getJoueur(Long id) {
        return null;
    }
    public Joueur deleteJoueur(Long id) {
        return null;
    }
}
