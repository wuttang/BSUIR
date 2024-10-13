import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LogicalExpressionTest {
    @Test
    public void truthTableTest() {
        LogicalExpression expression = new LogicalExpression("a→(b&!c)~d|e→a&c→d~a");
        List<Boolean> result = List.of(true, false, false, false, true, false, false, false, true, false, false, false,
                true, false, false, false, true, false, false, false, true, true, false, false, false, true, true, true, true, true,
                false, false);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }

    @Test
    public void buildPDNFTest() {
        LogicalExpression expression = new LogicalExpression("a→(b&!c)~d|e→a&c→d~a");
        Assertions.assertEquals(expression.generateTruthTable().get(expression.getExpression()), expression.generatePDNF().generateTruthTable().get(expression.generatePDNF().getExpression()));
    }

    @Test
    public void buildPCNFTest() {
        LogicalExpression expression = new LogicalExpression("a→(b&!c)~d|e→a&c→d~a");
        Assertions.assertEquals(expression.generateTruthTable().get(expression.getExpression()), expression.generatePCNF().generateTruthTable().get(expression.generatePCNF().getExpression()));
    }

    @Test
    public void getNumericFormPDNFTest() {
        LogicalExpression expression = new LogicalExpression("a→(b&!c)~d|e→a&c→d~a");
        String result = "( 0, 4, 8, 12, 16, 20, 21, 25, 26, 27, 28, 29 ) |";
        Assertions.assertEquals(result, expression.toNumericFormPDNF());
    }

    @Test
    public void getNumericFormPCNFTest() {
        LogicalExpression expression = new LogicalExpression("a→(b&!c)~d|e→a&c→d~a");
        String result = "( 1, 2, 3, 5, 6, 7, 9, 10, 11, 13, 14, 15, 17, 18, 19, 22, 23, 24, 30, 31 ) &";
        Assertions.assertEquals(result, expression.toNumericFormPCNF());
    }

    @Test
    public void getIndexFormTest() {
        LogicalExpression expression = new LogicalExpression("a→(b&!c)~d|e→a&c→d~a");
        Assertions.assertEquals(2290650236L, expression.toIndexForm());
    }

    @Test
    public void expressionWithNoVariablesTest() {
        LogicalExpression expression = new LogicalExpression("true");
        List<Boolean> result = List.of(false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }

    @Test
    public void expressionWithSingleVariableTest() {
        LogicalExpression expression = new LogicalExpression("a");
        List<Boolean> result = List.of(false, true);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }

    @Test
    public void expressionWithNegationTest() {
        LogicalExpression expression = new LogicalExpression("!a");
        List<Boolean> result = List.of(true, false);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }

    @Test
    public void expressionWithConjunctionTest() {
        LogicalExpression expression = new LogicalExpression("a & b");
        List<Boolean> result = List.of(false, false, false, true);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }

    @Test
    public void expressionWithDisjunctionTest() {
        LogicalExpression expression = new LogicalExpression("a | b");
        List<Boolean> result = List.of(false, true, true, true);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }

    @Test
    public void complexExpressionTest() {
        LogicalExpression expression = new LogicalExpression("(a & b) | (!c & d)");
        List<Boolean> result = List.of(false, true, false, false, false, true, false, false, false, true, false, false, true, true, true, true);

        Assertions.assertEquals(result, expression.generateTruthTable().get(expression.getExpression()));
    }
}
