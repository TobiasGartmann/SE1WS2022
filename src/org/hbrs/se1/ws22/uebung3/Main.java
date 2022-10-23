package org.hbrs.se1.ws22.uebung3;

import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args) {
        Container.getInstance().setPersistenceStrategy(new PersistenceStrategyStream<Member>());
        Client client = new Client();
        client.viewContainer();
    }
}
