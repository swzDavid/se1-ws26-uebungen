package org.hbrs.se1.ws26.exercises.uebung1.view;

import org.hbrs.se1.ws26.exercises.uebung1.control.factory.Factory;
import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;

public class Client {

    /**
     * Methode zur Ausgabe einer Zahl auf der Console
     * (auch bezeichnet als CLI, Terminal)
     * Verwendung des Design Pattern: Factory Method (GoF, Kapitel 6)
     * Problem: Der Client soll einen Translator benutzen,
     *          ohne die konkrete Klasse GermanTranslator zu kennen oder mit new zu erzeugen.
     *          Der Client soll also unabhängig von der Implementierung des Translators funktionieren.
     * Lösung: Die Objekterzeugung wird in die Factory-Klasse ausgelagert.
     *         Dort kann der Client ein Translator-Objekt anfordern.
     *         Dadurch, dass gegen das Interface gearbeitet wird, muss bei
     *         Änderungen nur die Factory-Klasse angepasst werden.
     *
     */
    void display(int aNumber) {
        // In dieser Methode soll die Methode translateNumber
        // mit dem übergegebenen Wert der Variable aNumber
        // aufgerufen werden.
        //
        // Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

        Translator translator = Factory.createTranslator();
        String result = translator.translateNumber(aNumber);

        System.out.println("Das Ergebnis der Berechnung: " +
                result);

    }
}





