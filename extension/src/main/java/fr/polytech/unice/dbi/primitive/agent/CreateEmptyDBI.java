package fr.polytech.unice.dbi.primitive.agent;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a fr.polytech.unice.dbi.primitive used to create DBI Agents.
 * The agent created by this fr.polytech.unice.dbi.primitive will be empty of any proposition and/or logic.
 * The user might initialize its belief and knowledge in order to use it
 */
public class CreateEmptyDBI implements Reporter {
    @Override
    public Object report(Argument[] args, Context context) {
        DBIAgent agent = new DBIAgent();
        return agent;
    }

    /**
     * This fr.polytech.unice.dbi.primitive takes no arguments and returns a reference to the DBI Behavior.
     *
     * @return
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(Syntax.WildcardType());
    }
}
