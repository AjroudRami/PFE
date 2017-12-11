package primitive;

import kobdig.agent.Fact;
import kobdig.logic.TruthDegree;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * This class implements a primitive (command) used to update the agent beliefs.
 */
public class UpdateBelief implements Command {

    /**
     * This method is called whenever the command is used.  check {@link #getSyntax()} getSyntax()} for more information
     * about its Netlogo Syntax.
     * @param args
     * @param context
     * @throws ExtensionException
     */
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent agent = context.getAgent();
        kobdig.agent.Agent dbi = (kobdig.agent.Agent) agent.getVariable(Primitives.dbi_index);
        Fact fact = (Fact) args[0].get();
        TruthDegree truthDegree = new TruthDegree(args[1].getDoubleValue());
        dbi.updateBeliefs(fact, truthDegree);
    }

    /**
     * This command takes exactly two arguments:
     * A Reference to a {@link Fact}
     * A Number that is the truthdegree of this fact
     * @return {@link Syntax}
     */
    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.ReferenceType(), Syntax.NumberType()});
    }
}
