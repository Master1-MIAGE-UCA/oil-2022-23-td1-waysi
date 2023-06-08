package jeu.proba.service;

import jeu.proba.model.Proba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProbaService {

    @Autowired
    private Proba probab;

    public void calculateProbabilities(int[] dice, int remainingRolls) {
        double probaFull = calculateProbaFull(dice, remainingRolls);
        double probaCarre = calculateProbaCarre(dice, remainingRolls);
        double probaYams = calculateProbaYams(dice, remainingRolls);
        double probaSuite = calculateProbaSuite(dice, remainingRolls);

        probab.setProbaFull(probaFull);
        probab.setProbaCarre(probaCarre);
        probab.setProbaYams(probaYams);
        probab.setProbaSuite(probaSuite);
    }

    public String decisionForMaxScore(int[] dice, int remainingRolls) {
        calculateProbabilities(dice, remainingRolls);
        if (probab.getProbaYams() >= 0.5) {
            return "Go for Yams!";
        } else if (probab.getProbaFull() >= 0.5) {
            return "Go for Full!";
        } else if (probab.getProbaCarre() >= 0.5) {
            return "Go for Carre!";
        } else if (probab.getProbaSuite() >= 0.5) {
            return "Go for Suite!";
        } else {
            return "Roll again!";
        }
    }


    private boolean isFull(int[] dice) {
        int[] counts = countDice(dice);
        boolean hasThreeOfAKind = false;
        boolean hasTwoOfAKind = false;
        for (int count : counts) {
            if (count >= 3) {
                hasThreeOfAKind = true;
            }
            if (count == 2) {
                hasTwoOfAKind = true;
            }
        }
        return hasThreeOfAKind && hasTwoOfAKind;
    }

    private boolean isCarre(int[] dice) {
        int[] counts = countDice(dice);
        for (int count : counts) {
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isYams(int[] dice) {
        int[] counts = countDice(dice);
        for (int count : counts) {
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean isPetiteSuite(int[] dice) {
        int[] counts = countDice(dice);
        return (counts[0] >= 1 && counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1)
                || (counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1)
                || (counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1);
    }

    private boolean isGrandeSuite(int[] dice) {
        int[] counts = countDice(dice);
        return (counts[0] >= 1 && counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1)
                || (counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1);
    }


    private double calculateProbaFull(int[] dice, int remainingRolls) {
        double proba = isFull(dice) ? 1 : 0;
        return proba + remainingRolls * 0.1;
    }

    private double calculateProbaCarre(int[] dice, int remainingRolls) {
        double proba = isCarre(dice) ? 1 : 0;
        return proba + remainingRolls * 0.1;
    }



    private double calculateProbaYams(int[] dice, int remainingRolls) {
        double proba = isYams(dice) ? 1 : 0;
        return proba + remainingRolls * 0.1;
    }

    private double calculateProbaSuite(int[] dice, int remainingRolls) {
        double proba = (isPetiteSuite(dice) || isGrandeSuite(dice)) ? 1 : 0;
        return proba + remainingRolls * 0.1;
    }

    private int[] countDice(int[] dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }

    public Proba getProba() {
        return probab;
    }
}
