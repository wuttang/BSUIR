//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Класс для представления конституенты-n
// 21.04.2024
//
// Источники:
// - Логические основы интеллектуальных систем. Практикум
// - Аппаратное обеспечение интеллектуальных систем. Практикум
//

package org.oskirko.Models;

import org.oskirko.Models.Exceptions.IncorrectConstituentNegation;
import org.oskirko.Models.Exceptions.IncorrectConstituentSign;
import org.oskirko.Models.Exceptions.RepeatingConstituentElements;

import java.util.*;

/**
 * Класс для представления конституенты-n
 */
public class Constituent {
    private String constituentSign;
    private Set<String> positiveVariables;
    private Set<String> negativeVariables;

    /**
     * Конструктор для класса
     * @param expression логическое выражение
     * @param constituentNumber номер конституенты
     * @throws IncorrectConstituentSign исключение при неверном знаке для конституенты
     * @throws IncorrectConstituentNegation исключение при неверном отрицании для конституенты
     * @throws RepeatingConstituentElements исключение при повторении элемента в конституенте
     */
    public Constituent(String expression, int constituentNumber) throws IncorrectConstituentSign, IncorrectConstituentNegation, RepeatingConstituentElements {
        positiveVariables = new HashSet<>();
        negativeVariables = new HashSet<>();

        constituentSign = constituentNumber == 1 ? "/\\" : "\\/";
        boolean isNegative = false;

        for (int i = 0; i < expression.length(); i++) {
            if (Arrays.asList(Symbols.LATIN_CAPITAL_LETTERS).contains(expression.charAt(i))) {
                String operand = this.getOperand(expression, i);
                if (this.getAllVariables().contains(operand)) {
                    throw new RepeatingConstituentElements();
                }
                if (isNegative) {
                    negativeVariables.add(operand);
                } else {
                    positiveVariables.add(operand);
                }
                isNegative = false;
            }

            else if (expression.charAt(i) == '!') {
                isNegative = true;
            }

            else if (expression.charAt(i) == '-'|| expression.charAt(i) == '~' ||
                    (expression.charAt(i) == '/' && expression.charAt(i + 1) == '\\' && constituentSign.equals("\\/")) ||
                    (expression.charAt(i) == '\\' && expression.charAt(i + 1) == '/' && constituentSign.equals("/\\"))) {
                throw new IncorrectConstituentSign();
            }

            else if (expression.charAt(i) == '(' && isNegative) {
                throw new IncorrectConstituentNegation();
            }

        }
    }

    /**
     * Метод для получения операнда логического выражения
     * @param logicalExpression логическое выражение
     * @param index индекс начала операнда в логическом выражении
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

    public Set<String> getAllVariables() {
        Set<String> result = new HashSet<>(positiveVariables);
        result.addAll(negativeVariables);
        return result;
    }

    /**
     * Метод для сравнения двух конституент(равны отрицательные переменные, положительные переменные и число конституенты)
     * @param o конституента для сравнения с текущей
     * @return истинность равенства
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constituent that = (Constituent) o;
        return Objects.equals(constituentSign, that.constituentSign) && Objects.equals(positiveVariables, that.positiveVariables) && Objects.equals(negativeVariables, that.negativeVariables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constituentSign, positiveVariables, negativeVariables);
    }
}
