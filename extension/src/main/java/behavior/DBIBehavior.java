package behavior;

import kobdig.agent.Agent;

import java.io.File;
import java.io.IOException;

/**
 * Represent a behavior based on the Desire, Belief, Intention model
 */
public class DBIBehavior implements Behavior, Importable, Savable {

    private Agent agent;

    public DBIBehavior(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void acknowledge(FactSet set) {

    }

    @Override
    public void update() {

    }

    @Override
    public DecisionSet decide() {
        return null;
    }

    @Override
    public Behavior importFrom(File f) throws IOException {
        return null;
    }

    @Override
    public void exportTo(File f) throws IOException {

    }

    @Override
    public void exportTo(File f, Flag[] flags) throws IOException {

    }
}
