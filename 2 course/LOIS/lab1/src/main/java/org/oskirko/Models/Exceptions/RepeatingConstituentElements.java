//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Класс для исключения при пвторении элементов в конститенте
// 21.04.2024
//
// Источники:
// - Логические основы интеллектуальных систем. Практикум
//

package org.oskirko.Models.Exceptions;

/**
 * Класс для исключения при пвторении элементов в конститенте
 */
public class RepeatingConstituentElements extends Exception{
    public RepeatingConstituentElements(String message) {
        super(message);
    }

    public RepeatingConstituentElements() {
        super("Repeating elements in constituent.");
    }
}
