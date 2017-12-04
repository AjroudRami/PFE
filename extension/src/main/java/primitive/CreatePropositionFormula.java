package primitive;

import kobdig.logic.Formula;
import kobdig.logic.Operator;
import kobdig.logic.PropositionalFormula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class CreatePropositionFormula implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Operator o = (Operator) args[0].get();
        Formula[] formulas = (Formula[]) args[1].get();
        PropositionalFormula propositionalFormula = new PropositionalFormula(o, formulas);
        return propositionalFormula;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.ReferenceType());
    }
}
