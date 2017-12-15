package fr.polytech.unice.dbi.util;

import org.nlogo.api.*;
import org.nlogo.core.LogoList;
import org.nlogo.core.Token;

import java.util.List;

public class TestArgument implements Argument {

    private Object object;

    public TestArgument(Object obj) {
        this.object = obj;
    }

    @Override
    public Object get() throws LogoException, ExtensionException {
        if (this.object == null) {
            throw new ExtensionException("Trying to get a null Object");
        }
        return this.object;
    }

    @Override
    public AgentSet getAgentSet() throws LogoException, ExtensionException {
        AgentSet agentSet;
        try {
            agentSet = (AgentSet) this.object;
        } catch (ClassCastException e) {
            throw new ExtensionException(e.getMessage());
        }
        return agentSet;
    }

    @Override
    public Agent getAgent() throws LogoException, ExtensionException {
        ObjectCaster<Agent> objectCaster = new ObjectCaster<>();
        return objectCaster.cast(this.object);
    }

    @Override
    public Boolean getBoolean() throws LogoException, ExtensionException {
        ObjectCaster<Boolean> objectCaster = new ObjectCaster<>();
        return objectCaster.cast(this.object);
    }

    @Override
    public boolean getBooleanValue() throws LogoException, ExtensionException {
        return getBoolean();
    }

    @Override
    public int getIntValue() throws LogoException, ExtensionException {
        ObjectCaster<Integer> objectCaster = new ObjectCaster<>();
        return objectCaster.cast(this.object);
    }

    @Override
    public double getDoubleValue() throws LogoException, ExtensionException {
        ObjectCaster<Double> objectCaster = new ObjectCaster<>();
        return objectCaster.cast(object);
    }

    @Override
    public LogoList getList() throws LogoException, ExtensionException {
        ObjectCaster<LogoList> objectCaster = new ObjectCaster<>();
        return objectCaster.cast(object);
    }

    @Override
    public Patch getPatch() throws LogoException {
        //TODO if used
        return null;
    }

    @Override
    public String getString() throws LogoException, ExtensionException {
        ObjectCaster<String> objectCaster = new ObjectCaster<>();
        return objectCaster.cast(object);
    }

    @Override
    public Turtle getTurtle() throws LogoException {
        //TODO if Used
        return null;
    }

    @Override
    public Link getLink() throws LogoException {
        //TODO if used
        return null;
    }

    @Override
    public AnonymousReporter getReporter() throws LogoException {
        //TODO if used
        return null;
    }

    @Override
    public AnonymousCommand getCommand() throws LogoException {
        //TODO if used
        return null;
    }

    @Override
    public List<Token> getCode() throws LogoException {
        //TODO if used
        return null;
    }

    @Override
    public Token getSymbol() throws LogoException {
        //TODO if used
        return null;
    }
}
