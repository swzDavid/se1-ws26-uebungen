package org.hbrs.se1.ws26.exercises.uebung4;

import java.io.Serializable;

public class UserStory implements Serializable {

    private static int zaehler_id = 0;
    private int id;
    private String titel;
    private double prio;
    private String akzeptanzkriterium;
    private Projekt projekt;
    private int mehrwert;
    private int strafe;
    private int aufwand;
    private int risiko;

    public UserStory(String titel, String akzeptanzkriterium, Projekt projekt, int mehrwert, int strafe, int aufwand, int risiko) {
        id = zaehler_id++;
        this.id = id;
        this.titel = titel;
        this.akzeptanzkriterium = akzeptanzkriterium;
        calculatePrio(mehrwert, strafe, aufwand, risiko);
    }

    private void calculatePrio(int mehrwert, int strafe, int aufwand, int risiko) {
        if (!istGueltig(mehrwert) || !istGueltig(strafe) || !istGueltig(aufwand) || !istGueltig(risiko)) {
            throw new IllegalArgumentException();
        }

        prio = (mehrwert + strafe) / (aufwand + risiko);
    }

    private boolean istGueltig(int wert) {
        if (wert <= 0) {
            return false;
        }

        switch (wert) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 13:
            case 20:
            case 40:
            case 100:
                return true;
            default:
                return false;
        }
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAkzeptanzkriterium() {
        return akzeptanzkriterium;
    }

    public void setAkzeptanzkriterium(String akzeptanzkriterium) {
        this.akzeptanzkriterium = akzeptanzkriterium;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public int getId() {
        return id;
    }

    public double getPrio() {
        return prio;
    }

    public int getMehrwert() {
        return mehrwert;
    }

    public int getStrafe() {
        return strafe;
    }

    public int getAufwand() {
        return aufwand;
    }

    public int getRisiko() {
        return risiko;
    }
}