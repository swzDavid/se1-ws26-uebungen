package org.hbrs.se1.ws26.exercises.uebung4;

public class Projekt {

    private static int zaehler_id = 0;
    private int id;
    private String titel;

    public Projekt(String titel) {
        id = zaehler_id++;
        this.titel = titel;
    }

}
