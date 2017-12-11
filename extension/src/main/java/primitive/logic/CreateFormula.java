package primitive.logic;

import kobdig.logic.Formula;
import kobdig.logic.Operator;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a Reporter that create a Formula object
 */
public class CreateFormula implements Reporter {

    /**
     * This reporter takes as arguments:
     * <ul>
     *     <li>an Operator of arity n</li>
     *     <li>a LogoList of Formula of size n (the operator's arity)</li>
     * </ul>
     * It returns a reference to the create Formula object
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Operator o = (Operator) args[0].get();
        LogoList list = args[1].getList();
        int size = list.length();
        Formula[] formulas = new Formula[size];
        for (int i = 0; i < size; i++) {
            formulas[i] = (Formula) list.get(i);
        }
        Formula formula = new Formula(o, formulas);
        return formula;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType(), Syntax.ListType()}, Syntax.ReferenceType());
    }
}
