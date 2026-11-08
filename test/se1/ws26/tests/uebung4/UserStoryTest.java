package se1.ws26.tests.uebung4;

import org.hbrs.se1.ws26.exercises.uebung4.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung4.Container;
import org.hbrs.se1.ws26.exercises.uebung4.UserStory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    @Test
    void prioWirdKorrektBerechnet() {
        UserStory us = new UserStory("T", "A", "P", 8, 5, 5, 3);
        assertEquals(1.625, us.getPrio(), 0.0001);
    }

    @Test
    void ungueltigerWertWirftException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UserStory("T", "A", "P", 4, 5, 5, 3));
    }

    @Test
    void testStoreDeleteAndLoad() throws ContainerException, PersistenceException {
        Container container = Container.getInstance();

        container.addUserStory(new UserStory("T", "A", "P", 8, 5, 5, 3));
        assertEquals(1, container.size());

        container.store();
        assertEquals(1, container.size());

        container.load();
        assertEquals(1, container.size());
    }

}
