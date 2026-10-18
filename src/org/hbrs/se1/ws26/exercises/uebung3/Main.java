package org.hbrs.se1.ws26.exercises.uebung3;

import org.hbrs.se1.ws26.exercises.uebung2.Container;
import org.hbrs.se1.ws26.exercises.uebung2.Member;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyStream;

public class Main {

    /*
    Das Strategy Design Pattern wird angewendet.
    - PersistenceStrategy ist die Strategy
    - PersistenceStrategyStream und PersistenceStrategyMongoDB sind die konkreten Strategien
    - Der Container kennt nur das Interface nicht die konkrete Strategie.
      Die Implementierung wird von außen gesetzt.
     */
    public static void main(String[] args) {
        Container container = Container.getInstance();
        container.setPersistenceStrategy(new PersistenceStrategyStream<Member>());
        Client client = new Client();
        client.run();

        try {
            container.store();
            System.out.println("Member gespeichert.");
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }
}
