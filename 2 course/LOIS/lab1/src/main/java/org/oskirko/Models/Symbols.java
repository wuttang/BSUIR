//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Перечисление всех символов, которые могут встретиться в логическом выражении
// 21.04.2024
//
// Источники:
// - Логические основы интеллектуальных систем. Практикум
//

package org.oskirko.Models;

import java.util.*;

/**
 * Перечисление всех символов, которые могут встретиться в логическом выражении
 */
public class Symbols {
    public static final String[] CONSTANTS = {"T", ".L"};
    public static final Character[] LATIN_CAPITAL_LETTERS =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                    'Y', 'Z'
            };
    public static final Operations[] OPERATIONS =
            {Operations.CONJUNCTION, Operations.DISJUNCTION, Operations.EQUIVALENCE, Operations.NEGATION, Operations.IMPLICATION};
    public static final Character[] NOT_NULL_DIGIT = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static final Character NULL_DIGIT = '0';
}