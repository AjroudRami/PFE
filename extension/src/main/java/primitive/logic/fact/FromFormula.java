package primitive.logic.fact;

import kobdig.agent.Fact;
import kobdig.logic.Formula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class FromFormula implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Formula formula = (Formula) args[0].get();
        Fact fact = new Fact(formula);
        return fact;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
