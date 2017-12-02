package primitive;

import behavior.Behavior;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class AddBehavior implements Command {

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent agent = args[0].getAgent();
        Behavior behavior = (Behavior) args[1].get();
        try {
            agent.setVariable(0, behavior);
        } catch (AgentException e) {
            e.printStackTrace();
            throw new ExtensionException("Error while setting behavior to the agent caused by:\n"
                    + e.getMessage());
        }
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.AgentType(), Syntax.ReferenceType()});
    }
}
