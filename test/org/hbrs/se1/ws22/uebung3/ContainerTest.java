package org.hbrs.se1.ws22.uebung3;

import org.hbrs.se1.ws22.uebung3.persistence.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container con = Container.getInstance();

    @Test
    public void noStrategyTest() {
        con.setPersistenceStrategy(null);
        Throwable exception = assertThrows(java.lang.NullPointerException.class,
                () -> con.store());
    }

    @Test
    public void mongoStrategyTest() {
        con.setPersistenceStrategy(new PersistenceStrategyMongoDB<Member>());
        Throwable exception = assertThrows(java.lang.UnsupportedOperationException.class,
                () -> con.store());
    }

    @Test
    public void flawedLocationTest() {
        PersistenceStrategyStream<Member> ps = new PersistenceStrategyStream<Member>();
        ps.setLocation("test\\");
        con.setPersistenceStrategy(ps);

        Member m1 = new ConcreteMember(7);
        try {
            con.addMember(m1);
        } catch (ContainerException e) {
            fail();
        }
        Throwable exception = assertThrows(org.hbrs.se1.ws22.uebung3.persistence.PersistenceException.class,
                () -> con.store());
    }

    @Test
    public void scenarioTest() {
        PersistenceStrategyStream<Member> ps = new PersistenceStrategyStream<Member>();
        ps.setLocation("test\\test.txt");
        con.setPersistenceStrategy(ps);

        List<Member> tmp = con.getCurrentList();
        assertEquals(0, tmp.size());

        Member m1 = new ConcreteMember(7);
        Member m2 = new ConcreteMember(11);
        Member m3 = new ConcreteMember(17);
        try {
            con.addMember(m1);
            con.addMember(m2);
            con.addMember(m3);
        } catch (ContainerException e) {
            fail();
        }

        tmp = con.getCurrentList();
        assertEquals(3, tmp.size());

        try {
            con.store();
        } catch (PersistenceException e) {
            fail();
        }

        tmp = con.getCurrentList();
        assertEquals(3, tmp.size());

        assertEquals("", con.deleteMember(11));
        assertEquals("", con.deleteMember(7));
        assertEquals("", con.deleteMember(17));

        tmp = con.getCurrentList();
        assertEquals(0, tmp.size());

        try {
            con.load();
        } catch (PersistenceException e) {
            fail();
        }

        tmp = con.getCurrentList();
        assertEquals(3, tmp.size());

    }

}