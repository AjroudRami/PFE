package businessData;

import kobdig.logic.TruthDegree;
import org.nlogo.core.ExtensionObject;
import primitive.ExtensionClassManager;

public class Operator extends kobdig.logic.Operator implements ExtensionObject {

    public static final kobdig.logic.Operator NOT = new kobdig.logic.Operator("~", 1) {
        @Override
        public TruthDegree truth(TruthDegree... t) {
            return super.truth(t).negated();
        }
    };

    /**
     * The pre-defined binary conjunction operator, "&and;".
     */
    public static final kobdig.logic.Operator AND = new kobdig.logic.Operator("&", 2) {
        @Override
        public TruthDegree truth(TruthDegree... t) {
            return TruthDegree.tnorm(t[1], super.truth(t));
        }
    };

    /**
     * The pre-defined binary disjunction operator, "&or;".
     */
    public static final kobdig.logic.Operator OR = new kobdig.logic.Operator("|", 2) {
        @Override
        public TruthDegree truth(TruthDegree... t) {
            return TruthDegree.snorm(t[1], super.truth(t));
        }
    };

    /**
     * The pre-defined binary exclusive or operator, "&oplus;".
     */
    public static final kobdig.logic.Operator XOR = new kobdig.logic.Operator("+", 2) {
        @Override
        public TruthDegree truth(TruthDegree... t) {
            return TruthDegree.snorm(
                    TruthDegree.tnorm(t[1].negated(), super.truth(t)),
                    TruthDegree.tnorm(t[1], super.truth(t).negated())
            );
        }
    };


    /**
     * Creates an operator with the given symbol and arity.
     *
     * @param symbol
     * @param arity
     */
    public Operator(String symbol, int arity) {
        super(symbol, arity);
    }

    @Override
    public String dump(boolean readable, boolean exporting, boolean reference) {
        return super.toString();
    }

    @Override
    public String getExtensionName() {
        return ExtensionClassManager.EXTENSION_NAME;
    }

    @Override
    public String getNLTypeName() {
        return this.getClass().getName();
    }

    @Override
    public boolean recursivelyEqual(Object obj) {

        return super.equals(obj);
    }

}
