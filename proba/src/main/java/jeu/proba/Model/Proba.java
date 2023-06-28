package jeu.proba.Model;

import org.springframework.stereotype.Component;

@Component
public class Proba {

    private double probaFull;
    private double probaCarre;
    private double probaYams;
    private double probaSuite;

    public double getProbaFull() {
        return probaFull;
    }

    public void setProbaFull(double probaFull) {
        this.probaFull = probaFull;
    }

    public double getProbaCarre() {
        return probaCarre;
    }

    public void setProbaCarre(double probaCarre) {
        this.probaCarre = probaCarre;
    }

    public double getProbaYams() {
        return probaYams;
    }
    // yes on va y arriver
    public void setProbaYams(double probaYams) {
        this.probaYams = probaYams;
    }

    public double getProbaSuite() {
        return probaSuite;
    }

    public void setProbaSuite(double probaSuite) {
        this.probaSuite = probaSuite;
    }
    public void calculerProbabilites(int[] des) {

        this.probaFull = (des[0] == des[1] && des[2] == des[3] && des[3] == des[4]) ? 1.0 : 0.0;
        this.probaCarre = (des[0] == des[1] && des[1] == des[2] && des[2] == des[3]) ? 1.0 : 0.0;
        this.probaYams = (des[0] == des[1] && des[1] == des[2] && des[2] == des[3] && des[3] == des[4]) ? 1.0 : 0.0;
        this.probaSuite = (des[1] == des[0] + 1 && des[2] == des[1] + 1 && des[3] == des[2] + 1 && des[4] == des[3] + 1) ? 1.0 : 0.0;
    }
}


