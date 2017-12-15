package fr.polytech.unice.dbi.primitive.agent.behavior;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import kobdig.agent.Fact;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This primitive returns the agent's desire truth degree for a fact as a double
 */
public class GetDesires implements Reporter {


    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        DBIAgent dbi = (DBIAgent) args[0].get();
        Fact fact = (Fact) args[1].get();
        double factor = dbi.desires(fact).doubleValue();
        return factor;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType(), Syntax.WildcardType()}, Syntax.NumberType());
    }
}
