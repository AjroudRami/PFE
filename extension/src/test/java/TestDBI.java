import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void test() {
        System.out.println("TESTING");
        Assert.assertTrue(true);
    }
}
