package org.hbrs.s1.ws22.uebung1.control;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void translateNumber() {
        GermanTranslator gt = new GermanTranslator();
        assertEquals("eins", gt.translateNumber(1));
        assertEquals("zehn", gt.translateNumber(10));
        assertEquals("Übersetzung der Zahl " + 0 + " nicht möglich (1.0)", gt.translateNumber(0));
        assertEquals("Übersetzung der Zahl " + 11 + " nicht möglich (1.0)", gt.translateNumber(11));
        assertEquals("Übersetzung der Zahl " + Integer.MAX_VALUE + " nicht möglich (1.0)",
                gt.translateNumber(Integer.MAX_VALUE));
        assertEquals("Übersetzung der Zahl " + Integer.MIN_VALUE + " nicht möglich (1.0)",
                gt.translateNumber(Integer.MIN_VALUE));

    }
}