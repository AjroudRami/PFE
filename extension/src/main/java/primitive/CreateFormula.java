package primitive;

import kobdig.logic.Formula;
import kobdig.logic.Operator;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class CreateFormula implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Operator o = (Operator) args[0].get();
        //TODO check if this work or use LogoList instead
        Formula[] formulas = (Formula[]) args[1].get();
        Formula formula = new Formula(o, formulas);
        return formula;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType(), Syntax.ListType()}, Syntax.ReferenceType());
    }
}
