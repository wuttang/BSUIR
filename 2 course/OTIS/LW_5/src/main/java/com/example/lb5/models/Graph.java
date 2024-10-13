package com.example.lb5.models;

import java.util.ArrayList;
import java.util.List;

public class Graph implements Cloneable{
    private List<GraphNode> nodeList;
    private List<GraphEdge> edgeList;

    public Graph() {
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    public List<GraphNode> getNeighbors(GraphNode node) {
        List<GraphNode> result = new ArrayList<>();
        for (GraphEdge edge : edgeList) {
            if (edge.getStartNode().getInfo().equals(node.getInfo())) result.add(edge.getFinishNode());
        }

        return result;
    }

    public List<GraphNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<GraphNode> nodeList) {
        this.nodeList = nodeList;
    }

    public List<GraphEdge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<GraphEdge> edgeList) {
        this.edgeList = edgeList;
    }

    public void addNode(GraphNode node) {
        nodeList.add(node);
    }

    public void addEdge(GraphEdge edge) {
        edgeList.add(edge);
    }

    public void transpose() {
        for (GraphEdge edge : edgeList) {
            GraphNode temp = edge.getStartNode();
            edge.setStartNode(edge.getFinishNode());
            edge.setFinishNode(temp);
        }
    }

    @Override
    public Graph clone() throws CloneNotSupportedException {
        return (Graph) super.clone();
    }
}
