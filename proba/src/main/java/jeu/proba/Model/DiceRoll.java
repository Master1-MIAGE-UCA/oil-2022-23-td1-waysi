package jeu.proba.Model;

public class DiceRoll {

    private int[] dice;
    private int remainingRolls;

    public int[] getDice() {
        return dice;
    }

    public void setDice(int[] dice) {
        this.dice = dice;
    }

    public int getRemainingRolls() {
        return remainingRolls;
    }

    public void setRemainingRolls(int remainingRolls) {
        this.remainingRolls = remainingRolls;
    }
}