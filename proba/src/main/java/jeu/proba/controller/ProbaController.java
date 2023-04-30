package jeu.proba.controller;
import jeu.proba.model.Proba;
import jeu.proba.service.ProbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proba")
public class ProbaController {
    @Autowired
    private ProbaService probaService;
    @Autowired
    private Proba proba;
    @GetMapping("/nom")
    public String getNom() {
        return proba.getNom();
    }
}
