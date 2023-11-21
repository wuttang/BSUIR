package com.example.lb5.models;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphNode {
    private static int currentNumber = 0;
    private double coordX;
    private double coordY;
    private String info;
    private Color color;

    public GraphNode(double coordX, double coordY, String info, Color color) {
        this.coordX = coordX;
        this.coordY = coordY;
        if (info.isEmpty()) this.info = Integer.toString(currentNumber++);
        else this.info = info;
        this.color = color;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public String getInfo() {
        return info;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode node = (GraphNode) o;
        return Objects.equals(info, node.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
