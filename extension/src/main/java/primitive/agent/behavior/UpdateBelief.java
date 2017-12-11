package primitive.agent.behavior;

import kobdig.agent.Agent;
import kobdig.agent.Fact;
import kobdig.logic.TruthDegree;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import sun.management.resources.agent;

/**
 * This class implements a primitive (command) used to update the agent beliefs.
 */
public class UpdateBelief implements Command {

    /**
     * This method is called whenever the command is used.  check {@link Command#getSyntax()} getSyntax()} for more information
     * about its Netlogo Syntax.
     * @param args
     * @param context
     * @throws ExtensionException
     */
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent dbi = (Agent) args[0].get();
        Fact fact = (Fact) args[1].get();
        TruthDegree truthDegree = new TruthDegree(args[2].getDoubleValue());
        dbi.updateBeliefs(fact, truthDegree);
    }

    /**
     * This command takes exactly three arguments:
     * A Reference to an {@link Agent}
     * A Reference to a {@link Fact}
     * A Number that is the truthdegree of this fact
     * @return {@link Syntax}
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.ReferenceType(), Syntax.ReferenceType(), Syntax.NumberType()});
    }
}
