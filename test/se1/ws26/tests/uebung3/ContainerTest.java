package se1.ws26.tests.uebung3;


import org.hbrs.se1.ws26.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung2.Container;
import org.hbrs.se1.ws26.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung2.Member;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container container;

    @BeforeEach
    void setUp() {
        // Hier muss der Container einmalig als Singleton instanziiert werden.
        container = Container.getInstance();
    }

    @Test
    void testMongoDBNotImplementedSolution() {
        // Set a strategy, which has not been implemented
        // container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );

        // Testing store
        // Hinweis: Beim Aufruf der Methoden store() oder load() muss eine Exception vom Typ
        // PersistenceException zurückgegeben werden. Der ExceptionType lautet ImplementationNotAvailable
        // Die Message (abrufbar mit der Methode e.getMessage() ) muss einen eindeutigen Text haben, z.B.:
        // assertEquals( e.getMessage() , "MongoDB is not implemented!"  );

        try {
            container.setPersistenceStrategy(new PersistenceStrategyMongoDB<Member>());
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getExceptionTypeType());
            assertEquals("Die Implementierung ist nicht verfügbar!", e.getMessage());
        }
    }

    @Test
    void testNoStrategeySet() {
        //
        // container.setPersistenceStrategie(null);
        // container.store();
        // } catch (PersistenceException e) {
        // Bitte auch hier die Message und den ExceptionType prüfen.
        // }

        try {
            container.setPersistenceStrategy(null);
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType());
            assertEquals("Es wurde keine Persistenz-Strategie gesetzt!", e.getMessage());
        }
    }

    @Test
    void testWrongLocationOfFile() {
        // try {
        //  PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
        // FileStreams do not like directories, so try this out ;-)
        // strat.setLocation("/Users/saschaalda/tmp");
        // container.setPersistenceStrategie( strat );
        // container.store();

        // } catch (PersistenceException e) {
        // Auch hier diverse Assertions bringen, um die Nachricht und den ExceptionType zu testen.
        // }

        try {
            PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
            strat.setLocation("/Users/davidschwietz/tmp");
            container.setPersistenceStrategy(strat);
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType());
            assertTrue(e.getMessage().startsWith("Fehler beim Speichern"));
        }
    }

    @Test
    void testStoreDeleteAndLoad() throws ContainerException, PersistenceException {
        // Testen Sie folgenden RoundTrip:
        // 1. Lösche alle Member-Objekte (Sicher ist sicher! Dazu die Methode deleteAllMember implementieren!)
        // 2. Setzen der Strategie
        // 3. Hinzufügen eines Member-Objekts
        // 4. Abspeichern
        // 5. Löschen des Member-Objekts
        // 6. Laden
        // Die Zustandsänderungen mittels der size() bitte stets testen!

        container.deleteAllMember();
        assertEquals(0, container.size());

        container.setPersistenceStrategy(new PersistenceStrategyStream<Member>());

        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());

        container.store();
        assertEquals(1, container.size());

        container.deleteMember(1);
        assertEquals(0, container.size());

        container.load();
        assertEquals(1, container.size());
    }

}