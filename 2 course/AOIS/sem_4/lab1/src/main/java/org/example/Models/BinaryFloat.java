package org.example.Models;

import java.util.Arrays;

public class BinaryFloat {
    private static final int LENGTH = 32;
    private static final int EXP_LENGTH = 8;
    private static final int MANTISSA_LENGTH = 23;

    private byte[] bits = new byte[LENGTH];

    public BinaryFloat() {}

    public BinaryFloat(float value) {
        bits[0] = (byte)(value > 0 ? 0 : 1);
        int exp = 0;
        float mantissa = Math.abs(value);

        while(mantissa >= 2) {
            mantissa /= 2;
            exp++;
        }

        while(mantissa < 1) {
            mantissa *= 2;
            exp--;
        }
        mantissa -= 1;

        exp += 127;

        for (int i = 8; i > 0; i--) {
            bits[i] = (byte)(exp % 2);
            exp /= 2;
        }

        for (int i = 9; i < bits.length; i++) {
            mantissa *= 2;

            if (mantissa >= 1) {
                bits[i] = 1;
                mantissa--;
            }
        }
    }

    public BinaryFloat(byte[] bits) {
        this.bits = bits;
    }

    /**
     * Метод для преобразования числа из двоичного числа в стандарте IEEE 754-2008 к десятичному виду
     * @return десятичный вид числа с плавающей точкой
     */
    public float toFloat() {
        int exp = -127;
        float mantissa = 1;

        for (int i = 0; i < EXP_LENGTH; i++) {
            exp += (int) (bits[8 - i] * Math.pow(2, i));
        }

        for (int i = 0; i < MANTISSA_LENGTH; i++) {
            mantissa += (float) (bits[9 + i] * Math.pow(2, -(i + 1)));
        }

        return (bits[0] == 1 ? -1 : 1) * mantissa * (float)(Math.pow(2, exp));
    }

    public static BinaryFloat sum(BinaryFloat first, BinaryFloat second) {
        BinaryInteger firstExp = new BinaryInteger(Arrays.copyOfRange(first.bits, 1, 1 + EXP_LENGTH));
        BinaryInteger secondExp = new BinaryInteger(Arrays.copyOfRange(second.bits, 1, 1 + EXP_LENGTH));

        byte[] firstMantissaBits = new byte[23];
        byte[] secondMantissaBits = new byte[23];
        System.arraycopy(Arrays.copyOfRange(first.bits, 1 + EXP_LENGTH, LENGTH), 0, firstMantissaBits, 0, MANTISSA_LENGTH);
        System.arraycopy(Arrays.copyOfRange(second.bits, 1 + EXP_LENGTH, LENGTH), 0, secondMantissaBits, 0, MANTISSA_LENGTH);
        BinaryFixed firstMantissa = new BinaryFixed(false, new byte[]{0, 0, 0, 0, 0, 0, 0, 1}, firstMantissaBits);
        BinaryFixed secondMantissa = new BinaryFixed(false, new byte[]{0, 0, 0, 0, 0, 0, 0, 1}, secondMantissaBits);

        if (firstExp.isMoreThan(secondExp)) {
            int difference = BinaryInteger.difference(firstExp, secondExp).toDecimal();
            secondMantissa = secondMantissa.rightShift(difference);
        } else if (firstExp.isLessThan(secondExp)) {
            int difference = BinaryInteger.difference(secondExp, firstExp).toDecimal();
            firstMantissa = firstMantissa.rightShift(difference);
        }

        firstMantissa.setSign(first.bits[0] == 1);
        secondMantissa.setSign(second.bits[0] == 1);
        BinaryFixed resultMantissa = BinaryFixed.sum(firstMantissa, secondMantissa);
        boolean resultSign = resultMantissa.isSign();
        resultMantissa.setSign(false);
        BinaryInteger resultExp = firstExp.isMoreThan(secondExp) ? firstExp : secondExp;

        while(resultMantissa.toFloat() < 1F) {
            resultMantissa = resultMantissa.leftShift(1);
            resultExp = BinaryInteger.difference(resultExp, new BinaryInteger(1));
        }

        while(resultMantissa.toFloat() >= 2F) {
            resultMantissa = resultMantissa.rightShift(1);
            resultExp = BinaryInteger.sum(resultExp, new BinaryInteger(1));
        }

        byte[] result = new byte[32];
        result[0] = (byte) (resultSign ? 1 : 0);
        System.arraycopy(resultExp.getBits(), resultExp.getBits().length - EXP_LENGTH, result, 1, EXP_LENGTH);
        System.arraycopy(resultMantissa.getFractionalPart(), 0, result, EXP_LENGTH + 1, MANTISSA_LENGTH);

        return new BinaryFloat(result);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Представление числа в IEEE 754-2008: ");
        string.append(bits[0]);
        string.append(" ");

        for (int i = 1; i < EXP_LENGTH + 1; i++) {
            string.append(bits[i]);
        }
        string.append(" ");

        for (int i = EXP_LENGTH + 1; i < LENGTH; i++) {
            string.append(bits[i]);
        }
        string.append(" ");

        return new String(string);
    }
}
