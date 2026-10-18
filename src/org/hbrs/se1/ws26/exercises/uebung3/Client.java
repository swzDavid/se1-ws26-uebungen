package org.hbrs.se1.ws26.exercises.uebung3;

import org.hbrs.se1.ws26.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung2.Container;
import org.hbrs.se1.ws26.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung2.Member;

public class Client {

    public void run() {
        Container container = Container.getInstance();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);
        MemberView mv = new MemberView();

        try {
            container.addMember(m1);
            container.addMember(m2);
            container.addMember(m3);
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }

        mv.dump(container.getCurrentList());

    }


}
