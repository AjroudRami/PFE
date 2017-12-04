package primitive;

import kobdig.agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Reporter;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a primitive used to retreive the DBI Agent Goals
 */
public class GetGoals implements Reporter {

    /**
     * This primitive returns a FactSet bundled in an Object
     *
     * @param args
     * @param context
     * @return a FactSet bundled in an object
     * @throws ExtensionException
     */
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException {
        return ((Agent) context.getAgent().getVariable(0)).goals();
    }

    /**
     * It takes no arguments
     *
     * @return
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.reporterSyntax(Syntax.ReferenceType());
    }
}
