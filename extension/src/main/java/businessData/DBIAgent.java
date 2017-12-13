package businessData;

import kobdig.agent.Agent;
import org.nlogo.core.ExtensionObject;
import primitive.ExtensionClassManager;

import java.io.IOException;
import java.io.InputStream;

public class DBIAgent extends Agent implements ExtensionObject {


    public DBIAgent() {
        super();
    }

    public DBIAgent(InputStream input) throws IOException {
        super(input);
    }


    @Override
    public String dump(boolean readable, boolean exporting, boolean reference) {
        return super.toString();
    }

    @Override
    public String getExtensionName() {
        return ExtensionClassManager.EXTENSION_NAME;
    }

    @Override
    public String getNLTypeName() {
        return this.getClass().getName();
    }

    @Override
    public boolean recursivelyEqual(Object obj) {
        return super.equals(obj);
    }
}
