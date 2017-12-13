package primitive.logic.operator;

import kobdig.logic.Operator;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a Netlogo primitive that is used to create a logical operator
 */
public class CreateOperator implements Reporter {

    /**
     * Depending on the number of arguments, the user can declare a standard operator (AND, OR, NOT, XOR),
     * or create a new one. If declaring a new operator, the user must give the name and the arity of the operator.
     * If the number of arguments exceeds 2 or if the arguments does not match the normal usage, an {@link ExtensionException}
     * is thrown
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        System.out.println(args.length);
        String opName = args[0].getString();
        System.out.println(opName);
        Operator op;
        if (args.length == 1) {
            op = parseOperator(opName);
        } else if (args.length == 2) {
            int arity = args[1].getIntValue();
            op = new kobdig.logic.Operator(opName, arity);
        } else {
            throw new ExtensionException("Illegal arguments");
        }
        return op;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType() | Syntax.RepeatableType()}, Syntax.WildcardType(), 1, 1);
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
        Operator res;
        switch (nameToUpper) {
            case "AND":
                res = Operator.AND;
                break;
            case "OR":
                res = Operator.OR;
                break;
            case "NOT":
                res = Operator.NOT;
                break;
            case "XOR":
                res = Operator.XOR;
                break;
            default:
                throw new IllegalArgumentException("invalid Operator name: " + name);
        }
        System.out.println(res.toString());
        return res;
    }
}
