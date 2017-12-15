package fr.polytech.unice.dbi.primitive.logic.truthdegree;

import kobdig.logic.TruthDegree;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class DoubleValue implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        TruthDegree truthDegree = (TruthDegree) args[0].get();
        return truthDegree.doubleValue();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.NumberType());
    }
}
