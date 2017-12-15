package fr.polytech.unice.dbi.primitive.agent.storage;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class UpdateDBIBehavior implements Command {

    private DBIStorage storage;

    public UpdateDBIBehavior(DBIStorage storage) {
        this.storage = storage;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.WildcardType(), Syntax.StringType()});
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        DBIAgent agent = (DBIAgent) args[0].get();
        String name = args[1].getString();

        this.storage.updateAgent(name, agent);
    }
}
