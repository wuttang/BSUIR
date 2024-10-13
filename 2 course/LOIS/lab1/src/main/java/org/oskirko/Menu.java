//
// Лабораторная работа №1 по дисциплине "Логические основы интеллектуальных систем"
// Вариант 12: Проверить, является ли формула СКНФ
// Выполнили студенты группы 221703 БГУИР:
// - Оскирко Дмитрий Анатольевич
// - Воложинец Архип Александрович
//
// Класс, отвечающий за пользовательский интерфейс
// 21.04.2024
//

package org.oskirko;

import org.oskirko.Models.Exceptions.IncorrectTypeOfLogicalFormula;
import org.oskirko.Models.LogicalExpression;
import org.oskirko.Models.TestConstants;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IncorrectTypeOfLogicalFormula {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите сделать?\n1. Проверить логическую формулу на СКНФ.\n2. Пройти тест на знание СКНФ.");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("Введите логическую формулу: ");
            try {
                scanner = new Scanner(System.in);
                LogicalExpression logicalExpression = new LogicalExpression(scanner.nextLine());
                if (logicalExpression.isPCNF()) {
                    System.out.println("✅Данная логическая формула является СКНФ");
                } else {
                    System.out.println("❌Данная логическая формула не является СКНФ");
                }
            } catch (IncorrectTypeOfLogicalFormula e) {
                System.out.println("❌Вы ввели неверный вид логического выражения!");
            }
        } else if (choice == 2) {
            var random = new SecureRandom();
            List<String> allTests = new ArrayList<>(TestConstants.TESTS);
            int correctTests = 0;
            int allTestCount = allTests.size();

            while (!allTests.isEmpty()) {
                String testString = allTests.get(random.nextInt(allTests.size()));
                LogicalExpression test = new LogicalExpression(testString);
                System.out.println("Является ли СКНФ следующая логическая формула(1 - является, 2 - не является):\n" + testString);
                int answer = scanner.nextInt();
                if (test.isPCNF()) {
                    if (answer == 1) {
                        System.out.println("✅Верно!");
                        correctTests++;
                    } else if (answer == 2) {
                        System.out.println("❌Неверно!");
                    }
                } else {
                    if (answer == 2) {
                        System.out.println("✅Верно!");
                        correctTests++;
                    } else if (answer == 1) {
                        System.out.println("❌Неверно!");
                    }
                }
                allTests.remove(testString);
            }

            System.out.println("Ваш результат: " + TestConstants.MAX_GRADE / allTestCount * correctTests);
        }
    }
}
