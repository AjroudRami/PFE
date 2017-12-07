package primitive;

import kobdig.agent.Agent;
import kobdig.agent.Fact;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class GetFactDesireFactor implements Reporter {


    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Fact fact = (Fact) args[0].get();
        Agent agent = (Agent) context.getAgent().getVariable(Primitives.dbi_index);
        double factor = agent.desires(fact).doubleValue();
        return factor;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType()}, Syntax.NumberType());
    }
}
