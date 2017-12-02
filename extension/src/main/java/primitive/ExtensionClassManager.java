package primitive;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

public class ExtensionClassManager extends DefaultClassManager{
    @Override
    public void load(PrimitiveManager primManager) throws ExtensionException {
        primManager.addPrimitive(Primitives.ADD_BEHAVIOR, new AddBehavior());
        primManager.addPrimitive(Primitives.DELETE_BEHAVIOR, new DeleteBehavior());
        primManager.addPrimitive(Primitives.EXPORT_DBI_BEHAVIOR, new ExportDBIBehavior());
        primManager.addPrimitive(Primitives.IMPORT_DBI_BEHAVIOR, new ImportDBIBehavior());
    }
}
