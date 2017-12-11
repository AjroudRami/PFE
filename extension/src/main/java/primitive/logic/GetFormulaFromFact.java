package primitive.logic;

import kobdig.agent.Fact;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a reporter that help extract a Formula from a Fact
 */
public class GetFormulaFromFact implements Reporter {

    /**
     * It takes a Fact as argument and returns a Formula
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Fact fact = (Fact) args[0].get();
        return fact.formula();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[] {Syntax.ReferenceType()}, Syntax.ReferenceType());
    }
}
