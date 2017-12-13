package primitive;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;
import primitive.agent.CreateEmptyDBI;
import primitive.agent.behavior.*;
import primitive.agent.io.ExportDBIBehavior;
import primitive.agent.io.ImportDBIBehavior;
import primitive.agent.storage.AddDBIBehavior;
import primitive.agent.storage.DBIStorage;
import primitive.agent.storage.DeleteBehavior;
import primitive.agent.storage.UpdateDBIBehavior;
import primitive.logic.atom.CreateAtom;
import primitive.logic.fact.FromFormula;
import primitive.logic.fact.GetFormula;
import primitive.logic.fact.Negate;
import primitive.logic.factSet.Membership;
import primitive.logic.formula.CreateFormula;
import primitive.logic.formula.FromAtom;
import primitive.logic.formula.FromFact;
import primitive.logic.operator.CreateOperator;
import primitive.logic.propositionalAtom.CreatePropositionalAtom;
import primitive.logic.propositionalFormula.CreatePropositionalFormula;
import primitive.logic.truthdegree.DoubleValue;
import util.RemoveDuplicates;

public class ExtensionClassManager extends DefaultClassManager {

    public static final String EXTENSION_NAME = "dbi_agents";

    private static final boolean LOAD_STORAGE = true;

    @Override
    public void load(PrimitiveManager primManager) {

        if (LOAD_STORAGE) {
            loadStorage(primManager);
        }

        primManager.addPrimitive(Primitives.EXPORT_DBI_BEHAVIOR, new ExportDBIBehavior());
        primManager.addPrimitive(Primitives.IMPORT_DBI_BEHAVIOR, new ImportDBIBehavior());
        primManager.addPrimitive(Primitives.CREATE_ATOM, new CreateAtom());
        primManager.addPrimitive(Primitives.UPDATE_AGENT_BELIEF, new UpdateBelief());
        primManager.addPrimitive(Primitives.GET_AGENT_GOALS, new GetGoalLogoList());
        primManager.addPrimitive(Primitives.GET_FACT_DESIRE_FACTOR, new GetDesires());
        primManager.addPrimitive(Primitives.GET_FACT_BELIEF_FACTOR, new GetBeliefs());
        primManager.addPrimitive(Primitives.CREATE_PROPOSITION, new CreatePropositionalFormula());
        primManager.addPrimitive(Primitives.CREATE_FACT_FROM_FORMULA, new FromFormula());
        primManager.addPrimitive(Primitives.CREATE_FORMULA, new CreateFormula());
        primManager.addPrimitive(Primitives.CREATE_FORMULA_FROM_ATOM, new FromAtom());
        primManager.addPrimitive(Primitives.CREATE_OPERATOR, new CreateOperator());
        primManager.addPrimitive(Primitives.CREATE_PROPOSITIONAL_FORMULA, new CreatePropositionalFormula());
        primManager.addPrimitive(Primitives.CREATE_PROPOSITIONAL_ATOM, new CreatePropositionalAtom());
        primManager.addPrimitive(Primitives.CREATE_EMPTY_DBI, new CreateEmptyDBI());
        primManager.addPrimitive(Primitives.REMOVE_DUPLICATES, new RemoveDuplicates());
        primManager.addPrimitive(Primitives.NEGATE_FACT, new Negate());
        primManager.addPrimitive(Primitives.GET_FORMULA_FROM_FACT, new FromFact());
        primManager.addPrimitive(Primitives.PROPOSITIONAL_FORMULA_FROM_ATOM, new primitive.logic.propositionalFormula.FromAtom());
        primManager.addPrimitive(Primitives.GET_GOALS_FACTSET, new GetGoalFactSet());
        primManager.addPrimitive(Primitives.FACTSET_MEMBERSHIP, new Membership());
        primManager.addPrimitive(Primitives.FACT_GET_FORMULA, new GetFormula());
        primManager.addPrimitive(Primitives.TRUTHDEGREE_DOUBLE_VALUE, new DoubleValue());

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
        primManager.addPrimitive(Primitives.ADD_BEHAVIOR, new AddDBIBehavior(storage));
        primManager.addPrimitive(Primitives.UPDATE_BEHAVIOR, new UpdateDBIBehavior(storage));
    }
}
