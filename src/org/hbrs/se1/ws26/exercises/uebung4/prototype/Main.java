package org.hbrs.se1.ws26.exercises.uebung4.prototype;

import org.hbrs.se1.ws26.exercises.uebung4.UserStoryView;

public class Main {

    public static void main(String[] args) {
        UserStoryView usw = new UserStoryView();
        try {
            usw.startEingabe();
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}
