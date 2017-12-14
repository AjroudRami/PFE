package util;

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
    public Agent getAgent() throws LogoException {
        return null;
    }

    @Override
    public Boolean getBoolean() throws LogoException {
        return null;
    }

    @Override
    public boolean getBooleanValue() throws LogoException {
        return false;
    }

    @Override
    public int getIntValue() throws LogoException {
        return 0;
    }

    @Override
    public double getDoubleValue() throws LogoException {
        return 0;
    }

    @Override
    public LogoList getList() throws LogoException {
        return null;
    }

    @Override
    public Patch getPatch() throws LogoException {
        return null;
    }

    @Override
    public String getString() throws LogoException {
        return null;
    }

    @Override
    public Turtle getTurtle() throws LogoException {
        return null;
    }

    @Override
    public Link getLink() throws LogoException {
        return null;
    }

    @Override
    public AnonymousReporter getReporter() throws LogoException {
        return null;
    }

    @Override
    public AnonymousCommand getCommand() throws LogoException {
        return null;
    }

    @Override
    public List<Token> getCode() throws LogoException {
        return null;
    }

    @Override
    public Token getSymbol() throws LogoException {
        return null;
    }
}
