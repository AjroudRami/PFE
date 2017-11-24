import command.UpdateTurtles;
import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

public class ExtensionClassManager extends DefaultClassManager {
    public void load(PrimitiveManager primitiveManager) {
        primitiveManager.addPrimitive(
                "first-n-integers", new FirstIntegers());
        primitiveManager.addPrimitive("first-command", new FirstCommand());
        primitiveManager.addPrimitive("update-agent", new UpdateTurtles());
    }
}