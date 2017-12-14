package primitive.logic.formula;

import kobdig.logic.Atom;
import kobdig.logic.Formula;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.Iterator;
import java.util.Set;

/**
 * This class implements a Primitive used to retrieve the Atoms from a Formula
 */
public class GetAtoms implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        LogoListBuilder builder = new LogoListBuilder();
        Formula formula = (Formula) args[0].get();
        Set<Atom> atoms = formula.atomSet();
        Iterator<Atom> it = atoms.iterator();
        while (it.hasNext()) {
            builder.add(it.next());
        }
        return builder.toLogoList();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.ListType());
    }
}
