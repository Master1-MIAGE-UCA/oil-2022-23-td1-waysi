package jeu.joueur.controller;

import jeu.joueur.model.Joueur;
import org.junit.jupiter.api.Test;

import jeu.joueur.controller.JoueurController;
import jeu.joueur.service.JoueurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(args = "--server.port=8082")
@AutoConfigureMockMvc
public class JoueurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Joueur joueur;

    @SpyBean
    JoueurController joueurControlleur;



    @BeforeEach
    void setup() {

        when(joueur.getNom()).thenReturn("Joueur");
    }

    @Test
    void testGetNom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/joueur/nom"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Joueur"));
    }

    @Test
    void testAjouterHebergeur() throws Exception {
        String urlHebergeur = "http://localhost:8080/appariement/hebergeurs";
        mockMvc.perform(MockMvcRequestBuilders.post("/joueur/hebergeur")
                        .content(urlHebergeur)
                        .requestAttr("org.springframework.test.web.servlet.request.MockMvcRequestBuilders.args", new String[]{"--port=8080"}))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vous avez été ajouté à l'hébergeur"));
    }

    @Test
    void testLancerDes() throws Exception {
        HashMap<Integer, Integer> listeDes = new HashMap<>();
        listeDes.put(1, 1);
        listeDes.put(2, 2);
        listeDes.put(3, 3);

        mockMvc.perform(MockMvcRequestBuilders.post("/joueur/lancerDes")
                        .contentType("application/json")
                        .content(listeDes.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Les dés sont " + listeDes));
    }

    @Test
    void testScoreRelance() throws Exception {
        HashMap<Integer, Integer> listeDes = new HashMap<>();
        listeDes.put(1, 1);
        listeDes.put(2, 2);
        listeDes.put(3, 3);

        mockMvc.perform(MockMvcRequestBuilders.post("/joueur/scoreRelance")
                        .contentType("application/json")
                        .content(listeDes.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Les dés sont " + listeDes + " après relance"));
    }

    @Test
    void testRelancerDes() throws Exception {
        HashMap<Integer, Integer> listeDes = new HashMap<>();
        listeDes.put(1, 1);
        listeDes.put(2, 2);
        listeDes.put(3, 3);

        mockMvc.perform(MockMvcRequestBuilders.post("/joueur/relancerDes")
                        .contentType("application/json")
                        .content(listeDes.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testChoisirCombinaison() throws Exception {
        HashMap<String, Boolean> combinaisons = new HashMap<>();
        combinaisons.put("Combinaison 1", true);
        combinaisons.put("Combinaison 2", false);

        mockMvc.perform(MockMvcRequestBuilders.post("/joueur/choisirCombinaison")
                        .contentType("application/json")
                        .content(combinaisons.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vous avez choisi la combinaison Combinaison 1"));
    }

    @Test
    void testScore() throws Exception {
        int score = 100;

        mockMvc.perform(MockMvcRequestBuilders.post("/joueur/score")
                        .contentType("application/json")
                        .content(String.valueOf(score)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Votre score est de " + score));
    }
}
