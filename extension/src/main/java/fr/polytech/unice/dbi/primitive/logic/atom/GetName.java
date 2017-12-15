package fr.polytech.unice.dbi.primitive.logic.atom;

import kobdig.logic.Atom;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * Primitive used to return an Atom name
 */
public class GetName implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Atom atom = (Atom) args[0].get();
        return atom.toString();
    }

    /**
     * Takes an Atom and returns a String
     *
     * @return
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.StringType());
    }
}
