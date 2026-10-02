package org.hbrs.se1.ws26.exercises.uebung1.control.factory;

import org.hbrs.se1.ws26.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Factory {

    public static Translator createTranslator() {
        GermanTranslator translator = new GermanTranslator();
        translator.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        return translator;
    }

}
