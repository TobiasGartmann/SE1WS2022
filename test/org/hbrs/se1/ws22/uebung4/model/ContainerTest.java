package org.hbrs.se1.ws22.uebung4.model;

import org.hbrs.se1.ws22.uebung4.model.entities.Employee;
import org.hbrs.se1.ws22.uebung4.model.exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.model.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container con = Container.getInstance();

    @BeforeEach
    void setup() {
        con.setPersistenceStrategy(new PersistenceStrategyStream());
        Employee e1 = new Employee();
        e1.setPid(1);
        e1.setVorname("Alexander");
        e1.setNachname("Calvin");

        Employee e2 = new Employee();
        e2.setPid(2);
        e2.setVorname("Marie");
        e2.setNachname("Calvin");

        try {
            con.add(e1);
            con.add(e2);
        } catch (ContainerException e) {
            fail();
        }

        try {
            con.store();
        } catch (PersistenceException e) {
            fail();
        }

        Employee e3 = new Employee();
        e3.setPid(3);
        e3.setVorname("Devin");
        e3.setNachname("Calvin");

        try {
            con.add(e3);
        } catch (ContainerException e) {
            fail();
        }
        con.delete(1);
    }

    @Test
    void load() {
        assertEquals(2, con.size());

        try {
            con.load("force");
        } catch (PersistenceException e) {
            fail();
        }

        assertEquals(2, con.size());

        con.delete(1);
        con.delete(2);
        con.delete(3);

        assertEquals(0, con.size());

        try {
            con.load("merge");
        } catch (PersistenceException e) {
            fail();
        }

        assertEquals(2, con.size());

        con.delete(1);
        con.delete(2);

        try {
            con.load("merge");
        } catch (PersistenceException e) {
            fail();
        }

        assertEquals(2, con.size());

        con.delete(1);
        con.delete(2);

        for (Employee ee : con.getCurrentList()) {
            System.out.println(ee.toString());
        }

        assertEquals(0, con.size());

        try {
            con.load("abs");
        } catch (PersistenceException e) {
            fail();
        }

        assertEquals(0, con.size());


    }
}