package primitive;

import behavior.DBIBehavior;
import behavior.Fact;
import kobdig.logic.TruthDegree;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;

public class UpdateBelief implements Command {
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        DBIBehavior behavior = (DBIBehavior) args[0].get();
        Fact fact = (Fact) args[1].get();
        TruthDegree truthDegree = (TruthDegree) args[2].get();
    }

    @Override
    public Syntax getSyntax() {
        return null;
    }
}
