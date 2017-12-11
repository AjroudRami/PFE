package primitive.agent.behavior;

import kobdig.agent.Agent;
import kobdig.agent.Fact;
import kobdig.agent.FactSet;
import org.nlogo.api.*;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.Iterator;

/**
 * This class implements a primitive used to retreive the DBI Agent Goals
 */
public class GetGoals implements Reporter {

    /**
     * This primitive returns a FactSet bundled in a LogoList object
     *
     * @param args
     * @param context
     * @return a FactSet bundled in a LogoList object
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        Agent dbi = (Agent) args[0].get();
        FactSet facts = dbi.goals();
        Iterator<Fact> iterator = facts.factIterator();
        LogoListBuilder builder = new LogoListBuilder();
        while (iterator.hasNext()) {
            builder.add(iterator.next());
        }
        return builder.toLogoList();
    }

    /**
     * It takes no arguments
     *And returns a LogoList of Fact
     * @return LogoList<Fact> type
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.ReferenceType()}, Syntax.ListType());
    }
}
