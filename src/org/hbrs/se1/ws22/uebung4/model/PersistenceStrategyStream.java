package org.hbrs.se1.ws22.uebung4.model;

import org.hbrs.se1.ws22.uebung4.model.exceptions.PersistenceException;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        System.out.println("in Methode open");
        try {
            fos = new FileOutputStream(location);
            fis = new FileInputStream(location);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, File could not be found");
        }
        try {
            oos = new ObjectOutputStream(fos);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            if (fis != null) { fis.close(); }
            if (ois != null) { ois.close(); }
            if (fos != null) { fos.close(); }
            if (oos != null) { oos.close(); }
        } catch (IOException e) {
            String msg = "Couldn't close connection!";
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, msg);
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException {
        try {
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            String msg = "Couldn't open connection!";
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, msg);
        }

        try {
            oos.writeObject(member);
        } catch (IOException e) {
            String msg = "Couldn't save stream!";
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, msg);
        } finally {
            closeConnection();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        try {
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            String msg = "Couldn't open connection!";
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, msg);
        }

        List<E> list = null;
        try {
            Object o = ois.readObject();
            if (o instanceof List<?>) {
                list = (List<E>) o;
            }
            return list;
        } catch (IOException | ClassNotFoundException e) {
            String msg = "Couldn't load stream!";
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, msg);
        } finally {
            closeConnection();
        }
    }
}
