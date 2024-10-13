package org.example.Models;

public class BinaryFixed {
    private boolean sign = false;
    private byte[] integerPart = new byte[8];
    private byte[] fractionalPart = new byte[23];

    public BinaryFixed() {}

    public BinaryFixed(boolean sign, byte[] integerPart, byte[] fractionalPart) {
        this.sign = sign;
        this.integerPart = integerPart;
        this.fractionalPart = fractionalPart;
    }

    public BinaryFixed(float value) {
        sign = value < 0;
        value = Math.abs(value);

        int decimalIntegerPart = (int)value;
        float decimalFractionalPart = value - (int)value;

        for (int i = integerPart.length - 1; i > 0; i--) {
            integerPart[i] = (byte)(decimalIntegerPart % 2 == 1 ? 1 : 0);
            decimalIntegerPart /= 2;
        }

        for (int i = 0; i < fractionalPart.length; i++) {
            decimalFractionalPart *= 2;

            if (decimalFractionalPart >= 1) {
                fractionalPart[i] = 1;
                decimalFractionalPart--;
            }
        }
    }

    public float toFloat() {
        float answer = 0;

        for (int i = 0; i < integerPart.length; i++) {
            answer += (float) (integerPart[i] * Math.pow(2, integerPart.length - i - 1));
        }

        for (int i = 0; i < fractionalPart.length; i++) {
            answer += (float) (fractionalPart[i] * Math.pow(2, -i - 1));
        }

        return sign ? -answer : answer;
    }

    public static BinaryFixed sum(BinaryFixed first, BinaryFixed second) {
        boolean resultSign = false;

        byte[] firstNumber = new byte[32];
        System.arraycopy(first.integerPart, 0, firstNumber, 1, first.integerPart.length);
        System.arraycopy(first.fractionalPart, 0, firstNumber, first.integerPart.length + 1, first.fractionalPart.length);
        firstNumber[0] = (byte) (first.sign ? 1 : 0);
        BinaryInteger firstBinaryNumber = new BinaryInteger(firstNumber);

        byte[] secondNumber = new byte[32];
        System.arraycopy(second.integerPart, 0, secondNumber, 1, second.integerPart.length);
        System.arraycopy(second.fractionalPart, 0, secondNumber, second.integerPart.length + 1, second.fractionalPart.length);
        secondNumber[0] = (byte) (second.sign ? 1 : 0);
        BinaryInteger secondBinaryNumber = new BinaryInteger(secondNumber);

        BinaryInteger result = new BinaryInteger();

        if (firstBinaryNumber.isPositive() && secondBinaryNumber.isPositive()) {
            result = BinaryInteger.sum(firstBinaryNumber, secondBinaryNumber);
            resultSign = false;
        } else if (!firstBinaryNumber.isPositive() && !secondBinaryNumber.isPositive()){
            result = BinaryInteger.sum(firstBinaryNumber.toOpposite(), secondBinaryNumber.toOpposite());
            resultSign = true;
        } else if (firstBinaryNumber.isPositive() && !secondBinaryNumber.isPositive()) {
            result = BinaryInteger.difference(firstBinaryNumber, secondBinaryNumber.toOpposite());
            resultSign = !result.isPositive();
            result = result.toOpposite();
        } else if (!firstBinaryNumber.isPositive() && secondBinaryNumber.isPositive()) {
            result = BinaryInteger.difference(secondBinaryNumber, firstBinaryNumber.toOpposite());
            resultSign = !result.isPositive();
            result = result.toOpposite();
        }

        byte[] resultBits = result.getBits();
        byte[] integerPart = new byte[8];
        byte[] fractionalPart = new byte[23];

        System.arraycopy(resultBits, 1, integerPart, 0, 8);
        System.arraycopy(resultBits, 9, fractionalPart, 0, 23);

        return new BinaryFixed(resultSign, integerPart, fractionalPart);
    }

    public static BinaryFixed difference(BinaryFixed first, BinaryFixed second) {
        byte[] firstNumber = new byte[32];
        System.arraycopy(first.integerPart, 0, firstNumber, 1, first.integerPart.length);
        System.arraycopy(first.fractionalPart, 0, firstNumber, first.integerPart.length + 1, first.fractionalPart.length);
        firstNumber[0] = (byte) (first.sign ? 1 : 0);

        byte[] secondNumber = new byte[32];
        System.arraycopy(second.integerPart, 0, secondNumber, 1, second.integerPart.length);
        System.arraycopy(second.fractionalPart, 0, secondNumber, second.integerPart.length + 1, second.fractionalPart.length);
        secondNumber[0] = (byte) (second.sign ? 1 : 0);

        BinaryInteger result = BinaryInteger.difference(new BinaryInteger(firstNumber), new BinaryInteger(secondNumber));
        byte[] resultBits = result.getBits();
        byte[] integerPart = new byte[8];
        byte[] fractionalPart = new byte[23];

        System.arraycopy(resultBits, 1, integerPart, 0, 8);
        System.arraycopy(resultBits, 9, fractionalPart, 0, 23);

        return new BinaryFixed(resultBits[0] == 1, integerPart, fractionalPart);
    }

    public BinaryFixed leftShift(int n) {
        BinaryFixed result = new BinaryFixed();
        byte[] bits = new byte[31];

        System.arraycopy(integerPart, 0, bits, 0, integerPart.length);
        System.arraycopy(fractionalPart, 0, bits, integerPart.length, fractionalPart.length);

        for (int i = 0; i < bits.length - n; i++) {
            bits[i] = bits[i + n];
        }
        bits[fractionalPart.length - 1] = 0;

        System.arraycopy(bits, 0, result.integerPart, 0, 8);
        System.arraycopy(bits, 8, result.fractionalPart, 0, 23);

        return result;
    }

    public BinaryFixed rightShift(int n){
        BinaryFixed result = new BinaryFixed();
        byte[] bits = new byte[31];

        System.arraycopy(integerPart, 0, bits, 0, integerPart.length);
        System.arraycopy(fractionalPart, 0, bits, integerPart.length, fractionalPart.length);

        for (int i = bits.length - 1; i >= n; i--) {
            bits[i] = bits[i - n];
        }
        bits[0] = 0;

        System.arraycopy(bits, 0, result.integerPart, 0, 8);
        System.arraycopy(bits, 8, result.fractionalPart, 0, 23);

        return result;
    }

    public byte[] getFractionalPart() {
        return fractionalPart;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Представление числа в виде числа с фиксированной запятой: ");
        string.append(sign ? 1 : 0);
        string.append(" ");

        for (int i = 0; i < integerPart.length; i++) {
            string.append(integerPart[i]);
        }
        string.append(" ");

        for (int i = 0; i < fractionalPart.length; i++) {
            string.append(fractionalPart[i]);
        }
        string.append(" ");

        return new String(string);
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}
