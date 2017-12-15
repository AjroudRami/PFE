package fr.polytech.unice.dbi.businessData;

import fr.polytech.unice.dbi.TestDBI;
import kobdig.logic.Atom;
import kobdig.logic.Operator;
import org.junit.Assert;
import org.junit.Test;

public class TestDBIExtensionObject extends TestDBI {


    @Test
    public void testDump() {
        String atomName = "testAtom";
        DBIExtensionObject<Operator> op = new DBIExtensionObject<>(Operator.NOT);
        DBIExtensionObject<Atom> atom = new DBIExtensionObject<>(new Atom(atomName));

        Assert.assertEquals(atomName, atom.dump(false, false, false));
        Assert.assertEquals("~", op.dump(false, false, false));
    }

    @Test
    public void testGetNLTypeName() {
        String atomName = "testAtom";
        DBIExtensionObject<Operator> op = new DBIExtensionObject<>(Operator.NOT);
        DBIExtensionObject<Atom> atom = new DBIExtensionObject<>(new Atom(atomName));

        Assert.assertEquals(Atom.class.getName(), atom.getNLTypeName());
        Assert.assertEquals(Operator.NOT.getClass().getName(), op.getNLTypeName());
    }
}
