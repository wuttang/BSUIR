package org.example.Models;

import java.util.Arrays;

public class BinaryInteger {
    private final int LENGTH = 32;
    private byte[] bits = new byte[LENGTH];

    public BinaryInteger() {}

    public BinaryInteger(int decimalInteger) {
        int bitsLength = bits.length;

        if (decimalInteger < 0) {
            bits[0] = 1;
            decimalInteger = -decimalInteger;
        }

        for (int i = bitsLength - 1; i > 0; i--) {
            bits[i] = (byte)((decimalInteger % 2) == 1 ? 1 : 0);
            decimalInteger /= 2;
        }
    }

    public BinaryInteger(byte[] bits) {
        for (int i = 0; i < bits.length; i++) {
            this.bits[i + this.bits.length - bits.length] = bits[i];
        }
    }

    public BinaryInteger toOpposite() {
        BinaryInteger result = new BinaryInteger();
        result.bits = this.bits.clone();

        result.bits[0] = (byte)(result.bits[0] == 1 ? 0 : 1);

        return result;
    }

    /**
     * Метод для преобразования числа из двоичной системы в десятиричную
     * @return число в десятиричной системе
     */
    public int toDecimal() {
        int result = 0;
        for (int i = 1; i < bits.length; i++) {
            result += (int) (bits[i] * Math.pow(2, bits.length - i - 1));
        }

        return bits[0] == 1 ? -result : result;
    }

    /**
     * Метод для определения знака числа
     * @return true - в случае положительного числа, false - в случае отрицательного числа
     */
    public boolean isPositive() {
        return bits[0] != 1;
    }

    /**
     * Метод для представления числа в виде прямого кода
     * @return представление числа в виде прямого кода
     */
    public byte[] toDirectCode() {
        return bits.clone();
    }

    /**
     * Метод для представления числа в виде обратного кода
     * @return представление числа в виде обратного кода
     */
    public byte[] toReverseCode() {
        if (this.isPositive()) return this.toDirectCode();
        else {
            byte[] result = this.toDirectCode();

            for (int i = 1; i < bits.length; i++) {
                result[i] = (byte)(result[i] == 1 ? 0 : 1);
            }

            return result;
        }
    }

    /**
     * Метод для представления числа в виде дополнительного кода
     * @return представление числа в виде дополнительного кода
     */
    public byte[] toAdditionalCode() {
        if (this.isPositive()) return this.toDirectCode();
        else {
            byte[] result = this.toReverseCode();

            for (int i = result.length - 1; i > 1; i--) {
                result[i] = (byte)(result[i] == 1 ? 0 : 1);

                if (result[i] == 1) {
                    break;
                }
            }

            return result;
        }
    }

    /**
     * Метод для представления числа в дробного числа по стандарту IEEE 754-2008
     * @return представление числа в виде дробного в стандарте IEEE 754-2008
     */
    public BinaryFloat toBinaryFloat() {
        return new BinaryFloat(this.toDecimal());
    }

    /**
     * Метод для нахождения суммы двух двоичных чисел
     * @param first первое двоичное число
     * @param second второе двоичное число
     * @return сумма двух двочиных чисел
     */
    public static BinaryInteger sum(BinaryInteger first, BinaryInteger second) {
        BinaryInteger firstCopy = new BinaryInteger(first.bits);
        BinaryInteger secondCopy = new BinaryInteger(second.bits);

        firstCopy.bits = firstCopy.toAdditionalCode();
        secondCopy.bits = secondCopy.toAdditionalCode();
        BinaryInteger result = new BinaryInteger();
        byte carry = 0;

        for (int i = first.bits.length - 1; i >= 0; i--) {
            result.bits[i] = (byte)(firstCopy.bits[i] ^ secondCopy.bits[i] ^ carry);

            carry = (byte)((firstCopy.bits[i] == 1 && secondCopy.bits[i] == 1)
                    || (firstCopy.bits[i] == 1 && secondCopy.bits[i] == 0 && carry == 1)
                    || (firstCopy.bits[i] == 0 && secondCopy.bits[i] == 1 && carry == 1) ? 1 : 0);
        }

        if (!result.isPositive()) {
            result.bits = result.toAdditionalCode();
        }

        return result;
    }

    /**
     * Метод для нахождения разности двух двоичных чисел
     * @param first первое двоичное число
     * @param second второе двоичное число
     * @return разность двух двочиных чисел
     */
    public static BinaryInteger difference(BinaryInteger first, BinaryInteger second) {
        return sum(first, second.toOpposite());
    }

