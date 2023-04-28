package jeu.hebergeur.service;
import jeu.hebergeur.model.Hebergeur;
import org.springframework.stereotype.Service;

@Service
public class HebergeurService {
    public Hebergeur creerHebergeur() {
        Hebergeur hebergeur = new Hebergeur();
        hebergeur.setIdHebergeur((long) (Math.random() * 1000));
        return hebergeur;
    }
//    public Joueur ajouterJoueur(Long idHebergeur, Joueur joueur) {
//        //A ajouter quand on aura un repository
//        /*Hebergeur hebergeur = hebergeurRepository.findById(idHebergeur);
//        if(hebergeur.getNbJoueur() < hebergeur.getNbJoueurMax()) {
//            hebergeur.getJoueurs().add(joueur);
//            hebergeur.setNbJoueur(hebergeur.getNbJoueur() + 1);
//            return joueur;
//        } */
//        return null;
//    }

}
