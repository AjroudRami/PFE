package primitive;

import behavior.Behavior;
import behavior.BehaviorBuilder;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExportDBIBehavior implements Command {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.AgentType(), Syntax.StringType()});
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent agent = args[0].getAgent();
        String filename = args[1].getString();
        Behavior behavior = (Behavior) agent.getVariable(0);
        try {
            BehaviorBuilder.saveBehavior(behavior, filename);
        } catch (IOException e) {
            throw new ExtensionException(e.getMessage());
        }
    }
}
