package primitive;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

public class ExtensionClassManager extends DefaultClassManager{
    @Override
    public void load(PrimitiveManager primManager) throws ExtensionException {
        primManager.addPrimitive(Primitives.DELETE_BEHAVIOR, new DeleteBehavior());
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
        primManager.addPrimitive(Primitives.SET_DBI_BEHAVIOR, new SetDBIBehavior());
        primManager.addPrimitive(Primitives.CREATE_EMPTY_DBI, new CreateEmptyDBI());
        primManager.addPrimitive(Primitives.INIT_DBI_STORAGE, new InitDBIStorage());

        primManager.addPrimitive(Primitives.REMOVE_DUPLICATES, new RemoveDuplicates()
        );

    }
}
