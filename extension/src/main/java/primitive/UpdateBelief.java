package primitive;

import kobdig.agent.Fact;
import kobdig.logic.TruthDegree;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class UpdateBelief implements Command {

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        Agent agent = context.getAgent();
        //TODO flaw in conception, dbi behavior is assumed to be index 0 in Agent variables
        kobdig.agent.Agent dbi = (kobdig.agent.Agent) agent.getVariable(Primitives.dbi_index);
        Fact fact = (Fact) args[0].get();
        TruthDegree truthDegree = new TruthDegree(args[1].getDoubleValue());
        dbi.updateBeliefs(fact, truthDegree);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.ReferenceType(), Syntax.NumberType()});
    }
}
