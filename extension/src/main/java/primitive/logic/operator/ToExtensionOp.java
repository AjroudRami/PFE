package primitive.logic.operator;

import businessData.ExtensionOperator;
import kobdig.logic.Operator;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class ToExtensionOp implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Operator op = (Operator) args[0].get();
        return new ExtensionOperator(op);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
