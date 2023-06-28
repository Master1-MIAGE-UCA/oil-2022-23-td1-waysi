package jeu.proba.Controller;

import jeu.proba.Model.DiceRoll;
import jeu.proba.Model.Proba;
import jeu.proba.Service.ProbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proba")
public class ProbaController {

    @Autowired
    private ProbaService probaService;

    @GetMapping
    public Proba getProbabilities() {
        // Retourne l'objet Proba actuel qui contient toutes les probabilités
        return probaService.getProba();
    }



    @PostMapping("/update")
    public void updateProbabilities(@RequestBody DiceRoll diceRoll) {
        probaService.calculateProbabilities(diceRoll.getDice(), diceRoll.getRemainingRolls());
    }

}