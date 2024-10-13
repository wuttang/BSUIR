package org.example;

import java.util.Scanner;

public class CaesarCipher {

    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int shiftedIndex = (index + shift) % ALPHABET_SIZE;
                result.append(ALPHABET.charAt(shiftedIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, ALPHABET_SIZE - (shift % ALPHABET_SIZE));
    }

    public static void bruteForceAttack(String encryptedText) {
        for (int shift = 1; shift < ALPHABET_SIZE; shift++) {
            String decryptedText = decrypt(encryptedText, shift);
            System.out.println("Ключ: " + shift + " -> " + decryptedText);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Зашифровать текст");
            System.out.println("2. Расшифровать текст");
            System.out.println("3. Атака полным перебором ключа");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите текст для шифрования:");
                    String textToEncrypt = scanner.nextLine();

                    System.out.println("Введите сдвиг:");
                    int shiftEncrypt = scanner.nextInt();

                    String encryptedText = encrypt(textToEncrypt, shiftEncrypt);
                    System.out.println("Зашифрованный текст: " + encryptedText);
                    break;

                case 2:
                    System.out.println("Введите текст для расшифрования:");
                    String textToDecrypt = scanner.nextLine();

                    System.out.println("Введите сдвиг:");
                    int shiftDecrypt = scanner.nextInt();

                    String decryptedText = decrypt(textToDecrypt, shiftDecrypt);
                    System.out.println("Расшифрованный текст: " + decryptedText);
                    break;

                case 3:
                    System.out.println("Введите зашифрованный текст:");
                    String encryptedTextForBruteForce = scanner.nextLine();

                    System.out.println("Запуск атаки полным перебором:");
                    bruteForceAttack(encryptedTextForBruteForce);
                    break;

                case 0:
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }
    }
}