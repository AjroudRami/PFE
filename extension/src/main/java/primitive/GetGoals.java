package primitive;

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
        FactSet facts = ((Agent) context.getAgent().getVariable(Primitives.dbi_index)).goals();
        Iterator<Fact> iterator = facts.factIterator();
        LogoListBuilder builder = new LogoListBuilder();
        Agent agent = new Agent();
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
        return SyntaxJ.reporterSyntax(Syntax.ListType());
    }
}
