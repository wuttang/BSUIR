//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Класс для представления логического выражения
// 21.04.2024
//
// Источники:
// - Логические основы интеллектуальных систем. Практикум
//

package org.oskirko.Models;

import org.oskirko.Models.Exceptions.IncorrectConstituentNegation;
import org.oskirko.Models.Exceptions.IncorrectConstituentSign;
import org.oskirko.Models.Exceptions.IncorrectTypeOfLogicalFormula;
import org.oskirko.Models.Exceptions.RepeatingConstituentElements;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для представления логического выражения
 */
public class LogicalExpression {
    private String expression;

    /**
     * Конструктор для класса
     * @param expression строковое представление логического выражения
     * @throws IncorrectTypeOfLogicalFormula исключение при неверном виде логического выражения
     */
    public LogicalExpression(String expression) throws IncorrectTypeOfLogicalFormula {
        this.expression = expression;
        if (!this.isCorrect() || this.expression.isBlank()) {
            throw new IncorrectTypeOfLogicalFormula();
        }
    }

    /**
     * Метод для проверки на правильность логического выражения
     * @return истинность высказывания "Верно ли заданное логическое выражение"
     */
    private boolean isCorrect() {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < this.expression.length(); i++) {
            if (this.expression.charAt(i) == '(') {
                stack.push(Character.toString(this.expression.charAt(i)));
            }

            else if (this.expression.charAt(i) == '.') {
                if (i + 1 < this.expression.length() && this.expression.charAt(i + 1) == 'L') {
                    stack.push(".L");
                    i = i + 1;
                } else {
                    return false;
                }
            }

            else if (Arrays.asList(Symbols.LATIN_CAPITAL_LETTERS).contains(this.expression.charAt(i))) {
                String operand = this.getOperand(this.expression, i);
                if (!isCorrectOperand(operand)) {
                    return false;
                }
                stack.push(operand);
                i += operand.length() - 1;
            }

            else if (this.expression.charAt(i) == '-' || this.expression.charAt(i) == '/' ||
                    this.expression.charAt(i) == '\\' || this.expression.charAt(i) == '~' ||
                    this.expression.charAt(i) == '!') {
                Operations operations = getOperation(this.expression, i);
                if (operations == null) {
                    return false;
                }
                stack.push(operations.getStringRepresentation());
                i += operations.getStringRepresentation().length() - 1;
            }

            else if (this.expression.charAt(i) == ')') {
                int operationCount = 0;
                StringBuilder bracketExpression = new StringBuilder(")");
                String currentString = null;
                do {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    currentString = stack.pop();
                    if (currentString.equals("/\\") || currentString.equals("\\/") || currentString.equals("!")
                            || currentString.equals("->") || currentString.equals("~")) {
                        operationCount++;
                    }
                    bracketExpression.insert(0, currentString);
                } while (!currentString.equals("("));

                if (operationCount != 1) {
                    return false;
                }

                if (!this.isBinaryOperation(new String(bracketExpression)) && !this.isUnaryOperation(new String(bracketExpression))) {
                    return false;
                } else {
                    stack.push("A");
                }
            }

            else if (this.expression.charAt(i) != ' ') {
                return false;
            }
        }


