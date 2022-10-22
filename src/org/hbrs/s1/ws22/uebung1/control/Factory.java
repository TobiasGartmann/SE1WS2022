package org.hbrs.s1.ws22.uebung1.control;

public class Factory {

    public static GermanTranslator createGermanTranslator() {
        return new GermanTranslator();
    }
}