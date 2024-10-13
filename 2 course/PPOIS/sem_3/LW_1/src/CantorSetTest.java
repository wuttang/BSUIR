import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CantorSetTest {

    @Test
    public void addElementTest() throws Exception {
        CantorSet set = new CantorSet("{a, b, c}");
        CantorSet setTest = new CantorSet("{a, b, c, d}");
        set.addElement("d");
        Assert.assertEquals(setTest, set);
    }

    @Test
    public void removeElementTest() throws Exception {
        CantorSet set = new CantorSet("{a, b, c}");
        CantorSet setTest = new CantorSet("{a, b}");
        set.removeElement("c");
        Assert.assertEquals(setTest, set);
    }

    @Test
    public void emptySetTest() throws Exception {
        CantorSet set = new CantorSet("{}");
        Assert.assertTrue(set.isEmpty());
    }

    @Test
    public void toStringTest() throws Exception {
        CantorSet set = new CantorSet("{anton, b, c, {d, e}, {d, e}, h, {}, {f, {g}}}");
        Assert.assertEquals("[anton, b, c, {d, e}, h, {}, {f, {g}}]", set.toString());
    }

    @Test
    public void isInSetTest() throws Exception {
        CantorSet set = new CantorSet("{5}");
        Assert.assertTrue(set.isInSet("5"));
    }

    @Test
    public void sizeOfTest() throws Exception {
        CantorSet set = new CantorSet("{5, 6, c}");
        Assert.assertEquals(3, set.sizeOfSet());
    }

    @Test
    public void unionOfSetsTest() throws Exception {
        CantorSet set1 = new CantorSet("{array, b, c, {l}, {z, {x}}}");
        CantorSet set2 = new CantorSet("{k}");
        CantorSet set3 = CantorSet.unionOfSets(set1, set2);
        CantorSet setTest = new CantorSet("{array, b, c, k, {l}, {z, {x}}}");
        Assert.assertEquals(setTest, set3);
    }

    @Test
    public void intersectionOfSetsTest() throws Exception {
        CantorSet set1 = new CantorSet("{a, b, c}");
        CantorSet set2 = new CantorSet("{c, b, l, m}");
        CantorSet set3 = CantorSet.intersectionOfSets(set1, set2);
        CantorSet setTest = new CantorSet("{b, c}");
        Assert.assertEquals(setTest, set3);
    }

    @Test
    public void differenceOfSetsTest() throws Exception {
        CantorSet set1 = new CantorSet("{a, b, c, d}");
        CantorSet set2 = new CantorSet("{k, m, f, c, d}");
        CantorSet set3 = CantorSet.differenceOfSets(set1, set2);
        CantorSet setTest = new CantorSet("{a, b}");
        Assert.assertEquals(setTest, set3);
    }

    @Test
    public void booleanSetTest() throws Exception {
        Set<CantorSet> setTest = new HashSet<>();
        setTest.add(new CantorSet("{a}"));
        setTest.add(new CantorSet("{b}"));
        setTest.add(new CantorSet("{a, b}"));
        setTest.add(new CantorSet("{}"));
        CantorSet set = new CantorSet("{a, b}");
        Set<CantorSet> booleanSet = CantorSet.booleanSet(set);
        Assert.assertEquals(setTest, booleanSet);
    }


}
