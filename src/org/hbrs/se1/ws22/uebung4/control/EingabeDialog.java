package org.hbrs.se1.ws22.uebung4.control;

import org.hbrs.se1.ws22.uebung4.model.Container;
import org.hbrs.se1.ws22.uebung4.model.entities.Employee;
import org.hbrs.se1.ws22.uebung4.model.exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.view.EmployeeView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EingabeDialog {
    private static Scanner scanner = new Scanner(System.in);
    private static Container container = Container.getInstance();
    public void input() {
        System.out.println("Type 'help' for further information.");
        loop:
        while (true) {
            String s = scanner.next();

            switch (s) {
                case "enter":
                    enter();
                    break;
                case "store":
                    store();
                    break;
                case "load":
                    load();
                    break;
                case "dump":
                    dump();
                    break;
                case "search":
                    search();
                    break;
                case "exit":
                    break loop;
                case "help":
                    help();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }

    }

    public void enter() {
        Employee emp = new Employee();
        String tmp = "";

        while (true) {
            System.out.print("Vorname: ");
            tmp = scanner.next();
            if (!containsInt(tmp)) {
                emp.setVorname(tmp);
                break;
            } else {
                System.out.println("Zahlen sind nicht erlaubt");
            }
        }

        while (true) {
            System.out.print("Nachname: ");
            tmp = scanner.next();
            if (!containsInt(tmp)) {
                emp.setNachname(tmp);
                break;
            } else {
                System.out.println("Zahlen sind nicht erlaubt");
            }
        }

        while (true) {
            System.out.print("PID: ");
            tmp = scanner.next();
            if (containsInt(tmp)) {
                emp.setPid(Integer.valueOf(tmp));
                break;
            } else {
                System.out.println("Buchstaben sind nicht erlaubt");
            }
        }

        while (true) {
            System.out.print("Rolle: ");
            tmp = scanner.next();
            if (!containsInt(tmp)) {
                emp.setRolle(tmp);
                break;
            } else {
                System.out.println("Zahlen sind nicht erlaubt");
            }
        }

        while (true) {
            System.out.println("Abteilung angeben? (y/n)");
            tmp = scanner.next();
            if (tmp.equals("y")) {
                System.out.print("Abteilung: ");
                emp.setAbteilung(scanner.next());
                break;
            } else if (tmp.equals("n")) {
                emp.setAbteilung("");
                break;
            }
        }

        String[][] expertise = {{"", ""}, {"", ""}, {"", ""}};
        int i = 0;
        while (i < 3) {
            System.out.println("Expertise " + (i + 1) + " angeben? (y/n)");
            tmp = scanner.next();

            if (tmp.equals("y")) {
                System.out.print("Expertise: ");
                expertise[i][0] = scanner.next();


                while (true) {
                    System.out.print("Expertise-Level (1-3): ");
                    tmp = scanner.next();
                    if (Integer.parseInt(tmp) >= 1 && Integer.parseInt(tmp) <= 3) {
                        expertise[i][1] = tmp;
                        break;
                    } else {
                        System.out.println("Invalides Expertisen-Level");
                    }
                }

                ++i;
            } else if (tmp.equals("n")) {
                break;
            }
        }

        emp.setExpertise(expertise);

        try {
            container.add(emp);
        } catch (Exception e) {
            System.out.println("Error while entering an employee - please try again");
        }
    }

    public void store() {
        try {
            container.store();
        } catch (PersistenceException e) {
            System.out.println("Error while storing - please try again");
        }
    }

    public void load() {
        System.out.println("Lade-Modus ('merge' oder 'force'): ");
        String s = scanner.next();

        try {
            if (s.equals("merge") || s.equals("force")) {
                container.load(s);
            } else {
                System.out.println(s + " - mode not specified");
            }
        } catch (PersistenceException e) {
            System.out.println("Error while loading - please try again");
        }
    }

    public void dump() {
        List<Employee> l = container.getCurrentList();
        System.out.println("Wonach soll gefiltert werden (-p, -v, -n, -r, -a, -all)?");
        String s = scanner.next();
        if (!s.equals("-all")) {
            System.out.println("Nach welchen String soll gefiltert werden?");
            String com = scanner.next();
            switch (s) {
                case "-p":
                    l = l.stream().filter(employee -> employee.getPid().equals(com)).collect(Collectors.toList());
                    break;
                case "-v":
                    l = l.stream().filter(employee -> employee.getVorname().equals(com)).collect(Collectors.toList());
                    break;
                case "-n":
                    l = l.stream().filter(employee -> employee.getNachname().equals(com)).collect(Collectors.toList());
                    break;
                case "-r":
                    l = l.stream().filter(employee -> employee.getRolle().equals(com)).collect(Collectors.toList());
                    break;
                case "-a":
                    l = l.stream().filter(employee -> employee.getAbteilung().equals(com)).collect(Collectors.toList());
                    break;
                default:
                    System.out.println("Filter existiert nicht");
                    break;
            }
        }
        EmployeeView.dump(l);
    }

    public void search() {
        System.out.println("Such-Modus (Gebe Expertise an): ");
        String s = scanner.next();
        EmployeeView.search(container.getCurrentList(), s);
    }

    public void help() {
        System.out.println("Mögliche Befehle [enter] [store] [load] [dump] [search] [exit] [help]");
    }

    public boolean containsInt(String s) {
        boolean r = false;
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                r = true;
            }
        }
        return r;
    }
}
