package fr.polytech.unice.dbi.primitive.logic.factSet;

import kobdig.agent.Fact;
import kobdig.agent.FactSet;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class Membership implements Reporter {

    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        FactSet set = (FactSet) args[0].getAgentSet();
        Fact fact = (Fact) args[1].get();
        return set.membership(fact);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType(), Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
