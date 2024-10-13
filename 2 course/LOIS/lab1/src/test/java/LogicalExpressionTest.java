import org.junit.Test;
import org.oskirko.Models.Exceptions.IncorrectTypeOfLogicalFormula;
import org.oskirko.Models.LogicalExpression;

public class LogicalExpressionTest {
    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression1() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression2() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("a");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression3() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression4() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A1B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression5() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A=B1)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression6() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(AB&)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression7() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(!)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression8() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A!B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression9() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A/\\(\\/C))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression10() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(1_0_\\(\\/?))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression11() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A/\\B/\\C))))))))))))))))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression12() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A()C");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression13() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->B->C->D");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression14() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->B");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression15() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B->C->(G->H)->I)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression16() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A/\\B/\\C/\\D/\\E/\\F/\\G/\\H/\\I");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression17() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(0)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression18() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(AG)");
    }

//    @Test
//    public void testLogicalExpression19() throws IncorrectTypeOfLogicalFormula {
//        LogicalExpression logicalExpression = new LogicalExpression("(A->B123)");
//    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression20() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(AB/\\&&C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression21() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->(A))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression22() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A)->A)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression23() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A)->(A))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression24() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression25() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression26() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->B))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression27() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression28() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression29() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->(B(C))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression30() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B)->C");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression31() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->B)->C");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression32() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->B)->C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression33() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B->C))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression34() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B->C)))))->)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression35() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(((A->B)->C->D)))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression36() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B)(!C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression37() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->(B->C)->D))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression38() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(*A\\/B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression39() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B*)");
    }

    @Test
    public void testLogicalExpression40() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((T~.L)->(!T))");
    }

    @Test
    public void testLogicalExpression41() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(((T~A)->(!T))/\\B123)");
    }

    @Test
    public void testLogicalExpression42() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(T/\\(!A))");
    }

    @Test
    public void testLogicalExpression43() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((T->.L)/\\H)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression44() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\*/B*)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression45() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B0123)");
    }

    @Test
    public void testLogicalExpression46() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A");
    }

    @Test
    public void testLogicalExpression47() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression48() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A\\/B?C");
    }

    @Test
    public void testLogicalExpression49() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A123\\/B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression50() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression51() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A->B))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression52() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A/B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression53() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B->)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression54() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B->C))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression55() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B)->C))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression56() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->(B->C)->D))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression57() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B)(!C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression58() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression59() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A123\\/B");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression60() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B?C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression61() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B*)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression62() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\*/B*)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression63() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B0123)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression64() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B)(C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression65() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\*/B)(C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression66() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B*)C");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression67() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("A/\\B\\/");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression68() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B->C)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression69() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B)->");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression70() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B->C->)");
    }

    @Test
    public void testLogicalExpression71() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/B)");
    }

    @Test
    public void testLogicalExpression72() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A/\\B)");
    }

    @Test
    public void testLogicalExpression73() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->B)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression74() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A<->B)");
    }

    @Test
    public void testLogicalExpression75() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(!A)");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression76() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A<->(B->C))");
    }

    @Test
    public void testLogicalExpression77() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B)/\\(B->C))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression78() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A\\/B)/\\(B<->C))");
    }

    @Test
    public void testLogicalExpression79() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A\\/(!B))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression80() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A<->(!B))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression81() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(!A<->B)");
    }

    @Test
    public void testLogicalExpression82() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("(A->(B/\\C))");
    }

    @Test
    public void testLogicalExpression83() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A\\/B)->(C/\\D))");
    }

    @Test
    public void testLogicalExpression84() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B)->(B\\/C))");
    }

    @Test
    public void testLogicalExpression85() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A)->(B->C))");
    }

    @Test
    public void testLogicalExpression86() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A->B)/\\(B->C))");
    }

    @Test
    public void testLogicalExpression87() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A)\\/(!B))");
    }

    @Test
    public void testLogicalExpression88() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((A\\/B)->(!B))");
    }

    @Test(expected = IncorrectTypeOfLogicalFormula.class)
    public void testLogicalExpression89() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A->B)/\\(B->C))");
    }

    @Test
    public void testLogicalExpression90() throws IncorrectTypeOfLogicalFormula {
        LogicalExpression logicalExpression = new LogicalExpression("((!A)->(B->C))");
    }
}
