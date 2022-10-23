package org.hbrs.se1.ws22.uebung3;

import java.io.Serializable;

public interface Member extends Serializable {

    /**
     * ID ist über einen Konstruktor einer abgeleiteten Klasse
     * explizit außerhalb der Container-Klasse zu belegen
     *  -> Primärschlüssel zur Unterscheidung aller Member-Objekte
     */

    Integer getID();

    public String toString();

}
