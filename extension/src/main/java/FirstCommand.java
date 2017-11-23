import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class FirstCommand implements Command {
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Turtle turtle = (Turtle) args[0].getAgent();
        //org.nlogo.app.App.app().command("ask patches [set pcolor blue]");
        try {
            turtle.setVariable(org.nlogo.agent.Turtle.VAR_HEADING, turtle.heading() + 45);
        } catch (AgentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.AgentType(), Syntax.ListType()});
    }
}
