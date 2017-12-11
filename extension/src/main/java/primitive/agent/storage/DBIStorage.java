package primitive.agent.storage;

import kobdig.agent.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DBIStorage implements Command{

    private Map<String, Agent> agents;

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        this.agents = new HashMap<>();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax();
    }

    /**
     * This method update an agent in the storage
     * @param name
     * @param agent
     */
    public void updateAgent(String name, Agent agent) {
        this.agents.replace(name, agent);
    }

    /**
     * Register a new agent, registration only works if the given name is not used.
     * @param name
     * @param agent
     */
    public void setAgent(String name, Agent agent) {
        this.agents.putIfAbsent(name, agent);
    }

    /**
     * Return an Optional object corresponding to the given name.
     * If the agent name is not registred, it returns Optional.empty()
     * @param name
     * @return
     */
    public Optional<Agent> getAgent(String name) {
        Optional<Agent> opt;
        Agent agent = this.agents.get(name);
        if (this.agents.get(name) != null) {
            opt = Optional.of(agent);
        } else {
            opt = Optional.empty();
        }
        return opt;
    }

    /**
     * Remove an agent from the storage
     * @param name
     */
    public void remove(String name) {
        this.agents.remove(name);
    }
}
