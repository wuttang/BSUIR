package org.example;

import org.example.Models.BinaryFloat;
import org.example.Models.BinaryInteger;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choose;

        System.out.println("Операции с целыми числами в десятичном виде");
        System.out.println("1. Преобразование в двоичное число в прямой, обратный и дополнительный код.");
        System.out.println("2. Найти сумму двух чисел.");
        System.out.println("3. Найти разность двух чисел.");
        System.out.println("4. Найти произведение двух чисел.");
        System.out.println("5. Найти частное двух чисел.\n");
        System.out.println("Операции с дробными числами в десятичном виде");
        System.out.println("6. Преобразование в вид IEEE 754-2008.");
        System.out.println("7. Найти сумму двух чисел.");
        System.out.println("8. Выход");


        System.out.println();
        System.out.print("Ваш выбор: ");
        choose = scanner.nextInt();

        switch (choose) {
            case 1: {
                System.out.print("Введите число в десятичном виде: ");
                int number = scanner.nextInt();

                System.out.print("Данное число в прямом коде: ");
                System.out.println(bitsToString(new BinaryInteger(number).toDirectCode()));
                System.out.print("Данное число в обратном коде: ");
                System.out.println(bitsToString(new BinaryInteger(number).toReverseCode()));
                System.out.print("Данное число в дополнительном коде: ");
                System.out.println(bitsToString(new BinaryInteger(number).toAdditionalCode()));
                break;
            }

            case 2: {
                System.out.print("Введите первое число: ");
                int firstNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(firstNumber));
                System.out.print("Введите второе число: ");
                int secondNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(secondNumber));

                System.out.println("Сумма этих чисел: " + BinaryInteger.sum(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)).toDecimal());
                System.out.println(BinaryInteger.sum(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)));

                break;
            }

            case 3: {
                System.out.print("Введите первое число: ");
                int firstNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(firstNumber));
                System.out.print("Введите второе число: ");
                int secondNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(secondNumber));

                System.out.println("Разность этих чисел: " + BinaryInteger.difference(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)).toDecimal());
                System.out.println(BinaryInteger.difference(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)));

                break;
            }

            case 4: {
                System.out.print("Введите первое число: ");
                int firstNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(firstNumber));
                System.out.print("Введите второе число: ");
                int secondNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(secondNumber));

                System.out.println("Произведение этих чисел: " + BinaryInteger.multiply(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)).toDecimal());
                System.out.println(BinaryInteger.multiply(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)));

                break;
            }

            case 5: {
                System.out.print("Введите первое число: ");
                int firstNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(firstNumber));
                System.out.print("Введите второе число: ");
                int secondNumber = scanner.nextInt();
                System.out.println(new BinaryInteger(secondNumber));

                System.out.println("Частное этих чисел: " + BinaryInteger.division(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)).toFloat());
                System.out.println(BinaryInteger.division(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber)));

                break;
            }

            case 6: {
                System.out.print("Введите число в десятичной форме: ");
                float number = scanner.nextFloat();
                System.out.println(new BinaryFloat(number));
            }

            case 7: {
                System.out.print("Введите первое число в десятичной форме: ");
                float firstNumber = scanner.nextFloat();
                System.out.println(new BinaryFloat(firstNumber));
                System.out.print("Введите второе число в десятичной форме: ");
                float secondNUmber = scanner.nextFloat();
                System.out.println(new BinaryFloat(secondNUmber));

                System.out.println("Сумма этих чисел: " + BinaryFloat.sum(new BinaryFloat(firstNumber), new BinaryFloat(secondNUmber)).toFloat());
                System.out.println(BinaryFloat.sum(new BinaryFloat(firstNumber), new BinaryFloat(secondNUmber)));

                break;
            }

            case 8: break;

            default: {
                System.out.println("Неверный ввод.");
                break;
            }
        }
    }

    private static String bitsToString(byte[] bits) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < bits.length; i++) {
            string.append(bits[i]);
            if((i + 1) % 8 == 0) string.append(" ");
        }

        return new String(string);
    }
}
