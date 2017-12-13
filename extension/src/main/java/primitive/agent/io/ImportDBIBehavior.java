package primitive.agent.io;

import businessData.DBIAgent;
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


/**
 * This class implements a Reporter that is used to import DBI Agents from files.
 */
public class ImportDBIBehavior implements Reporter {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.WildcardType());
    }

    /**
     * This method takes one argument: a String representing the dbi agent filename to import.
     *
     * @param args
     * @param context
     * @return a DBIAgent. It returns null if an IOException occurs.
     * @throws ExtensionException if an IOException occurs
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String filename = args[0].getString();
        DBIAgent agent;
        try {
            File file = new File(filename);
            InputStream inputStream = new FileInputStream(file);
            agent = new DBIAgent(inputStream);
        } catch (IOException e) {
            throw new ExtensionException(e.getMessage());
        }
        return agent;
    }
}
