package model;

import lombok.Data;

@Data
public class Salaire {
    private double salaireBrut;
    private double salaireNet;

    public Salaire(double salaireBrut) {
        this.salaireBrut = salaireBrut;
    }

    public void calculerSalaireNet() {
        this.salaireNet = this.salaireBrut * 0.8;
    }
}
