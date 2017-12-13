package primitive.logic.propositionalFormula;

import kobdig.logic.PropositionalAtom;
import kobdig.logic.PropositionalFormula;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FromAtom implements Reporter {

    private Logger LOGGER = Logger.getLogger(FromAtom.class.getName());

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        LOGGER.log(Level.INFO, "Creating PropositionalFormula from Atom");
        PropositionalAtom atom = (PropositionalAtom) args[0].get();
        LOGGER.log(Level.INFO, "Atom: " + atom.toString());
        return new PropositionalFormula(atom);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
