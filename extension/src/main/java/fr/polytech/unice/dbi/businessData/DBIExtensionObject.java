package fr.polytech.unice.dbi.businessData;

import fr.polytech.unice.dbi.primitive.ExtensionClassManager;
import org.nlogo.core.ExtensionObject;

public class DBIExtensionObject<E> implements ExtensionObject {

    private E object;

    public DBIExtensionObject(E object) {
        this.object = object;
    }

    @Override
    public String dump(boolean readable, boolean exporting, boolean reference) {
        return object.toString();
    }

    @Override
    public String getExtensionName() {
        return ExtensionClassManager.EXTENSION_NAME;
    }

    @Override
    public String getNLTypeName() {
        return object.getClass().getName();
    }

    @Override
    public boolean recursivelyEqual(Object obj) {
        return object.equals(obj);
    }
}
