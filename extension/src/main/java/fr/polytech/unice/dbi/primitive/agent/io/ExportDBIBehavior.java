package fr.polytech.unice.dbi.primitive.agent.io;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Primitive used to export a DBIAgent to a file
 * The DBIAgent toString() gives a String file from which we can reimport the agent.
 */
public class ExportDBIBehavior implements Command {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.WildcardType(), Syntax.StringType()});
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        DBIAgent agent = (DBIAgent) args[0].get();
        String filename = args[1].getString();
        PrintWriter out;
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new ExtensionException(e.getMessage());
            }
        }
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new ExtensionException(e.getMessage());
        }
        out.println(agent.toString());
        out.flush();
        out.close();
    }
}
