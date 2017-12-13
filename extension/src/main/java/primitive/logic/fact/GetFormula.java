package primitive.logic.fact;

import kobdig.agent.Fact;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class GetFormula implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Fact fact = (Fact) args[0].get();
        return fact.formula();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
