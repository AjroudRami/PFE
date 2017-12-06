package primitive;

import kobdig.agent.Fact;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class GetFactBeliefFactor implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        //TODO flaw in conception, dbi behavior is assumed to be index 0 in Agent variables
        Fact fact = (Fact) args[0].get();
        Agent agent = context.getAgent();
        kobdig.agent.Agent dbi = (kobdig.agent.Agent) agent.getVariable(0);
        return dbi.believes(fact).doubleValue();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType()}, Syntax.NumberType());
    }
}
