package com.example.projetyams.hebergeur.service;

import com.example.projetyams.hebergeur.model.Hebergeur;
import com.example.projetyams.joueur.model.Joueur;
import org.springframework.stereotype.Service;

@Service
public class HebergeurService {
    public Hebergeur creerHebergeur() {
        Hebergeur hebergeur = new Hebergeur();
        hebergeur.setIdHebergeur((long) (Math.random() * 1000));
        return hebergeur;
    }
    public Joueur ajouterJoueur(Long idHebergeur, Joueur joueur) {
        //A ajouter quand on aura un repository
        /*Hebergeur hebergeur = hebergeurRepository.findById(idHebergeur);
        if(hebergeur.getNbJoueur() < hebergeur.getNbJoueurMax()) {
            hebergeur.getJoueurs().add(joueur);
            hebergeur.setNbJoueur(hebergeur.getNbJoueur() + 1);
            return joueur;
        } */
        return null;
    }

}
