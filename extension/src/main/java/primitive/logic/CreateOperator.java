package primitive.logic;

import kobdig.logic.Operator;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;

/**
 * This class implements a Netlogo primitive that is used to create a logical operator
 */
public class CreateOperator implements Reporter {

    /**
     * Depending on the number of arguments, the user can declare a standard operator (AND, OR, NOT, XOR),
     * or create a new one. If declaring a new operator, the user must give the name and the arity of the operator.
     *
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String opName = args[0].getString();
        Operator op;
        if (args.length == 1) {
            op = parseOperator(opName);
        } else if (args.length == 2) {
            int arity = args[1].getIntValue();
            op = new Operator(opName, arity);
        } else {
            throw new ExtensionException("Illegal arguments");
        }
        return op;
    }

    @Override
    public Syntax getSyntax() {
        return null;
    }

    /**
     * Create a standard operator from its String name
     *
     * @param name
     * @return
     */
    private Operator parseOperator(String name) {
        //Avoid case problems
        String nameToUpper = name.toUpperCase();
        switch (nameToUpper) {
            case "AND":
                return Operator.AND;
            case "OR":
                return Operator.OR;
            case "NOT":
                return Operator.NOT;
            case "XOR":
                return Operator.XOR;
            default:
                return null;
        }
    }
}
