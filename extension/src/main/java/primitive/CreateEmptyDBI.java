package primitive;

import kobdig.agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class CreateEmptyDBI implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Agent agent = new Agent();
        return agent;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(Syntax.ReferenceType());
    }
}
