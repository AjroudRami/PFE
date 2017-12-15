package fr.polytech.unice.dbi.util;

import org.nlogo.api.ExtensionException;

public class ObjectCaster<E> {

    public ObjectCaster() {
    }

    public E cast(Object obj) throws ExtensionException {
        if (obj == null) {
            throw new ExtensionException("Trying to get a null Object");
        }
        try {
            return (E) obj;
        } catch (ClassCastException e) {
            throw new ExtensionException(e.getMessage());
        }
    }
}
