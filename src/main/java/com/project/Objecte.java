package com.project;

import java.io.Serializable;

public class Objecte implements Serializable {
    String nom;
    String utilitat;

    Objecte (String nom, String utilitat) {
        this.nom = nom;
        this.utilitat = utilitat;
    }

    @Override
    public String toString () {
        return "Nom: " + this.nom + ", Utilitat: " + this.utilitat;
    }
}