    /**
     * Метод для нахождения произведения двух двоичных чисел
     * @param first первое двоичное число
     * @param second второе двоичное число
     * @return произведение двух двочиных чисел
     */
    public static BinaryInteger multiply(BinaryInteger first, BinaryInteger second) {
        BinaryInteger result = new BinaryInteger();

        BinaryInteger firstCopy = new BinaryInteger(first.bits);
        BinaryInteger secondCopy = new BinaryInteger(second.bits);
        int shiftCount = 0;

        for (int i = firstCopy.bits.length - 1; i > 0; i--) {
            if (firstCopy.bits[i] == 1) {
                result = BinaryInteger.sum(result, secondCopy.leftShift(shiftCount));
            }

            shiftCount++;
        }

        result.bits[0] = (byte)((first.bits[0] == 1 && second.bits[0] == 1 ||
                first.bits[0] == 0 && second.bits[0] == 0) ? 0 : 1);

        return result;
    }

    public static BinaryFixed division(BinaryInteger first, BinaryInteger second) {
        BinaryInteger firstCopy = new BinaryInteger(first.bits);
        BinaryInteger secondCopy = new BinaryInteger(second.bits);

        byte[] integerPart = new byte[32];
        byte[] fractionalPart = new byte[32];
        BinaryInteger currentPart = new BinaryInteger();

        for (int i = 0; i < firstCopy.bits.length; i++) {
            if (currentPart.isLessThan(secondCopy)) {
                integerPart[i] = 0;
                currentPart = currentPart.leftShift(1);
                currentPart.bits[currentPart.bits.length - 1] = firstCopy.bits[i];
            }

            if (currentPart.isMoreThan(secondCopy) || currentPart.equals(secondCopy)) {
                integerPart[i] = 1;
                currentPart = BinaryInteger.difference(currentPart, secondCopy);
            }
        }

        for (int i = 0; i < 5; i++) {
            if (currentPart.isLessThan(secondCopy)) {
                fractionalPart[i] = 0;
                currentPart = currentPart.leftShift(1);
            }

            if (currentPart.isMoreThan(secondCopy) || currentPart.equals(secondCopy)) {
                fractionalPart[i] = 1;
                currentPart = BinaryInteger.difference(currentPart, secondCopy);
            }
        }

        boolean sign = first.bits[0] == 1 && second.bits[0] == 0 || first.bits[0] == 0 && second.bits[0] == 1;

        return new BinaryFixed(sign, integerPart, fractionalPart);
    }

    /**
     * Метод для сравнение двух двоичных чисел(больше ли данное число)
     * @param binaryInteger число, с которым будем сравнивать
     * @return истинность высказывания
     */
    public boolean isMoreThan(BinaryInteger binaryInteger) {
        if (this.bits[0] > binaryInteger.bits[0]) return false;
        else if (this.bits[0] < binaryInteger.bits[0]) return true;
        else {
            for (int i = 1; i < 32; i++) {
                if (this.bits[i] > binaryInteger.bits[i]) return true;
                else if (this.bits[i] < binaryInteger.bits[i]) return false;
            }
        }

        return false;
    }

    /**
     * Метод для сравнение двух двоичных чисел(меньше ли данное число)
     * @param binaryInteger число, с которым будем сравнивать
     * @return истинность высказывания
     */
    public boolean isLessThan(BinaryInteger binaryInteger) {
        if (this.bits[0] < binaryInteger.bits[0]) return false;
        else if (this.bits[0] > binaryInteger.bits[0]) return true;
        else {
            for (int i = 1; i < 32; i++) {
                if (this.bits[i] < binaryInteger.bits[i]) return true;
                else if (this.bits[i] > binaryInteger.bits[i]) return false;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryInteger that = (BinaryInteger) o;
        return Arrays.equals(bits, that.bits);
    }

    /**
     * Метод для побитового сдвига влево
     * @param n количество битов для сдвига
     * @return двоичное число с n сдвинутыми битами
     */
    public BinaryInteger leftShift(int n) {
        BinaryInteger result = new BinaryInteger();

        for (int i = 1; i < bits.length - n; i++) {
            result.bits[i] = bits[i + n];
        }
        result.bits[bits.length - 1] = 0;

        return result;
    }

    public BinaryInteger rightShift(int n){
        BinaryInteger result = new BinaryInteger();

        for (int i = bits.length - 1; i >= n; i--) {
            result.bits[i] = bits[i - n];
        }
        result.bits[1] = 0;

        return result;
    }

    public byte[] getBits() {
        return bits;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Представление числа в виде целого числа: ");

        for (int i = 0; i < bits.length; i++) {
            string.append(bits[i]);
            if((i + 1) % 8 == 0) string.append(" ");
        }

        return new String(string);
    }
}
