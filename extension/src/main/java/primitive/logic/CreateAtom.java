package primitive.logic;

import kobdig.logic.Atom;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class is used to create a logical Atom in Netlogo
 * It can be
 * an atomic proposition (propositional logic),
 * a predicate (predicate logic),
 * an atomic concept (description logic) ...
 */
public class CreateAtom implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String atomName = args[0].getString();
        Atom atom = new Atom(atomName);
        return atom;
    }

    /**
     * This reporter only takes one argument, the name of the atom as a string, and returns a reference to the Atom
     * object
     *
     * @return the Atom
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.WildcardType());
    }
}
