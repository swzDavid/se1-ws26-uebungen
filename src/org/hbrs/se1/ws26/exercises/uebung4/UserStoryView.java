package org.hbrs.se1.ws26.exercises.uebung4;

import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceStrategyStream;

import java.util.Scanner;

public class UserStoryView {

    Container container = Container.getInstance();

    public void startEingabe() throws Exception {
        String strInput = null;

        // Initialisierung des Eingabe-View
        Scanner scanner = new Scanner(System.in);

        // Ausagbe eines Texts zur Begruessung
        System.out.println("UserStory-Tool by David Schwietz");

        while (true) {

            System.out.print("> ");
            strInput = scanner.nextLine();

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");

            // 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
            if (strings[0].equals("help")) {
                System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump, enter, store, load, exit");
            }

            if (strings[0].equals("dump")) {
                startAusgabe();
            }

            if (strings[0].equals("enter")) {
                enterUserStory(scanner);
            }

            if (strings[0].equals("store")) {
                try {
                    container.setPersistenceStrategy(new PersistenceStrategyStream<UserStory>());
                    container.store();
                    System.out.println(" User Stories gespeichert.");
                } catch (PersistenceException e) {
                    System.out.println("Fehler beim Speichern: " + e.getMessage());
                }

            }

            if (strings[0].equals("load")) {
                try {
                    container.load();
                } catch (PersistenceException e) {
                    System.out.println("Fehler beim Aufrufen: " + e.getMessage());
                }
            }

            if (strings[0].equals("exit")) {
                break;
            }

        } // Ende der Schleife
        scanner.close();
    }

    private void enterUserStory(Scanner scanner) {
        System.out.print("Titel: ");
        String titel = scanner.nextLine();

        System.out.print("Projekt: ");
        String projektName = scanner.nextLine();

        System.out.print("Akzeptanzkriterium: ");
        String akzeptanz = scanner.nextLine();

        System.out.println("Mehrwert: ");
        int mehrwert = Integer.parseInt(scanner.nextLine());

        System.out.println("Aufwand: ");
        int aufwand = Integer.parseInt(scanner.nextLine());

        System.out.println("Risiko: ");
        int risiko = Integer.parseInt(scanner.nextLine());

        System.out.println("Strafe: ");
        int strafe = Integer.parseInt(scanner.nextLine());

        try {
            UserStory us = new UserStory(titel, akzeptanz, projektName,
                    mehrwert, strafe, aufwand, risiko);
            container.addUserStory(us);
            System.out.println("User Story angelegt");
        } catch (ContainerException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

    }

    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public void startAusgabe() {
        System.out.printf("%-5s | %-20s | %-15s | %-25s | %s%n",
                "ID", "Titel", "Projekt", "Akzeptanzkriterium", "Priorität");
        System.out.println("-".repeat(90));

        container.getCurrentList().stream()
                .sorted((a, b) -> Double.compare(b.getPrio(), a.getPrio()))
                .map(us -> String.format("%-5d | %-20s | %-15s | %-25s | %.2f",
                        us.getId(), us.getTitel(), us.getProjekt(),
                        us.getAkzeptanzkriterium(), us.getPrio()))
                .forEach(us -> System.out.println(us));
    }

}