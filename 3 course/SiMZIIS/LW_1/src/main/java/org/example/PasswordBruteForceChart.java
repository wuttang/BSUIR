package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Scanner;

public class PasswordBruteForceChart extends JFrame {

    public PasswordBruteForceChart(String title) {
        super(title);

        XYSeries series = new XYSeries("Среднее время подбора");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите скорость подбора пароля (паролей в секунду): ");
        double attackSpeed = scanner.nextDouble();

        for (int length = 1; length <= 10; length++) {
            double averageTime = PasswordGenerator.calculateAverageBruteForceTime(length, attackSpeed);
            series.add(length, averageTime);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Зависимость среднего времени подбора пароля от его длины",
                "Длина пароля",
                "Среднее время (секунды)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordBruteForceChart example = new PasswordBruteForceChart("График подбора пароля");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}