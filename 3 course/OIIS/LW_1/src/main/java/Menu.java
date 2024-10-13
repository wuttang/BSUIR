import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;


public class Menu {
    private static void plotGraph(double[] x, double[] y, String title, String xLabel, String yLabel) {
        XYSeries series = new XYSeries(title);
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        int N = 512; // Количество точек
        Complex[] input = new Complex[N];

        double frequency = 100; // Частота сигнала
        double samplingRate = 800; // Частота дискретизации
        double[] time = new double[N];
        double[] signalSin = new double[N];
        double[] signalCos = new double[N];

        // Генерация синусоидального сигнала
        for (int i = 0; i < N; i++) {
            double t = i / samplingRate;
            double value = Math.sin(2 * Math.PI * t * frequency);
            input[i] = new Complex(value, 0);
            time[i] = t;
            signalSin[i] = value;
        }

        // Выполнение БПФ для синусоиды
        Complex[] outputSin = FFT.fft(input);

        // Генерация косинусоидального сигнала
        for (int i = 0; i < N; i++) {
            double t = i / samplingRate;
            double value = Math.cos(2 * Math.PI * t * frequency);
            input[i] = new Complex(value, 0);
            signalCos[i] = value;
        }

        // Выполнение БПФ для косинусоиды
        Complex[] outputCos = FFT.fft(input);

        // Подсчет амплитуд
        double[] amplitudeSin = new double[N / 2];
        double[] amplitudeCos = new double[N / 2];
        double[] frequencies = new double[N / 2];

        for (int i = 0; i < N / 2; i++) {
            frequencies[i] = i * samplingRate / N;
            amplitudeSin[i] = outputSin[i].abs() * 2.0 / N;
            amplitudeCos[i] = outputCos[i].abs() * 2.0 / N;
        }

        plotGraph(frequencies, amplitudeSin, "Амплитудный спектр (SIN)", "Частота (Гц)", "Амплитуда");
        plotGraph(frequencies, amplitudeCos, "Амплитудный спектр (COS)", "Частота (Гц)", "Амплитуда");
    }
}