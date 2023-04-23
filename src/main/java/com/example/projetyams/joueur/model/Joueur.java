package com.example.projetyams.joueur.model;

public class Joueur {
    private String nom;
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Joueur(String nom, Long id) {
        this.nom = nom;
        this.id = id;
    }
}
