import java.util.*;

public class LogicalExpression {
    private final String expression;
    private final Map<Character, List<Boolean>> variableMap;

    public LogicalExpression(String expression) {
        this.expression = expression;
        this.variableMap = new HashMap<>();
        initializeVariableMap();
    }

    private String toReversePolishNotation() {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char character : this.expression.toCharArray()) {
            if (isOperand(character)) {
                result.append(character);
            }
            else if (isOperator(character)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(character)) {
                    result.append(stack.pop());
                }
                stack.push(character);
            }
            else if (character == '(') {
                stack.push(character);
            }
            else if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    private int getPriority(char operator) {
        return switch (operator) {
            case '!' -> 5;
            case '&' -> 4;
            case '|' -> 3;
            case '→' -> 2;
            case '~' -> 1;
            default -> 0;
        };
    }

    private boolean isOperand(char character) {
        return Character.isLetterOrDigit(character);
    }

    private boolean isOperator(char character) {
        return character == '!' || character == '&' || character == '|' || character == '~' || character == '→';
    }

    private void extractVariables() {
        for (char character : this.expression.toCharArray()) {
            if (isOperand(character)) {
                this.variableMap.put(character, new ArrayList<>());
            }
        }
    }

    private int countVariables() {
        return variableMap.size();
    }

    private void initializeVariableMap() {
        extractVariables();
        for (int i = 0; i < Math.pow(2, countVariables()); i++) {
            String currentBinaryNumber = String.format("%" + countVariables() + "s", Integer.toBinaryString(i)).replace(' ', '0');
            int count = 0;
            for (char variable : variableMap.keySet()) {
                variableMap.get(variable).add(currentBinaryNumber.charAt(count) == '1');
                count++;
            }
        }
    }

    private List<Boolean> evaluateExpression() {
        Stack<Boolean> stack = new Stack<>();
        List<Boolean> result = new ArrayList<>();
        String reversePolishNotation = toReversePolishNotation();

        for (int i = 0; i < Math.pow(2, countVariables()); i++) {
            for (char character : reversePolishNotation.toCharArray()) {
                if (isOperand(character)) {
                    boolean variableValue = variableMap.get(character).get(i);
                    stack.push(variableValue);
                }
                else if (isOperator(character)) {
                    performOperation(character, stack);
                }
            }

            result.add(stack.pop());
        }

        return result;
    }

    private void performOperation(char operation, Stack<Boolean> stack) {
        switch (operation) {
            case '!' -> performNotOperation(stack);
            case '&' -> performAndOperation(stack);
            case '|' -> performOrOperation(stack);
            case '~' -> performEquivalenceOperation(stack);
            case '→' -> performImplicationOperation(stack);
        }
    }

    private void performNotOperation(Stack<Boolean> stack) {
        boolean operand = stack.pop();
        stack.push(!operand);
    }

    private void performAndOperation(Stack<Boolean> stack) {
        Boolean operand1 = stack.pop();
        Boolean operand2 = stack.pop();
        stack.push(operand1 && operand2);
    }

    private void performOrOperation(Stack<Boolean> stack) {
        Boolean operand1 = stack.pop();
        Boolean operand2 = stack.pop();
        stack.push(operand1 || operand2);
    }

    private void performEquivalenceOperation(Stack<Boolean> stack) {
        Boolean operand1 = stack.pop();
        Boolean operand2 = stack.pop();
        stack.push(operand1 == operand2);
    }

    private void performImplicationOperation(Stack<Boolean> stack) {
        Boolean operand1 = stack.pop();
        Boolean operand2 = stack.pop();
        stack.push(!operand2 || operand1);
    }

    public Map<String, List<Boolean>> generateTruthTable() {
        HashMap<String, List<Boolean>> result = new LinkedHashMap<>();
        for (char character : variableMap.keySet()) {
            result.put(Character.toString(character), variableMap.get(character));
        }

        result.put(this.expression, evaluateExpression());

        return result;
    }

    public void printTruthTable() {
        Map<String, List<Boolean>> truthTable = generateTruthTable();
        for (String expression : truthTable.keySet()) {
            System.out.printf("%-15s", expression);
        }
        System.out.println();
        for (int i = 0; i < Math.pow(2, countVariables()); i++) {
            for (String expression : truthTable.keySet()) {
                System.out.printf("%-15s", truthTable.get(expression).get(i) ? 1 : 0);
            }
            System.out.println();
        }
    }

    public LogicalExpression generatePDNF() {
        StringBuilder result = new StringBuilder();
        List<Boolean> expressionResult = generateTruthTable().get(expression);

        for (int i = 0; i < expressionResult.size(); i++) {
            if (expressionResult.get(i)) {
                result.append("( ");
                for (char variable : variableMap.keySet()) {
                    result.append(variableMap.get(variable).get(i) ? variable + " & " : "!" + variable + " & ");
                }
                result.delete(result.length() - 2, result.length());
                result.append(") | ");
            }
        }
        result.delete(result.length() - 2, result.length());

        return new LogicalExpression(result.toString());
    }

    public LogicalExpression generatePCNF() {
        StringBuilder result = new StringBuilder();
        List<Boolean> expressionResult = generateTruthTable().get(expression);

        for (int i = 0; i < expressionResult.size(); i++) {
            if (!expressionResult.get(i)) {
                result.append("( ");
                for (char variable : variableMap.keySet()) {
                    result.append(!variableMap.get(variable).get(i) ? variable + " | " : "!" + variable + " | ");
                }
                result.delete(result.length() - 2, result.length());
                result.append(") & ");
            }
        }
        result.delete(result.length() - 2, result.length());

        return new LogicalExpression(result.toString());
    }

    public String toNumericFormPDNF() {
        StringBuilder result = new StringBuilder();
        List<Boolean> expressionResult = generateTruthTable().get(expression);

        result.append("( ");
        for (int i = 0; i < expressionResult.size(); i++) {
            if (expressionResult.get(i)) result.append(i).append(", ");
        }
        result.delete(result.length() - 2, result.length());

        result.append(" ) |");

        return result.toString();
    }

    public String toNumericFormPCNF() {
        StringBuilder result = new StringBuilder();
        List<Boolean> expressionResult = generateTruthTable().get(expression);

        result.append("( ");
        for (int i = 0; i < expressionResult.size(); i++) {
            if (!expressionResult.get(i)) result.append(i).append(", ");
        }
        result.delete(result.length() - 2, result.length());

        result.append(" ) &");

        return result.toString();
    }

    public long toIndexForm() {
        StringBuilder result = new StringBuilder();
        for (boolean expressionResult : generateTruthTable().get(expression)) {
            result.append(expressionResult ? 1 : 0);
        }

        return Long.parseLong(result.toString(), 2);
    }

    public String getExpression() {
        return expression;
    }
}
