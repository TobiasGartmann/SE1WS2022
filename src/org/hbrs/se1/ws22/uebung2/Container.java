package org.hbrs.se1.ws22.uebung2;

import java.util.LinkedList;

public class Container {
    private LinkedList<Member> list = new LinkedList<Member>();

    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            throw new ContainerException("Das übergebene Member-Objekt ist null");
        }

        for (Member mem : list) {
            if (mem.getID() == member.getID()) {
                throw new ContainerException("Das Member-Objekt mit der ID " + mem.getID() +" ist bereits vorhanden!");
            }
        }
        list.add(member);
    }

    public String deleteMember(Integer id) {
        Member tmp = null;
        for (Member mem : list) {
            if (mem.getID() == id) {
                tmp = mem;
                list.remove(mem);
            }
        }
        if (tmp == null) {
            return "Member not found (ID = " + id + ")";
        }
        return "";
    }

    public void dump() {
        for (Member mem : list) {
            System.out.println(mem.toString());
        }
    }

    public int size() {
        return list.size();
    }
}
