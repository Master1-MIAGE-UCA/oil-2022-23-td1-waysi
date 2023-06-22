package jeu.hebergeur.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CalculDesPointsTest {

    @Test
    void calculMineur() {
        CalculDesPoints calculDesPoints = new CalculDesPoints();
        HashMap<Integer, Integer> des = new HashMap<>();
        des.put(1, 1);
        des.put(2, 2);
        des.put(3, 1);
        int combinaison = 1;

        int resultat = calculDesPoints.calculMineur(des, combinaison);

        assertEquals(2, resultat);
    }




    @Test
    void calculMemeValeur() {


        CalculDesPoints calculDesPoints = new CalculDesPoints();
        HashMap<Integer, Integer> des = new HashMap<>();
        des.put(1, 3);
        des.put(2, 3);
        des.put(3, 3);
        int combinaison = 3;
        boolean full = false;

        int resultat = calculDesPoints.calculMemeValeur(des, combinaison, full);

        assertEquals(9, resultat);
        des.put(4, 2);
        des.put(5, 2);
        full = true;
        resultat = calculDesPoints.calculMemeValeur(des, combinaison, full);
        assertEquals(25, resultat);
    }



    @Test
    void calculSuite() {
        CalculDesPoints calculDesPoints = new CalculDesPoints();
        HashMap<Integer, Integer> desPetiteSuite = new HashMap<>();
        desPetiteSuite.put(1, 1);
        desPetiteSuite.put(2, 2);
        desPetiteSuite.put(3, 3);
        desPetiteSuite.put(4, 4);
        desPetiteSuite.put(5, 1);
        int combinaisonPetiteSuite = 4;

        HashMap<Integer, Integer> desGrandeSuite = new HashMap<>();
        desGrandeSuite.put(1, 1);
        desGrandeSuite.put(2, 2);
        desGrandeSuite.put(3, 3);
        desGrandeSuite.put(4, 4);
        desGrandeSuite.put(5, 5);
        int combinaisonGrandeSuite = 5;


        int resultatPetiteSuite = calculDesPoints.calculSuite(desPetiteSuite, combinaisonPetiteSuite);
        int resultatGrandeSuite = calculDesPoints.calculSuite(desGrandeSuite, combinaisonGrandeSuite);


        assertEquals(30, resultatPetiteSuite);
        assertEquals(40, resultatGrandeSuite);
    }




    @Test
    void calculChance() {
        CalculDesPoints calculDesPoints = new CalculDesPoints();
        HashMap<Integer, Integer> des = new HashMap<>();
        des.put(1, 4);
        des.put(2, 2);
        des.put(3, 6);
        des.put(4, 3);
        des.put(5, 5);
        des.put(6, 1);


        int resultat = calculDesPoints.calculChance(des);


        assertEquals(21, resultat);
    }
    }
