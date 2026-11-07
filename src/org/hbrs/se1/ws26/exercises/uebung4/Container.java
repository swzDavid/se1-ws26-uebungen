package org.hbrs.se1.ws26.exercises.uebung4;

import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private static Container instance;
    private List<UserStory> UserStories = new ArrayList<>();
    private PersistenceStrategy<UserStory> persistenceStrategy;

    private Container() {
    }

    /*
    Singleton Pattern, privater Konstruktor, statisches Attribut instance, statische getInstance()
     */
    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void addUserStory(UserStory userStory) throws ContainerException {
        if (userStory == null) {
            throw new ContainerException("Es darf kein null-Objekt hinzugefügt werden!");
        }

        if (checkUserStory(userStory.getId())) {
            throw new ContainerException(
                    "Die User Story mit der ID " + userStory.getId() + " ist bereits vorhanden!");
        }
        UserStories.add(userStory);
    }

    public boolean checkUserStory(int id) {
        for (UserStory us : UserStories) {
            if (us.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /*
    Der Rückgabewert könnte vom Aufrufer ignoriert werden, da der Compiler zur Laufzeit keinen Fehler wirft.
    Dann bleibt der Fehler unbemerkt. Der Aufrufer muss den String zunächst manuell auswerten und erzwingt
    keine Fehlerbehnadlung.
    */
    public String deleteUserStory(int id) {
        for (UserStory us : UserStories) {
            if (us.getId() == id) {
                UserStories.remove(us);
                return "Die User Story mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Die User Story mit der ID " + id + " ist nicht vorhanden!";
    }

    public int size() {
        return UserStories.size();
    }

    public List<UserStory> getCurrentList() {
        return UserStories;
    }

    public void deleteAllUserStories() {
        UserStories.clear();
    }

    public void setPersistenceStrategy(PersistenceStrategy<UserStory> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Es wurde keine Persistenz-Strategie gesetzt!");
        }

        try {
            persistenceStrategy.save(UserStories);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Die Implementierung ist nicht verfügbar!");
        }
    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Es wurde keine Persistenz-Strategie gesetzt!");
        }

        try {
            UserStories = persistenceStrategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Die Implementierung ist nicht verfügbar!");
        }
    }
}
