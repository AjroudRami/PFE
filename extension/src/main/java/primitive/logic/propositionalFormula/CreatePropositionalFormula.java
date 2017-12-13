package primitive.logic.propositionalFormula;

import kobdig.logic.Operator;
import kobdig.logic.PropositionalFormula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreatePropositionalFormula implements Reporter {

    private static Logger LOGGER = Logger.getLogger(CreatePropositionalFormula.class.getName());
    /**
     * Create a propositional formula from an operator and Formulas
     * The operator arity and the number of args must match
     *
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Operator o;
        PropositionalFormula propositionalFormula;
        try {
            o = (Operator) args[0].get();
            int size = args.length - 1;
            PropositionalFormula[] formulas = new PropositionalFormula[size];
            LOGGER.log(Level.INFO, "creating array of PropositionalFormula of size: " + size);
            for (int i = 0; i < size; i++) {
                LOGGER.log(Level.INFO, "Getting formula " + i + " from args");
                formulas[i] = (PropositionalFormula) args[i + 1].get();
                LOGGER.log(Level.INFO, "item " + i + " " + formulas[i].toString());
            }
            LOGGER.log(Level.INFO, "Formulas collected, Creating PropositionalFormula");
            propositionalFormula = new PropositionalFormula(o, formulas);
        } catch (ClassCastException c) {
            LOGGER.log(Level.SEVERE, "Error while creating PropositionalFormula, ClassCastException");
            throw new ExtensionException(c.getMessage());
        }
        return propositionalFormula;
    }

    /**
     * This Reporter takes an Operator (mandatory) and a variable number of Formulas (min 1) as arguments
     * @return a Syntax corresponding to this Reporter usage
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType() | Syntax.RepeatableType()},
                Syntax.WildcardType(), 2, 2);
    }
}
