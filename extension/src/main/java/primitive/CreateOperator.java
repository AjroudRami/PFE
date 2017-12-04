package primitive;

import kobdig.logic.Operator;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;

public class CreateOperator implements Reporter {
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

    private Operator parseOperator(String name) {
        switch (name) {
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
