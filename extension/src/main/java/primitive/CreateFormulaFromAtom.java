package primitive;

import kobdig.logic.Atom;
import kobdig.logic.Formula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class CreateFormulaFromAtom implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Atom atom = (Atom) args[0].get();
        Formula formula = new Formula(atom);
        return formula;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType()}, Syntax.ReferenceType());
    }
}
