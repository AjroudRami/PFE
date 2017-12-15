package fr.polytech.unice.dbi.primitive;

import fr.polytech.unice.dbi.primitive.agent.CreateEmptyDBI;
import fr.polytech.unice.dbi.primitive.agent.behavior.*;
import fr.polytech.unice.dbi.primitive.agent.io.ExportDBIBehavior;
import fr.polytech.unice.dbi.primitive.agent.io.ImportDBIBehavior;
import fr.polytech.unice.dbi.primitive.agent.storage.*;
import fr.polytech.unice.dbi.primitive.logic.atom.CreateAtom;
import fr.polytech.unice.dbi.primitive.logic.atom.GetName;
import fr.polytech.unice.dbi.primitive.logic.fact.FromFormula;
import fr.polytech.unice.dbi.primitive.logic.fact.GetFormula;
import fr.polytech.unice.dbi.primitive.logic.fact.Negate;
import fr.polytech.unice.dbi.primitive.logic.factSet.Membership;
import fr.polytech.unice.dbi.primitive.logic.formula.*;
import fr.polytech.unice.dbi.primitive.logic.operator.CreateOperator;
import fr.polytech.unice.dbi.primitive.logic.operator.ToExtensionOp;
import fr.polytech.unice.dbi.primitive.logic.propositionalAtom.CreatePropositionalAtom;
import fr.polytech.unice.dbi.primitive.logic.propositionalFormula.CreatePropositionalFormula;
import fr.polytech.unice.dbi.primitive.logic.truthdegree.DoubleValue;
import fr.polytech.unice.dbi.util.RemoveDuplicates;
import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

public class ExtensionClassManager extends DefaultClassManager {

    public static final String EXTENSION_NAME = "dbi";

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
        primManager.addPrimitive(Primitives.PROPOSITIONAL_FORMULA_FROM_ATOM,
                new fr.polytech.unice.dbi.primitive.logic.propositionalFormula.FromAtom());
        primManager.addPrimitive(Primitives.GET_GOALS_FACTSET, new GetGoalFactSet());
        primManager.addPrimitive(Primitives.FACTSET_MEMBERSHIP, new Membership());
        primManager.addPrimitive(Primitives.FACT_GET_FORMULA, new GetFormula());
        primManager.addPrimitive(Primitives.TRUTHDEGREE_DOUBLE_VALUE, new DoubleValue());
        primManager.addPrimitive(Primitives.TO_EXTENSION_OP, new ToExtensionOp());
        primManager.addPrimitive(Primitives.GET_ATOM_NAME, new GetName());
        primManager.addPrimitive(Primitives.FORMULA_IS_ATOMIC, new IsAtomic());
        primManager.addPrimitive(Primitives.GET_FORMULA_ATOMS, new GetAtoms());
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
        primManager.addPrimitive(Primitives.GET_DBI_BEHAVIOR, new GetDBIBehavior(storage));
    }

}
