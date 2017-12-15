package fr.polytech.unice.dbi.primitive.agent.storage;

import fr.polytech.unice.dbi.businessData.DBIAgent;
import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import java.util.HashMap;
import java.util.Map;

public class DBIStorage implements Command {

    private Map<String, DBIAgent> agents;

    @Override
    public void perform(Argument[] args, Context context) {
        this.agents = new HashMap<>();
    }

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax();
    }

    /**
     * This method update an agent in the storage
     *
     * @param name
     * @param agent
     */
    public void updateAgent(String name, DBIAgent agent) {
        this.agents.replace(name, agent);
    }

    /**
     * Register a new agent, registration only works if the given name is not used.
     *
     * @param name
     * @param agent
     */
    public void addAgent(String name, DBIAgent agent) {
        this.agents.putIfAbsent(name, agent);
    }

    /**
     * Return a DBIAgent if the name correspond to an element of the storage, null otherwise
     *
     * @param name
     * @return
     */
    public DBIAgent getAgent(String name) {
        if (this.agents.containsKey(name)) {
            return this.agents.get(name);
        } else {
            return null;
        }
    }

    /**
     * Remove an agent from the storage
     *
     * @param name
     */
    public void remove(String name) {
        this.agents.remove(name);
    }
}
