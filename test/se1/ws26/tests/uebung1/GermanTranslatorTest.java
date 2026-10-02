package se1.ws26.tests.uebung1;

import org.hbrs.se1.ws26.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GermanTranslatorTest {

    @Test
    public void kleinerEins() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl -3 nicht möglich (1.0)", translator.translateNumber(-3));
    }

    @Test
    public void einsBisNeunzehn() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("acht", translator.translateNumber(8));
        assertEquals("siebzehn", translator.translateNumber(17));
    }

    @Test
    public void glatteZehner() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("dreißig", translator.translateNumber(30));
        assertEquals("achtzig", translator.translateNumber(80));
    }

    @Test
    public void einundzwanzigBisEinhundert() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("sechsundfünfzig", translator.translateNumber(56));
        assertEquals("einundsiebzig", translator.translateNumber(71));
        assertEquals("einhundert", translator.translateNumber(100));
    }

    @Test
    public void groesserHundert() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl 101 nicht möglich (1.0)", translator.translateNumber(101));
        assertEquals("Übersetzung der Zahl 232 nicht möglich (1.0)", translator.translateNumber(232));
        assertEquals("zwei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig", translator.translateNumber(Integer.MAX_VALUE));
    }

    @Test
    public void datum() {
        GermanTranslator translator = new GermanTranslator();
        translator.setDate("01.10.2026");
        assertEquals("01.10.2026", translator.getDate());
    }

}