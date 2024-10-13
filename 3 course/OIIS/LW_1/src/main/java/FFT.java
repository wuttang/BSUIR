import org.apache.commons.math3.complex.Complex;

public class FFT {
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        if (n == 1) return new Complex[] { x[0] };

        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        // Рекурсивно просчитываем все четные позиции
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] evenFFT = fft(even);

        // Рекурсивно просчитываем все нечетные позиции
        Complex[] odd  = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] oddFFT = fft(odd);

        // Собираем все вместе
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = evenFFT[k].add (wk.multiply(oddFFT[k]));
            y[k + n/2] = evenFFT[k].add(wk.multiply(oddFFT[k]).negate());
        }
        return y;
    }
}