        return stack.size() == 1;
    }

    /**
     * Метод для проверки операнда на правильность
     * @param operand операнд
     * @return истинность высказывания "Верен ли операнд"
     */
    private boolean isCorrectOperand(String operand) {
        if (!Arrays.asList(Symbols.LATIN_CAPITAL_LETTERS).contains(operand.charAt(0))) {
            return false;
        }
        for (int i = 1; i < operand.length(); i++) {
            if (operand.charAt(i) == Symbols.NULL_DIGIT && i == 1) {
                return false;
            } else if (operand.charAt(i) != Symbols.NULL_DIGIT && !Arrays.asList(Symbols.NOT_NULL_DIGIT).contains(operand.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Метод для получения операнда по его начальному индексу из логического выражения
     * @param logicalExpression логическое выражение
     * @param index начальный индекс операнда
     * @return операнд
     */
    private String getOperand(String logicalExpression, int index) {
        StringBuilder operand = new StringBuilder(Character.toString(logicalExpression.charAt(index)));
        index++;
        while(index < logicalExpression.length() && (Arrays.asList(Symbols.NOT_NULL_DIGIT).contains(logicalExpression.charAt(index)) ||
                logicalExpression.charAt(index) == Symbols.NULL_DIGIT ||
                Arrays.asList(Symbols.LATIN_CAPITAL_LETTERS).contains(logicalExpression.charAt(index)))) {
            operand.append(logicalExpression.charAt(index++));
        }

        return new String(operand);
    }

    /**
     * Метод для получения операции по ее начальному индексу
     * @param logicalExpression логическое выражение
     * @param index начальный индекс операции
     * @return операция
     */
    private Operations getOperation(String logicalExpression, int index) {
        if (logicalExpression.charAt(index) == '!') {
            return Operations.NEGATION;
        }

        else if (logicalExpression.charAt(index) == '~') {
            return Operations.EQUIVALENCE;
        }

        else if (index + 1 < logicalExpression.length()) {
            if (logicalExpression.charAt(index) == '/' && logicalExpression.charAt(index + 1) == '\\') {
                return Operations.CONJUNCTION;
            }

            else if (logicalExpression.charAt(index) == '\\' && logicalExpression.charAt(index + 1) == '/') {
                return Operations.DISJUNCTION;
            }

            else if (logicalExpression.charAt(index) == '-' && logicalExpression.charAt(index + 1) == '>') {
                return Operations.IMPLICATION;
            }
        }

        return null;
    }

    /**
     * Метод для проверки логического выражения на соответствие виду СКНФ
     * @return истинность высказывания "Является ли логическое выражение СКНФ"
     */
    public boolean isPCNF() {
        List<Constituent> constituents = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < this.expression.length(); i++) {
            if (this.expression.charAt(i) == '.') {
                return false;
            }

            else if (this.expression.charAt(i) == 'T') {
                String operand = this.getOperand(this.expression, i);
                if (operand.equals("T")) {
                    return false;
                }
            }

            else if (this.expression.charAt(i) == '/' && this.expression.charAt(i + 1) == '\\') {
                try {
                    constituents.add(new Constituent(this.expression.substring(startIndex, i), 0));
                } catch (IncorrectConstituentNegation | IncorrectConstituentSign | RepeatingConstituentElements exception) {
                    return false;
                }
                startIndex = i + 2;
            }
        }

        try {
            constituents.add(new Constituent(this.expression.substring(startIndex), 0));
        } catch (IncorrectConstituentNegation | IncorrectConstituentSign | RepeatingConstituentElements exception) {
            return false;
        }
        for (int i = 0; i < constituents.size() - 1; i++) {
            for (int j = i + 1; j < constituents.size(); j++) {
                if (constituents.get(i).equals(constituents.get(j)) || !constituents.get(i).getAllVariables().equals(constituents.get(j).getAllVariables())) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Метод для проверки выражения на бинарность
     * @param operation логическое выражение
     * @return истинность высказывания "Является ли логическое выражение бинарным"
     */
    private boolean isBinaryOperation(String operation) {
        List<String> operationList = new ArrayList<>();
        for (int i = 0; i < operation.length(); i++) {
            if (operation.charAt(i) == 'T') {
                operationList.add("T");
            }

            else if (operation.charAt(i) == '.') {
                if (i + 1 < operation.length() && operation.charAt(i + 1) == 'L') {
                    operationList.add(".L");
                    i = i + 1;
                } else {
                    return false;
                }
            }

            else if (Arrays.asList(Symbols.LATIN_CAPITAL_LETTERS).contains(operation.charAt(i))) {
                String operand = this.getOperand(operation, i);
                if (!isCorrectOperand(operand)) {
                    return false;
                }
                operationList.add(operand);
                i += operand.length() - 1;
            }

            else if (operation.charAt(i) == '-' || operation.charAt(i) == '/' ||
                    operation.charAt(i) == '\\' || operation.charAt(i) == '~') {
                Operations operations = getOperation(operation, i);
                operationList.add(operations.getStringRepresentation());
                i += operations.getStringRepresentation().length() - 1;
            }

            else if (operation.charAt(i) == '(' || operation.charAt(i) == ')') {
                operationList.add(Character.toString(operation.charAt(i)));
            }
        }

        if (operationList.size() == 5) {
            if (operationList.get(0).equals("(") && operationList.get(4).equals(")")
                    && (this.isCorrectOperand(operationList.get(3)) || operationList.get(3).equals("T") ||
                    operationList.get(3).equals(".L")) && (this.isCorrectOperand(operationList.get(1)) ||
                    operationList.get(1).equals("T") || operationList.get(1).equals(".L")) &&
                    (operationList.get(2).equals("/\\") || operationList.get(2).equals("\\/") ||
                    operationList.get(2).equals("->") || operationList.get(2).equals("~"))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Метод для проверки выражения на унарность
     * @param operation логическое выражение
     * @return истинность высказывания "Является ли логическое выражение унарным"
     */
    private boolean isUnaryOperation(String operation) {
        List<String> operationList = new ArrayList<>();
        for (int i = 0; i < operation.length(); i++) {
            if (operation.charAt(i) == 'T') {
                operationList.add("T");
            }

            else if (operation.charAt(i) == '.') {
                if (i + 1 < operation.length() && operation.charAt(i + 1) == 'L') {
                    operationList.add(".L");
                    i = i + 1;
                } else {
                    return false;
                }
            }

            else if (Arrays.asList(Symbols.LATIN_CAPITAL_LETTERS).contains(operation.charAt(i))) {
                String operand = this.getOperand(operation, i);
                if (!isCorrectOperand(operand)) {
                    return false;
                }
                operationList.add(operand);
                i += operand.length() - 1;
            }

            else if (operation.charAt(i) == '!') {
                Operations operations = getOperation(operation, i);
                operationList.add(operations.getStringRepresentation());
                i += operations.getStringRepresentation().length() - 1;
            }

            else if (operation.charAt(i) == '(' || operation.charAt(i) == ')') {
                operationList.add(Character.toString(operation.charAt(i)));
            }
        }

        if (operationList.size() == 4) {
            if (operationList.get(0).equals("(") && operationList.get(3).equals(")")
                    && (this.isCorrectOperand(operationList.get(2)) || operationList.get(2).equals("T") ||
                    operationList.get(2).equals(".L")) && (operationList.get(1).equals("!"))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

//Регулярное выражение для Binary и Unary операций
// "\\([A-Z](?:[1-9][0-9]*)?((/\\\\)|(\\\\/)|(->)|(~))[A-Z](?:[1-9][0-9]*)?\\)"
// "\\(![A-Z](?:[1-9][0-9]*)?\\)"
