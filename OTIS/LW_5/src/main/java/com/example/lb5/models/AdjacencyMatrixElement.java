package com.example.lb5.models;

public class AdjacencyMatrixElement {
    private String startNodeName;
    private String finishNodeName;
    private Double pathLength;

    public AdjacencyMatrixElement(String startNodeName, String finishNodeName, Double pathLength) {
        this.startNodeName = startNodeName;
        this.finishNodeName = finishNodeName;
        this.pathLength = pathLength;
    }

    public String getStartNodeName() {
        return startNodeName;
    }

    public void setStartNodeName(String startNodeName) {
        this.startNodeName = startNodeName;
    }

    public String getFinishNodeName() {
        return finishNodeName;
    }

    public void setFinishNodeName(String finishNodeName) {
        this.finishNodeName = finishNodeName;
    }

    public Double getPathLength() {
        return pathLength;
    }

    public void setPathLength(double pathLength) {
        this.pathLength = pathLength;
    }
}
