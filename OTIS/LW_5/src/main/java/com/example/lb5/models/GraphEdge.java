package com.example.lb5.models;

import com.example.lb5.GraphApplication;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphEdge implements Cloneable{
    private GraphNode startNode;
    private GraphNode finishNode;
    private Double weight = null;
    private boolean isOrient = true;
    private Color color;

    public GraphEdge(GraphNode startNode, GraphNode finishNode, Double weight, boolean isOrient, Color color) {
        this.startNode = startNode;
        this.finishNode = finishNode;
        this.weight = weight;
        this.isOrient = isOrient;
        this.color = color;
    }

    public GraphEdge(GraphNode startNode, GraphNode finishNode, boolean isOrient, Color color) {
        this(startNode, finishNode, null, isOrient, color);
    }

    public GraphNode getStartNode() {
        return startNode;
    }

    public GraphNode getFinishNode() {
        return finishNode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setStartNode(GraphNode startNode) {
        this.startNode = startNode;
    }

    public void setFinishNode(GraphNode finishNode) {
        this.finishNode = finishNode;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static GraphEdge getLastAddedEdge() {
        return GraphApplication.currentGraph.getEdgeList().isEmpty() ? null : GraphApplication.currentGraph.getEdgeList().get(GraphApplication.currentGraph.getEdgeList().size() - 1);
    }

    public boolean isOrient() {
        return isOrient;
    }

    public void setOrient(boolean orient) {
        isOrient = orient;
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
        GraphEdge graphEdge = (GraphEdge) o;
        return Objects.equals(startNode, graphEdge.startNode) && Objects.equals(finishNode, graphEdge.finishNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNode, finishNode);
    }
}
