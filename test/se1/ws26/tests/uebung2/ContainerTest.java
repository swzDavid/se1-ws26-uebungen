package se1.ws26.tests.uebung2;

import org.hbrs.se1.ws26.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung2.Container;
import org.hbrs.se1.ws26.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung2.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    @Test
    public void testContainer() throws ContainerException {
        Container container = Container.getInstance();
        Member m1 = new ConcreteMember(22);
        Member m2 = new ConcreteMember(33);

        // leerer Container
        assertEquals(0, container.size());

        // erstes Member hinzufügen
        container.addMember(m1);
        assertEquals(1, container.size());

        // zweites Memeber hinzufügen
        container.addMember(m2);
        assertEquals(2, container.size());

        // Member ausgeben
        container.dump();
        assertEquals(2, container.size());

        // vorhandenes Member hinzufügen
        try {
            container.addMember(m1);
        } catch (ContainerException e) {
            assertEquals("Das Member-Objekt mit der ID 22 ist bereits vorhanden!", e.getMessage());
        }
        assertEquals(2, container.size());

        //nicht vorhandenes Member löschen
        assertEquals("Member-Objekt mit der ID 67 ist nicht vorhanden!", container.deleteMember(67));
        assertEquals(2, container.size());

        // size ausgeben
        container.size();
        assertEquals(2, container.size());

        // NULL hinzufügen
        try {
            container.addMember(null);
        } catch (ContainerException e) {
            assertEquals("Es darf kein null-Objekt hinzugefügt werden!", e.getMessage());
        }
        assertEquals(2, container.size());

        // erstes Member löschen
        assertEquals("Member-Objekt mit der ID 22 wurde gelöscht.", container.deleteMember(22));
        assertEquals(1, container.size());

        // zweites Member löschen
        assertEquals("Member-Objekt mit der ID 33 wurde gelöscht.", container.deleteMember(33));
        assertEquals(0, container.size());

    }
}