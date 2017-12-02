package primitive;

import behavior.Behavior;
import behavior.BehaviorBuilder;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.io.IOException;

public class ImportDBIBehavior implements Reporter {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(Syntax.ReferenceType());
    }

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String filename = args[0].getString();
        Behavior behavior;
        try {
            behavior = BehaviorBuilder.build(filename);
        } catch (IOException e) {
            throw new ExtensionException(e.getMessage());
        }
        return behavior;
    }
}
