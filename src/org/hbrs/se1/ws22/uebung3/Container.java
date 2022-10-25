package org.hbrs.se1.ws22.uebung3;

import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategy;
import java.util.LinkedList;
import java.util.List;

public class Container {
    private static Container container = new Container();
    private LinkedList<Member> list = new LinkedList<Member>();
    private PersistenceStrategy persistenceStrategy;

    private Container() { }

    // Singleton Pattern
    public static Container getInstance() {
        return container;
    }

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

    public int size() {
        return list.size();
    }

    public List<Member> getCurrentList() {
        return (List<Member>) list.clone();
    }

    public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void store() throws PersistenceException {
        persistenceStrategy.openConnection();
        persistenceStrategy.save(list);
        persistenceStrategy.closeConnection();
    }

    public void load() throws PersistenceException {
        persistenceStrategy.openConnection();
        list = (LinkedList<Member>) persistenceStrategy.load();
        persistenceStrategy.closeConnection();
    }
}
