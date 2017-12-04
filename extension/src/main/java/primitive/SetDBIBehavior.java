package primitive;

import kobdig.agent.Agent;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class SetDBIBehavior implements Command {
    
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent agent = (Agent) args[0].get();
        try {
            context.getAgent().setVariable(0, agent);
        } catch (AgentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.ReferenceType()});
    }
}
