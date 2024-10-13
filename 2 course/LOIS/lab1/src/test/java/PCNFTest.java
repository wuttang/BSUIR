import org.junit.Assert;
import org.junit.Test;
import org.oskirko.Models.Exceptions.IncorrectTypeOfLogicalFormula;
import org.oskirko.Models.LogicalExpression;

public class PCNFTest {
    @Test
    public void PCNFTest1() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((((A\\/B)\\/C)/\\((A\\/(!B))\\/C))/\\(((!A)\\/B)\\/(!C)))");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest2() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(((!A)/\\B)/\\(!C))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest3() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression(
                "(((((A\\/B)\\/C)/\\((A\\/(!B))\\/C))/\\(((!A)\\/B)\\/(!C)))/\\((A\\/B)\\/(!C)))");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest4() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest5() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A/\\B)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest6() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A/\\B)/\\C)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest7() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(!A)");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest8() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B)");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest9() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A/\\B)/\\C)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest10() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A/\\B)\\/C)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest11() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A/\\B)/\\(C\\/D))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest12() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(((A/\\B)/\\C)/\\D)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest13() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A)\\/B)");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest14() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A)/\\(B\\/C))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest15() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A/\\(!B))/\\C)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest16() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(((A\\/B)/\\C)/\\(!D))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest17() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A)/\\((!B)/\\C))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest18() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A\\/B)/\\((!A)/\\C))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest19() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A\\/B)/\\((A/\\C)/\\D))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest20() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A/\\B)/\\((C\\/D)/\\E))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest21() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression(
                "    ((((((((((((((((((((((((((((A\\/B)\\/C)\\/(!D))\\/E)\\/F)\\/G)\\/H)\\/I)\\/(!J))\\/K)\\/L)\\/M)\\/(!N))\\/O)\\/P)\\/(!Q))\\/R)\\/S)\\/U)\\/V)\\/W)\\/X)\\/(!Y))\\/Z)" +
                        "    /\\((((((((((((((((((((((((A\\/B)\\/C)\\/D)\\/(!E))\\/F)\\/(!G))\\/H)\\/I)\\/(!J))\\/K)\\/L)\\/(!M))\\/N)\\/O)\\/P)\\/Q)\\/R)\\/S)\\/(!U))\\/V)\\/W)\\/X)\\/Y)\\/(!Z)))" +
                        "    /\\((((((((((((((((((((((((A\\/B)\\/(!C))\\/D)\\/E)\\/F)\\/G)\\/H)\\/I)\\/J)\\/K)\\/L)\\/M)\\/(!N))\\/O)\\/P)\\/(!Q))\\/R)\\/S)\\/(!U))\\/V)\\/W)\\/(!X))\\/Y)\\/Z))" +
                        "    /\\((((((((((((((((((((((((A\\/B)\\/C)\\/D)\\/E)\\/(!F))\\/(!G))\\/H)\\/I)\\/(!J))\\/K)\\/L)\\/M)\\/N)\\/(!O))\\/P)\\/Q)\\/R)\\/S)\\/U)\\/V)\\/(!W))\\/X)\\/Y)\\/Z))" +
                        "    /\\(((((((((((((((((((((((((!A)\\/(!B))\\/C)\\/D)\\/E)\\/(!F))\\/G)\\/H)\\/I)\\/J)\\/(!K))\\/L)\\/M)\\/N)\\/O)\\/P)\\/Q)\\/(!R))\\/S)\\/(!U))\\/V)\\/(!W))\\/X)\\/Y)\\/Z))"
        );
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest22() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression(
                "(((((A\\/B)\\/C)/\\((A\\/B)\\/C))/\\((A\\/B)\\/C))/\\((A\\/B)\\/C))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest23() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression(
                "((A12345678987654321\\/B1234564321231212)/\\((!A12345678987654321)\\/B1234564321231212))");
        Assert.assertTrue(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest24() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression(
                "(((!(A\\/B))\\/C)/\\((A\\/B)\\/C))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest25() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((((A\\/B)\\/C)/\\((D\\/(!E))\\/F))/\\(((!A)\\/B)\\/(!C)))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest26() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((((A\\/B)\\/A)/\\((A\\/(!B))\\/A))/\\(((!A)\\/B)\\/(!A)))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest27() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((((A\\/B)\\/A)/\\((A\\/(!B))\\/C))/\\(((!A)\\/B)\\/(!C)))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest28() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(T\\/A)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest29() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/.L)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest30() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(T\\/.L)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest31() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(T\\/.L)");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest32() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((((T\\/B)\\/C)/\\((T\\/(!B))\\/C))/\\(((!T)\\/B)\\/(!C)))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }

    @Test
    public void PCNFTest33() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((((.L\\/B)\\/C)/\\((.L\\/(!B))\\/C))/\\(((!.L)\\/B)\\/(!C)))");
        Assert.assertFalse(logicalExpression.isPCNF());
    }
}
