package command;

import agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class UpdateTurtles implements Command {


    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent agent = (Agent) args[0].getAgent();
        agent.move();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.AgentType()});

    }
}
