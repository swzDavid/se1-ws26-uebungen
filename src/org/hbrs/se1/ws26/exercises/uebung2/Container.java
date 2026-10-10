package org.hbrs.se1.ws26.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) throws ContainerException {
        checkMember(member);
        members.add(member);
    }

    public void checkMember(Member member) throws ContainerException {
        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(
                        "Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
    }
}
