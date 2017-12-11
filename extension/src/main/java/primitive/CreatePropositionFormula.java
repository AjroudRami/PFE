package primitive;

import kobdig.logic.Formula;
import kobdig.logic.Operator;
import kobdig.logic.PropositionalFormula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.Iterator;

public class CreatePropositionFormula implements Reporter {

    /**
     * Create a propositional formula from an operator and a LogoList of formula
     * The operator arity and the length of the Logolist array must match.
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Operator o = (Operator) args[0].get();
        int size = args[1].getList().length();
        Iterator<Formula> formulaList = (Iterator) args[1].getList().iterator();
        Formula[] formulas =  new Formula[size];
        for (int i = 0; i < size; i++) {
            formulas[i] = formulaList.next();
        }
        PropositionalFormula propositionalFormula = new PropositionalFormula(o, formulas);
        return propositionalFormula;
    }

    /**
     * This Reporter takes an Operator and a Logolist as arguments.
     * @return a Syntax corresponding to this Reporter usage
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType()}, Syntax.ListType());
    }
}
