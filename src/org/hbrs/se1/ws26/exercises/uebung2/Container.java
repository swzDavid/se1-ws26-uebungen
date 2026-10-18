package org.hbrs.se1.ws26.exercises.uebung2;

import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private static Container instance;
    private List<Member> members = new ArrayList<>();
    private PersistenceStrategy<Member> persistenceStrategy;

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

    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            throw new ContainerException("Es darf kein null-Objekt hinzugefügt werden!");
        }

        if (checkMember(member.getID())) {
            throw new ContainerException(
                    "Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
        members.add(member);
    }

    public boolean checkMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
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
    public String deleteMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
                members.remove(m);
                return "Member-Objekt mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Member-Objekt mit der ID " + id + " ist nicht vorhanden!";
    }

    public int size() {
        return members.size();
    }

    public List<Member> getCurrentList() {
        return members;
    }

    public void deleteAllMember() {
        members.clear();
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Es wurde keine Persistenz-Strategie gesetzt!");
        }

        try {
            persistenceStrategy.save(members);
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
            members = persistenceStrategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Die Implementierung ist nicht verfügbar!");
        }
    }
}
