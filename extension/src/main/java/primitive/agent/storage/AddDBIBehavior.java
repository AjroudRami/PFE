package primitive.agent.storage;

import kobdig.agent.Agent;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import primitive.agent.storage.DBIStorage;

/**
 * This class implements a primitive used to store a DBI Agent in the DBIStorage object.
 */
public class AddDBIBehavior implements Command {

    private DBIStorage storage;

    public AddDBIBehavior(DBIStorage storage) {
        this.storage = storage;
    }

    /**
     * It takes an Agent and a String (the agent's name) as arguments.
     * @param args
     * @param context
     * @throws ExtensionException
     */
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent dbi = (Agent) args[0].get();
        String name = args[1].toString();
        this.storage.setAgent(name, dbi);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.ReferenceType(), Syntax.StringType()});
    }
}