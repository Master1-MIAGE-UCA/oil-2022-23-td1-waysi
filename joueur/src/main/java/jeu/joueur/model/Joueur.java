package jeu.joueur.model;

import org.springframework.stereotype.Component;

@Component
public class Joueur {
    private String nom;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
