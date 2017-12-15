package fr.polytech.unice.dbi.primitive;

import fr.polytech.unice.dbi.TestDBI;
import fr.polytech.unice.dbi.businessData.DBIAgent;
import fr.polytech.unice.dbi.primitive.agent.behavior.GetBeliefs;
import fr.polytech.unice.dbi.primitive.agent.behavior.GetGoalFactSet;
import fr.polytech.unice.dbi.primitive.agent.behavior.GetGoalLogoList;
import fr.polytech.unice.dbi.util.TestArgument;
import kobdig.agent.Fact;
import kobdig.agent.FactSet;
import kobdig.logic.PropositionalAtom;
import kobdig.logic.PropositionalFormula;
import kobdig.logic.TruthDegree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nlogo.api.Argument;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.LogoList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class TestDBIAgentPrimitives extends TestDBI {

    private DBIAgent agent = new DBIAgent();

    @Before
    public void initDBIAgent() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("farmer.apl");
        File file = new File(url.getPath());
        agent = new DBIAgent(new FileInputStream(file));
    }

    @Test
    public void testGetBeliefs() throws ExtensionException {
        GetBeliefs getBeliefs = new GetBeliefs();
        TestArgument agentArg = new TestArgument(agent);
        TestArgument factArg = new TestArgument(new Fact(new PropositionalFormula((new PropositionalAtom("improve")))));
        Object res = getBeliefs.report(new Argument[]{agentArg, factArg}, null);

        double truthDegree = (double) res;

        Assert.assertEquals(0.0, truthDegree, 0);
    }


    @Test
    public void testGetDesire() throws ExtensionException {
        GetBeliefs getBeliefs = new GetBeliefs();
        TestArgument agentArg = new TestArgument(agent);
        TestArgument factArg = new TestArgument(new Fact(new PropositionalFormula((new PropositionalAtom("enlarge")))));
        Object res = getBeliefs.report(new Argument[]{agentArg, factArg}, null);

        double truthDegree = (double) res;

        Assert.assertEquals(0.0, truthDegree, 0);

    }

    @Test
    public void testGetGoalsAsFactSet() throws ExtensionException {
        GetGoalFactSet getGoalFactSet = new GetGoalFactSet();
        TestArgument agentArg = new TestArgument(agent);

        Object res = getGoalFactSet.report(new Argument[]{agentArg}, null);

        FactSet factSet = (FactSet) res;

        //The size should be 0 because the agent knows nothing, he might be John Snow
        Assert.assertEquals(0, factSet.size());

        //Update the agent belief so it takes a decision
        agent.updateBeliefs(new Fact(new PropositionalFormula(new PropositionalAtom("viable"))), new TruthDegree(1));
        agent.updateBeliefs(new Fact(new PropositionalFormula(new PropositionalAtom("fertile"))), new TruthDegree(1));

        FactSet newSet = (FactSet) getGoalFactSet.report(new Argument[]{agentArg}, null);

        Assert.assertEquals(1, newSet.size());
    }

    @Test
    public void testGetGoalsAsLogoList() throws ExtensionException {
        GetGoalLogoList getGoalLogoList = new GetGoalLogoList();
        TestArgument agentArg = new TestArgument(agent);

        //TODO Fix this test
        //LogoList res = (LogoList) getGoalLogoList.report(new Argument[]{agentArg}, null);

        //The size should be 0 because the agent knows nothing, he might be John Snow
        //Assert.assertEquals(0, res.size());

        //Update the agent belief so it takes a decision
        agent.updateBeliefs(new Fact(new PropositionalFormula(new PropositionalAtom("viable"))), new TruthDegree(1));
        agent.updateBeliefs(new Fact(new PropositionalFormula(new PropositionalAtom("fertile"))), new TruthDegree(1));

        LogoList newSet = (LogoList) getGoalLogoList.report(new Argument[]{agentArg}, null);

        Assert.assertEquals(1, newSet.size());
    }


}
