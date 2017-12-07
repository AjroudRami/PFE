package primitive;

import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class DeleteBehavior implements Command {
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        //TODO flaw in conception, dbi behavior is assumed to be index 0 in Agent variables
        Agent agent = args[0].getAgent();
        try {
            agent.setVariable(Primitives.dbi_index, null);
        } catch (AgentException e) {
            e.printStackTrace();
            throw new ExtensionException("Error while removing behavior to the agent caused by:\n"
                    + e.getMessage());
        }
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.AgentType()});
    }
}
