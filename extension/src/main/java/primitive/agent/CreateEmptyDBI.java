package primitive.agent;

import kobdig.agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a primitive used to create DBI Agents.
 * The agent created by this primitive will be empty of any proposition and/or logic.
 * The user might initialize its belief and knowledge in order to use it
 */
public class CreateEmptyDBI implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) {
        Agent agent = new Agent();
        return agent;
    }

    /**
     * This primitive takes no arguments and returns a reference to the DBI Behavior.
     * @return
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(Syntax.ReferenceType());
    }
}
