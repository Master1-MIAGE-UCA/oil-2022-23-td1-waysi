package jeu.joueur.service;

import com.example.shared.Figures;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public abstract class JoueurAbstrait {
    public JoueurAbstrait() {
    }

    public abstract List<Integer> relancerDes(HashMap<Integer, Integer> listeDes);
    public abstract Figures choisirCombinaison(HashMap<Figures, Boolean> combinaisons);
}