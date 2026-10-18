package org.hbrs.se1.ws26.exercises.uebung3;

import org.hbrs.se1.ws26.exercises.uebung2.Member;

import java.util.List;

public class MemberView {

    public void dump(List<Member> liste) {
        for (Member m : liste) {
            System.out.println(m.toString());
        }
    }
}
