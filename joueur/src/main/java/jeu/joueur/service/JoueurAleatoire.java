package jeu.joueur.service;

import com.example.shared.Figures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class JoueurAleatoire extends JoueurAbstrait{
    @Override
    public List<Integer> relancerDes(HashMap<Integer, Integer> listeDes) {
        List<Integer> keys = new ArrayList<>(listeDes.keySet());

        //Choisir un nombre alétoire de dès à relancer
        Random random = new Random();
        int nbRelance = random.nextInt(5) + 1;
        List<Integer> desSelectionnes = new ArrayList<>();
        for(int i = 0; i < nbRelance; i++){
            int randomIndex = random.nextInt(keys.size());
            int keyChoisie = keys.get(randomIndex);
            desSelectionnes.add(keyChoisie);
            keys.remove(randomIndex);
        }
        return desSelectionnes;
    }

    @Override
    public Figures choisirCombinaison(HashMap<Figures, Boolean> combinaisons) {
        Random random = new Random();
        int choixCombinaison = random.nextInt(combinaisons.size());
        List<Figures> cles = new ArrayList<>(combinaisons.keySet());
        System.out.printf("Vous avez choisi la combinaison %s\n", cles.get(choixCombinaison));
        return cles.get(choixCombinaison);
    }
}
