package fr.polytech.unice.dbi.primitive.agent.behavior;

import kobdig.agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class GetGoalFactSet implements Reporter {

    /**
     * Returns the DBIAgent Goals as a Factset
     *
     * @param args
     * @param context
     * @return
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Agent agent = (Agent) args[0].get();
        return agent.goals();
    }

    /**
     * It takes one argument, the DBIAgent
     * And returns a LogoList of Fact
     *
     * @return LogoList<Fact> type
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.WildcardType());
    }
}
