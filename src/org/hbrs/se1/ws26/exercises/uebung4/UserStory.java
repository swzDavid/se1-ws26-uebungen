package org.hbrs.se1.ws26.exercises.uebung4;

import java.io.Serializable;

public class UserStory implements Serializable {

    private static int zaehler_id = 0;
    private int id;
    private String titel;
    private double prio;
    private String akzeptanzkriterium;
    private String projekt;

    public UserStory(String titel, String akzeptanzkriterium, String projekt, int mehrwert, int strafe, int aufwand, int risiko) {
        if (titel == null || titel.isEmpty()) {
            throw new IllegalArgumentException("Der Titel darf nicht leer sein!");
        }
        if (akzeptanzkriterium == null || akzeptanzkriterium.isEmpty()) {
            throw new IllegalArgumentException("Das Akzeptanzkriterium darf nicht leer sein!");
        }
        if (projekt == null || projekt.isEmpty()) {
            throw new IllegalArgumentException("Das Projekt darf nicht null sein!");
        }

        id = zaehler_id++;
        this.id = id;
        this.titel = titel;
        this.akzeptanzkriterium = akzeptanzkriterium;
        this.projekt = projekt;
        calculatePrio(mehrwert, strafe, aufwand, risiko);
    }

    private void calculatePrio(int mehrwert, int strafe, int aufwand, int risiko) {
        if (!istGueltig(mehrwert) || !istGueltig(strafe) || !istGueltig(aufwand) || !istGueltig(risiko)) {
            throw new IllegalArgumentException();
        }

        prio = (double) (mehrwert + strafe) / (aufwand + risiko);
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

    public String getProjekt() {
        return projekt;
    }

    public void setProjekt(String projekt) {
        this.projekt = projekt;
    }

    public int getId() {
        return id;
    }

    public double getPrio() {
        return prio;
    }

    public static void setZaehlerId(int wert) {
        zaehler_id = wert;
    }
}