package primitive;

import kobdig.agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImportDBIBehavior implements Reporter {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(Syntax.ReferenceType());
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String filename = args[0].getString();
        Agent agent;
        try {
            File file = new File(filename);
            InputStream inputStream = new FileInputStream(file);
            agent = new Agent(inputStream);
        } catch (IOException e) {
            throw new ExtensionException(e.getMessage());
        }
        return agent;
    }
}
