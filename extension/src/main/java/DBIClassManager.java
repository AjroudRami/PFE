import org.nlogo.api.*;

public class DBIClassManager extends DefaultClassManager {
    public void load(PrimitiveManager primitiveManager) {
        primitiveManager.addPrimitive(
                "first-n-integers", new FirstIntegers());
        primitiveManager.addPrimitive("first-command", new FirstCommand());
    }
}