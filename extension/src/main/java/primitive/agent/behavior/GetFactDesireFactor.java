package primitive.agent.behavior;

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
        Agent dbi = (Agent) args[0].get();
        Fact fact = (Fact) args[1].get();
        double factor = dbi.desires(fact).doubleValue();
        return factor;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType(), Syntax.WildcardType()}, Syntax.NumberType());
    }
}
