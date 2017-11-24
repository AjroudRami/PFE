import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import scala.collection.Seq;

import javax.swing.*;
import java.util.Arrays;

public class FirstCommand implements Command {
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        /**    Turtle turtle = (Turtle) args[0].getAgent();
        //org.nlogo.app.App.app().command("ask patches [set pcolor blue]");
        try {
            turtle.setVariable(org.nlogo.agent.Turtle.VAR_HEADING, turtle.heading() + 45);
        } catch (AgentException e) {
            e.printStackTrace();
         }**/
        //We can open a frame
        JFrame fenetre = new JFrame();


        fenetre.setVisible(true);

        System.out.println("Casting");
        org.nlogo.agent.World world = (org.nlogo.agent.World) context.world();
        System.out.print("Casting success");
        String[] empty = new String[]{};
        Seq<String> seq = scala.collection.JavaConversions.asScalaBuffer(Arrays.asList(new String[]{})).seq();
        //Turtle2D turtle2D = new Turtle2D(world, null, 0.0, 0.0);
        org.nlogo.nvm.Context ctx = (org.nlogo.nvm.Context) context;

        //new _createturtles().perform(ctx);
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{});
    }
}
