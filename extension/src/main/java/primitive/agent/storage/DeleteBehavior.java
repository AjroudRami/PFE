package primitive.agent.storage;

import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import primitive.agent.storage.DBIStorage;

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
