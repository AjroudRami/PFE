package fr.polytech.unice.dbi.primitive.agent.behavior;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import kobdig.agent.Fact;
import kobdig.agent.FactSet;
import org.nlogo.api.*;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 * This class implements a fr.polytech.unice.dbi.primitive used to retreive the DBI Agent Goals
 */
public class GetGoalLogoList implements Reporter {

    private static Logger LOGGER = Logger.getLogger(GetGoalLogoList.class.getName());
    /**
     * This fr.polytech.unice.dbi.primitive returns a FactSet bundled in a LogoList object
     *
     * @param args
     * @param context
     * @return a FactSet bundled in a LogoList object
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        DBIAgent dbi = (DBIAgent) args[0].get();
        FactSet facts = dbi.goals();
        Iterator<Fact> iterator = facts.factIterator();
        LogoListBuilder builder = new LogoListBuilder();
        if (!iterator.hasNext()) {
            return LogoList.Empty();
        }
        while (iterator.hasNext()) {
            builder.add(iterator.next());
        }
        return builder.toLogoList();
    }

    /**
     * It takes one argument, the DBIAgent
     * And returns a LogoList of Fact
     *
     * @return LogoList<Fact> type
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(new int[]{Syntax.WildcardType()}, Syntax.ListType());
    }
}
