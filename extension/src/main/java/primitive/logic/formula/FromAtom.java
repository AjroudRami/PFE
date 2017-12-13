package primitive.logic.formula;

import kobdig.logic.Atom;
import kobdig.logic.Formula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a reporter that create a Formula object
 */
public class FromAtom implements Reporter {

    /**
     * This method creates a Formula object from an Atom and returns a reference to the created Formula.
     *
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Atom atom = (Atom) args[0].get();
        Formula formula = new Formula(atom);
        return formula;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
