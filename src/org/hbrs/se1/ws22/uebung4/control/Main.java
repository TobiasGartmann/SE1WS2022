package org.hbrs.se1.ws22.uebung4.control;

import org.hbrs.se1.ws22.uebung4.model.PersistenceStrategy;
import org.hbrs.se1.ws22.uebung4.model.Container;
import org.hbrs.se1.ws22.uebung4.model.PersistenceStrategyStream;
import org.hbrs.se1.ws22.uebung4.model.entities.Employee;

public class Main {

    public static void main(String[] args) {
        Container container = Container.getInstance();
        container.setPersistenceStrategy((PersistenceStrategy) new PersistenceStrategyStream<Employee>());
        EingabeDialog dialog = new EingabeDialog();
        dialog.input();
    }
}
