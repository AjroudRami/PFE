package businessData;

import kobdig.logic.Operator;
import org.nlogo.core.ExtensionObject;
import primitive.ExtensionClassManager;

public class ExtensionOperator implements ExtensionObject {

    private Operator operator;

    public ExtensionOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String dump(boolean readable, boolean exporting, boolean reference) {
        return operator.toString();
    }

    @Override
    public String getExtensionName() {
        return ExtensionClassManager.EXTENSION_NAME;
    }

    @Override
    public String getNLTypeName() {
        return operator.getClass().getName();
    }

    @Override
    public boolean recursivelyEqual(Object obj) {
        return operator.equals(obj);
    }
}
