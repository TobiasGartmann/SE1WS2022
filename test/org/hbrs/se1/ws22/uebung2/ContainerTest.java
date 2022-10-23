package org.hbrs.se1.ws22.uebung2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    @Test
    void ContainerTest() {
        Container con = new Container();
        Member m1 = new ConcreteMember(17);
        Member m2 = new ConcreteMember(11);
        Member m3 = new ConcreteMember(11);

        assertEquals(0, con.size());

        try {
            con.addMember(m1);
            con.addMember(m2);
        } catch (Exception e) {
            fail();
        }

        assertEquals(2, con.size());

        Throwable exception = assertThrows(ContainerException.class, () -> con.addMember(m3));
        assertEquals("Das Member-Objekt mit der ID 11 ist bereits vorhanden!", exception.getMessage());

        assertEquals("Member not found (ID = 29)", con.deleteMember(29));
        assertEquals("", con.deleteMember(17));

        assertEquals(1, con.size());
    }
}
