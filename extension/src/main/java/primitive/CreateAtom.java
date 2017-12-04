package primitive;

import kobdig.logic.Atom;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class CreateAtom implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String atomName = args[0].getString();
        Atom atom = new Atom(atomName);
        return atom;
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.ReferenceType());
    }
}
