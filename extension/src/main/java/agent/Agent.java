package agent;

import org.nlogo.agent.AgentSet;
import org.nlogo.agent.Turtle2D;
import org.nlogo.agent.World;
import org.nlogo.api.AgentException;

public class Agent extends Turtle2D {

    public Agent(World world, AgentSet breed, Double xcor, Double ycor) {
        super(world, breed, xcor, ycor);
    }

    public void move() {
        switch ((int) getPatchHere().pcolor()) {
            case 0:
                moveLeft();
                break;
            case 0xFF:
                moveRight();
                break;
            default:
                break;
        }
    }


    public void moveLeft() {
        getPatchHere().pcolor(0xFF);
        this.heading(heading() + 45);
        try {
            this.jump(1.0);
        } catch (AgentException e) {
            e.printStackTrace();
        }
    }

    public void moveRight() {
        getPatchHere().pcolor(0);
        this.heading(heading() - 45);
        try {
            this.jump(1.0);
        } catch (AgentException e) {
            e.printStackTrace();
        }
    }


}