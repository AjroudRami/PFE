package fr.polytech.unice.dbi.primitive.agent.storage;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a fr.polytech.unice.dbi.primitive used to store a DBI Agent in the DBIStorage object.
 */
public class AddDBIBehavior implements Command {

    private DBIStorage storage;

    public AddDBIBehavior(DBIStorage storage) {
        this.storage = storage;
    }

    /**
     * It takes an Agent and a String (the agent's name) as arguments.
     *
     * @param args
     * @param context
     * @throws ExtensionException
     */
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        DBIAgent dbi = (DBIAgent) args[0].get();
        String name = args[1].toString();
        this.storage.addAgent(name, dbi);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.WildcardType(), Syntax.StringType()});
    }
}
