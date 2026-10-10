package org.hbrs.se1.ws26.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Member> members = new ArrayList<>();

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

    public String deleteMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
                members.remove(m);
                return "Member-Objekt mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Member-Objekt mit der ID " + id + " ist nicht vorhanden!";
    }
    /*
    Der Rückgabewert könnte vom Aufrufer ignoriert werden, da der Compiler zur Laufzeit keinen Fehler wirft.
    Dann bleibt der Fehler unbemerkt. Der Aufrufer muss den String zunächst manuell auswerten und erzwingt
    keine Fehlerbehnadlung.
     */

    public void dump() {
        for (Member m : members) {
            System.out.println(m.toString());
        }
    }

    public int size() {
        return members.size();
    }
}
