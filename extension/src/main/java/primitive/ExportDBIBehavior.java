package primitive;

import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class ExportDBIBehavior implements Command {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.AgentType(), Syntax.StringType()});
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        //TODO
    }
}
