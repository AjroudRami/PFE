import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nlogo.api.ExtensionException;
import primitive.logic.factSet.Membership;
import util.TestArgument;

@RunWith(org.junit.runners.JUnit4.class)
public class TestDBI {


    public int somme(int a, int b) {
        return a + b;
    }

    @Test
    public void testSomme() {
        int a = 2;
        int b = 3;

        Assert.assertEquals(6, somme(a, b));
    }

    @Test
    public void test() throws ExtensionException {
        System.out.println("TESTING");
        Assert.assertTrue(true);
        Membership mb = new Membership();
        mb.report(new TestArgument[0], null);

    }
}
