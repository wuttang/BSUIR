//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Перечисление всех логических операций
// 21.04.2024
//
// Источники:
// - Логические основы интеллектуальных систем. Практикум
//

package org.oskirko.Models;

/**
 * Перечисление всех логических операций
 */
public enum Operations {
    NEGATION("!"),
    CONJUNCTION("/\\"),
    DISJUNCTION("\\/"),
    IMPLICATION("->"),
    EQUIVALENCE("~");

    private final String stringRepresentation;

    Operations(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public String getStringRepresentation() {
        return this.stringRepresentation;
    }
}
