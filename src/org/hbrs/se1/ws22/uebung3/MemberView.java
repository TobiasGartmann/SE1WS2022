package org.hbrs.se1.ws22.uebung3;

import java.util.List;

public class MemberView {
    public void dump(List<Member> list) {
        for (Member mem : list) {
            System.out.println(mem.toString());
        }
    }
}
