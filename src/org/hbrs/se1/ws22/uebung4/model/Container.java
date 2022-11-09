package org.hbrs.se1.ws22.uebung4.model;

import org.hbrs.se1.ws22.uebung4.model.entities.Employee;
import org.hbrs.se1.ws22.uebung4.model.exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.model.exceptions.ContainerException;

import java.util.LinkedList;
import java.util.List;

public class Container {
    private static Container container = new Container();
    private LinkedList<Employee> list = new LinkedList<>();
    private PersistenceStrategy persistenceStrategy;

    private Container() { }

    // Singleton Pattern
    public static synchronized Container getInstance() {
        return container;
    }

    public void add(Employee member) throws ContainerException {
        if (member == null) {
            throw new ContainerException("Das übergebene Member-Objekt ist null");
        }

        for (Employee mem : list) {
            if (mem.getPid().intValue() == member.getPid().intValue()) {
                throw new ContainerException("Das Member-Objekt mit der ID " + mem.getPid() +" ist bereits vorhanden!");
            }
        }
        list.add(member);
    }

    public String delete(Integer id) {
        Employee tmp = null;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getPid().intValue() == id.intValue()) {
                tmp = list.get(i);
                list.remove(list.get(i));
                break;
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

    public List<Employee> getCurrentList() {
        return (List<Employee>) list.clone();
    }

    public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy initialized.");
        }

        persistenceStrategy.save(list);
    }

    public void load(String mode) throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy initialized.");
        }

        if (mode.equals("force")) {
            list = (LinkedList<Employee>) persistenceStrategy.load();
        } else if (mode.equals("merge")) {
            List<Employee> tmp = (LinkedList<Employee>) persistenceStrategy.load();
            for (Employee emp : tmp) {
                try {
                    add(emp);
                } catch (ContainerException e) { }
            }
        }
    }
}
