package primitive.logic.formula;

import kobdig.logic.Formula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a primitive that tell if a Formula is atomic or not and returns this result as a boolean
 */
public class IsAtomic implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Formula formula = (Formula) args[0].get();
        return formula.isAtomic();
    }

    /**
     * It takes a Formula as an  argument and returns a boolean
     *
     * @return
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.BooleanType());
    }
}
