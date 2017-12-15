package fr.polytech.unice.dbi.primitive.agent.storage;

import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class DeleteBehavior implements Command {

    private DBIStorage storage;

    public DeleteBehavior(DBIStorage storage) {
        this.storage = storage;
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        String dbiName = args[0].getString();
        this.storage.remove(dbiName);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.StringType()});
    }
}
