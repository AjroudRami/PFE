package fr.polytech.unice.dbi.primitive;

public class Primitives {

    public static final String SEP = "-";
    /**
     * Storage functionality
     */
    public static final String DBIStorage = "DBIStorage";
    public static final String INIT_DBI_STORAGE = DBIStorage + SEP + "init";
    public static final String ADD_BEHAVIOR = DBIStorage + SEP + "add";
    public static final String DELETE_BEHAVIOR = DBIStorage + SEP + "remove";
    public static final String UPDATE_BEHAVIOR = DBIStorage + SEP + "update";
    public static final String GET_DBI_BEHAVIOR = DBIStorage + SEP + "get";


    /**
     * DBIAgent primitives
     */
    //Class name
    public static final String DBIAgent = "DBIAgent";
    //Constructors
    public static final String CREATE_EMPTY_DBI = DBIAgent;
    public static final String IMPORT_DBI_BEHAVIOR = DBIAgent + SEP + "import";
    //methods
    public static final String UPDATE_AGENT_BELIEF = DBIAgent + SEP + "updateBelief";
    public static final String EXPORT_DBI_BEHAVIOR = DBIAgent + SEP + "export";
    public static final String GET_AGENT_GOALS = DBIAgent + SEP + "goalsAsLogoList";
    public static final String GET_FACT_DESIRE_FACTOR = DBIAgent + SEP + "desires";
    public static final String GET_FACT_BELIEF_FACTOR = DBIAgent + SEP + "believes";
    public static final String GET_GOALS_FACTSET = DBIAgent + SEP + "goalsAsFactset";


    /**
     * Atom primitives
     */
    //Name
    public static final String Atom = "Atom";
    public static final String CREATE_ATOM = Atom;
    public static final String GET_ATOM_NAME = Atom + SEP + "getName";


    /**
     * Formula primitives
     */
    //Name
    public static final String Formula = "Formula";
    //Constructors
    public static final String CREATE_FORMULA = Formula;
    public static final String CREATE_FORMULA_FROM_ATOM = Formula + SEP + "fromAtom";
    public static final String GET_FORMULA_FROM_FACT = Formula + SEP + "fromFact";
    public static final String GET_FORMULA_ATOMS = Formula + SEP + "getAtoms";
    public static final String FORMULA_IS_ATOMIC = Formula + SEP + "isAtomic";


    /**
     * Operator primitives
     */
    //Name
    public static final String Operator = "Operator";
    //Constructor
    public static final String CREATE_OPERATOR = Operator;
    //Methods
    public static final String TO_EXTENSION_OP = Operator + SEP + "toExtensionType";


    /**
     * PropositionalFormula primitives
     */
    //Name
    public static final String PropositionalFormula = "PropositionalFormula";
    public static final String CREATE_PROPOSITIONAL_FORMULA = PropositionalFormula;
    public static final String PROPOSITIONAL_FORMULA_FROM_ATOM = PropositionalFormula + SEP + "fromAtom";


    /**
     * PropositionalAtom primitives
     */
    public static final String CREATE_PROPOSITIONAL_ATOM = "PropositionalAtom";


    /**
     * Fact primitives
     */
    //Name
    public static final String Fact = "Fact";
    public static final String CREATE_FACT_FROM_FORMULA = Fact + SEP + "fromFormula";
    //Methods
    public static final String NEGATE_FACT = Fact + SEP + "negate";
    public static final String FACT_GET_FORMULA = Fact + SEP + "getFormula";


    /**
     * FactSet primitives
     */
    //Name
    public static final String Factset = "Factset";
    //Methods
    public static final String FACTSET_MEMBERSHIP = Factset + SEP + "membership";


    /**
     * TruthDegree primitives
     */
    //Name
    public static final String Truthdegree = "TruthDegree";
    //Methods
    public static final String TRUTHDEGREE_DOUBLE_VALUE = Truthdegree + SEP + "doubleValue";


    /**
     * Utils
     */
    public static final String REMOVE_DUPLICATES = "Utils-removeDuplicates";
}
