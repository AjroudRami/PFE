package primitive;

import business.TestPrim;
import business.TestPrim2;
import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;
import primitive.agent.CreateEmptyDBI;
import primitive.agent.behavior.GetFactBeliefFactor;
import primitive.agent.behavior.GetFactDesireFactor;
import primitive.agent.behavior.GetGoals;
import primitive.agent.behavior.UpdateBelief;
import primitive.agent.io.ExportDBIBehavior;
import primitive.agent.io.ImportDBIBehavior;
import primitive.agent.storage.AddDBIBehavior;
import primitive.agent.storage.DBIStorage;
import primitive.agent.storage.DeleteBehavior;
import primitive.logic.*;
import util.RemoveDuplicates;

public class ExtensionClassManager extends DefaultClassManager {

    private static final boolean LOAD_STORAGE = true;

    @Override
    public void load(PrimitiveManager primManager) throws ExtensionException {

        if (LOAD_STORAGE) {
            loadStorage(primManager);
        }

        primManager.addPrimitive(Primitives.EXPORT_DBI_BEHAVIOR, new ExportDBIBehavior());
        primManager.addPrimitive(Primitives.IMPORT_DBI_BEHAVIOR, new ImportDBIBehavior());
        primManager.addPrimitive(Primitives.CREATE_ATOM, new CreateAtom());
        primManager.addPrimitive(Primitives.UPDATE_AGENT_BELIEF, new UpdateBelief());
        primManager.addPrimitive(Primitives.GET_AGENT_GOALS, new GetGoals());
        primManager.addPrimitive(Primitives.GET_FACT_DESIRE_FACTOR, new GetFactDesireFactor());
        primManager.addPrimitive(Primitives.GET_FACT_BELIEF_FACTOR, new GetFactBeliefFactor());
        primManager.addPrimitive(Primitives.CREATE_PROPOSITION, new CreatePropositionFormula());
        primManager.addPrimitive(Primitives.CREATE_FACT_FROM_FORMULA, new CreateFactFromFormula());
        primManager.addPrimitive(Primitives.CREATE_FORMULA, new CreateFormula());
        primManager.addPrimitive(Primitives.CREATE_FORMULA_FROM_ATOM, new CreateFormulaFromAtom());
        primManager.addPrimitive(Primitives.CREATE_OPERATOR, new CreateOperator());
        primManager.addPrimitive(Primitives.CREATE_PROPOSITIONAL_FORMULA, new CreatePropositionFormula());
        primManager.addPrimitive(Primitives.CREATE_PROPOSITIONAL_ATOM, new CreatePropositionalAtom());
        primManager.addPrimitive(Primitives.CREATE_EMPTY_DBI, new CreateEmptyDBI());
        primManager.addPrimitive(Primitives.REMOVE_DUPLICATES, new RemoveDuplicates());
        primManager.addPrimitive(Primitives.NEGATE_FACT, new NegateFact());
        primManager.addPrimitive(Primitives.GET_FORMULA_FROM_FACT, new GetFormulaFromFact());
        primManager.addPrimitive("test", new TestPrim());
        primManager.addPrimitive("test2", new TestPrim2());

    }

    /**
     * This method helps binding the object related to the storage management.
     *
     * @param primManager
     * @throws ExtensionException
     */
    private void loadStorage(PrimitiveManager primManager) {
        DBIStorage storage = new DBIStorage();
        primManager.addPrimitive(Primitives.INIT_DBI_STORAGE, storage);
        primManager.addPrimitive(Primitives.DELETE_BEHAVIOR, new DeleteBehavior(storage));
        primManager.addPrimitive(Primitives.SET_DBI_BEHAVIOR, new AddDBIBehavior(storage));
        primManager.addPrimitive(Primitives.ADD_BEHAVIOR, new AddDBIBehavior(storage));
    }
}
