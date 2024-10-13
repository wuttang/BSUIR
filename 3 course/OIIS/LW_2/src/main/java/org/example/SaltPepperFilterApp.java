package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;
import java.util.Random;

public class SaltPepperFilterApp extends JFrame {
    private BufferedImage originalImage;
    private BufferedImage noisyImage;
    private BufferedImage filteredImage;
    private JLabel imageLabel;

    public SaltPepperFilterApp() {
        setTitle("Filters App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            File imageFile = new File("/Users/wuttang/BSUIR/3 course/OIIS/LW_2/src/main/java/org/example/1.png");
            System.out.println("Абсолютный путь к файлу: " + imageFile.getAbsolutePath());

            if (!imageFile.exists()) {
                throw new IOException("Файл изображения не найден по пути: " + imageFile.getAbsolutePath());
            }

            originalImage = ImageIO.read(imageFile);

            if (originalImage == null) {
                throw new IOException("Не удалось прочитать изображение. Возможно, файл поврежден.");
            }
        originalImage = resizeImage(originalImage, 400, 300);

            noisyImage = addSaltPepperNoise(originalImage, 0.05);
            filteredImage = applyMedianFilter(noisyImage, 3);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка загрузки изображения: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        imageLabel = new JLabel(new ImageIcon(originalImage));
        add(imageLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addNoiseButton = new JButton("Добавить фильтр соль-перец");
        JButton applyFilterButton = new JButton("Добавить медианный фильтр");
        JButton resetButton = new JButton("Сбросить");

        addNoiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(new ImageIcon(noisyImage));
            }
        });

        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(new ImageIcon(filteredImage));
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(new ImageIcon(originalImage));
            }
        });

        buttonPanel.add(addNoiseButton);
        buttonPanel.add(applyFilterButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public static BufferedImage addSaltPepperNoise(BufferedImage img, double noiseLevel) {
        BufferedImage noisyImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Random random = new Random();

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);

                double rand = random.nextDouble();
                if (rand < noiseLevel / 2) {
                    noisyImage.setRGB(x, y, Color.WHITE.getRGB());
                } else if (rand < noiseLevel) {
                    noisyImage.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    noisyImage.setRGB(x, y, rgb);
                }
            }
        }
        return noisyImage;
    }

    public static BufferedImage applyMedianFilter(BufferedImage img, int windowSize) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage output = new BufferedImage(width, height, img.getType());

        int offset = windowSize / 2;

        for (int x = offset; x < width - offset; x++) {
            for (int y = offset; y < height - offset; y++) {
                int[] red = new int[windowSize * windowSize];
                int[] green = new int[windowSize * windowSize];
                int[] blue = new int[windowSize * windowSize];

                int index = 0;
                for (int i = -offset; i <= offset; i++) {
                    for (int j = -offset; j <= offset; j++) {
                        int rgb = img.getRGB(x + i, y + j);
                        red[index] = (rgb >> 16) & 0xFF;
                        green[index] = (rgb >> 8) & 0xFF;
                        blue[index] = rgb & 0xFF;
                        index++;
                    }
                }

                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);

                int medianRed = red[red.length / 2];
                int medianGreen = green[green.length / 2];
                int medianBlue = blue[blue.length / 2];
                int medianRGB = (medianRed << 16) | (medianGreen << 8) | medianBlue;

                output.setRGB(x, y, medianRGB);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SaltPepperFilterApp().setVisible(true);
            }
        });
    }
}