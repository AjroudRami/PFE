package fr.polytech.unice.dbi.primitive.agent.storage;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class GetDBIBehavior implements Reporter {

    private DBIStorage storage;

    public GetDBIBehavior(DBIStorage storage) {
        this.storage = storage;
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String name = args[0].getString();
        DBIAgent agent = storage.getAgent(name);
        if (agent == null) {
            throw new ExtensionException("Agent name: " + name + "is not registred in the DBIStorage.");
        }
        return agent;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.WildcardType());
    }
}
