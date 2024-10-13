package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static String generatePassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(random.nextInt(10));
        }

        return password.toString();
    }

    public static void checkDistribution(String password) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : password.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        System.out.println("Частотное распределение символов:");
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("Символ: " + entry.getKey() + ", Частота: " + entry.getValue());
        }
    }

    public static double calculateAverageBruteForceTime(int length, double attackSpeed) {
        long possibleCombinations = (long) Math.pow(10, length);
        double averageTimeSeconds = (possibleCombinations / 2.0) / attackSpeed;
        return averageTimeSeconds;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину строки: ");
        int length = scanner.nextInt();

        String password = generatePassword(length);
        System.out.println("Сгенерированная строка: " + password);

        checkDistribution(password);

        System.out.print("Введите скорость подбора пароля (паролей в секунду): ");
        double attackSpeed = scanner.nextDouble();

        double averageTime = calculateAverageBruteForceTime(length, attackSpeed);
        System.out.println("Среднее время подбора пароля: " + averageTime + " секунд.");
    }
}