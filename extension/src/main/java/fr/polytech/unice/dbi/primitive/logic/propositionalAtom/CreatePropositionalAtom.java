package fr.polytech.unice.dbi.primitive.logic.propositionalAtom;

import kobdig.logic.PropositionalAtom;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a fr.polytech.unice.dbi.primitive that is used to create logicial PropositionalAtom.
 */
public class CreatePropositionalAtom implements Reporter {

    /**
     * This create a logical PropositionalAtom and returns it bundled in an Object
     *
     * @param args    contains a String, the atoms name
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        String atomName = args[0].getString();
        PropositionalAtom atom = new PropositionalAtom(atomName);
        return atom;
    }

    /**
     * This fr.polytech.unice.dbi.primitive takes a String as arguments and returns a reference to the PropositionalAtom object.
     *
     * @return
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.WildcardType());
    }
}
