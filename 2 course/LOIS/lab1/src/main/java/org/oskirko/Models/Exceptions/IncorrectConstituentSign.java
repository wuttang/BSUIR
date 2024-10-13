//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Класс для исключения при неверном знаке в конституенте
// 21.04.2024
//
// Источники:
// - Логические основы интеллектуальных систем. Практикум
//

package org.oskirko.Models.Exceptions;

/**
 * Класс для исключения при неверном знаке в конституенте
 */
public class IncorrectConstituentSign extends Exception{
    public IncorrectConstituentSign(String message) {
        super(message);
    }

    public IncorrectConstituentSign() {
        super("Incorrect sign for this type of constituent.");
    }
}
