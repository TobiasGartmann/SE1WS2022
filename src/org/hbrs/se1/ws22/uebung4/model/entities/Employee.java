package org.hbrs.se1.ws22.uebung4.model.entities;

import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee> {
    private String vorname;
    private String nachname;
    private Integer pid;
    private String abteilung;

    private String rolle;

    private String expertise[][] = new String[3][2];

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String toString() {
        return "Mitarbeiter Nr. " + getPid()+ ",Name: " + getVorname()
                + ", Nachname: " + getNachname()+", Rolle: " + getRolle()
                + ", Abteilung: " + getAbteilung();
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String[][] getExpertise() {
        return expertise;
    }

    public void setExpertise(String[][] expertise) {
        this.expertise = expertise;
    }

    @Override
    public int compareTo(Employee o) {
        return pid.compareTo(o.getPid());
    }
}
