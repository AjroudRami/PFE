package primitive.agent.behavior;

import kobdig.agent.Agent;
import kobdig.agent.Fact;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class GetFactBeliefFactor implements Reporter {

    /**
     * This method takes an agent and a fact and returns the belief factor of the agent.
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Agent dbi = (Agent) args[0].get();
        Fact fact = (Fact) args[1].get();
        return dbi.believes(fact).doubleValue();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType(), Syntax.ReferenceType()}, Syntax.NumberType());
    }
}
