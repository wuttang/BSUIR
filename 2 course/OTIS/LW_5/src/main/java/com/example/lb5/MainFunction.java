package com.example.lb5;

import com.example.lb5.controllers.MainController;
import com.example.lb5.models.Graph;
import com.example.lb5.models.GraphEdge;
import com.example.lb5.models.GraphNode;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainFunction {
    public static final double INF = Double.MAX_VALUE;

    public static boolean isNodeClicked(double coordX, double coordY) {
        for (int i = 0; i < GraphApplication.currentGraph.getNodeList().size(); i++) {
            if (GraphApplication.currentGraph.getNodeList().get(i).getCoordX() + 10 >= coordX && GraphApplication.currentGraph.getNodeList().get(i).getCoordX() - 10 <= coordX &&
                    GraphApplication.currentGraph.getNodeList().get(i).getCoordY() + 10 >= coordY && GraphApplication.currentGraph.getNodeList().get(i).getCoordY() - 10 <= coordY) {
                return true;
            }
        }

        return false;
    }

    public static GraphNode getNodeByCoord(double coordX, double coordY) {
        for (int i = 0; i < GraphApplication.currentGraph.getNodeList().size(); i++) {
            if (GraphApplication.currentGraph.getNodeList().get(i).getCoordX() + 10 >= coordX && GraphApplication.currentGraph.getNodeList().get(i).getCoordX() - 10 <= coordX &&
                    GraphApplication.currentGraph.getNodeList().get(i).getCoordY() + 10 >= coordY && GraphApplication.currentGraph.getNodeList().get(i).getCoordY() - 10 <= coordY) {
                return GraphApplication.currentGraph.getNodeList().get(i);
            }
        }

        return null;
    }

    private static void dfs(GraphNode startNode, HashMap<GraphNode, Boolean> visited, Graph graph) {
        visited.put(startNode, true);
        for (GraphNode neighbour : graph.getNeighbors(startNode)) {
            if (!visited.get(neighbour)) {
                dfs(neighbour, visited, graph);
            }
        }
    }

    public static boolean isConnected(Graph graph) throws CloneNotSupportedException {
        HashMap<GraphNode, Boolean> visited = new HashMap<>();
        for (GraphNode node : graph.getNodeList()) visited.putIfAbsent(node, false);
        dfs(graph.getNodeList().get(0), visited, graph);

        for (boolean isVisited : visited.values()) {
            if (!isVisited) {
                return false;
            }
        }

        Graph transposeGraph = graph.clone();
        transposeGraph.transpose();

        for (GraphNode node : transposeGraph.getNodeList()) visited.put(node, false);
        dfs(transposeGraph.getNodeList().get(0), visited, transposeGraph);
        for (boolean isVisited : visited.values()) {
            if (!isVisited) {
                return false;
            }
        }

        return true;
    }

    private static GraphNode findMostConnectiveNode(Graph graph) {
        HashMap<GraphNode, Integer> nodesCountMap = new HashMap<>();
        for (GraphNode node : graph.getNodeList()) nodesCountMap.put(node, 0);
        for (GraphEdge edge : graph.getEdgeList()) {
            nodesCountMap.put(edge.getStartNode(), nodesCountMap.get(edge.getStartNode()) + 1);
        }

        GraphNode mostConnectiveNode = graph.getNodeList().get(0);
        for (GraphNode node : nodesCountMap.keySet()) {
            if (nodesCountMap.get(mostConnectiveNode) < nodesCountMap.get(node)) mostConnectiveNode = node;
        }

        return mostConnectiveNode;
    }

    public static void makeConnectiveGraph(Graph graph, GraphicsContext gc) throws CloneNotSupportedException {
        if (isConnected(graph)) return;
        GraphNode mostConnectiveNode = findMostConnectiveNode(graph);
        List<GraphEdge> newEdges = new ArrayList<>();

        for (GraphEdge edge : graph.getEdgeList()) {
            if (edge.getStartNode().equals(mostConnectiveNode)) {
                edge.setOrient(false);
                newEdges.add(new GraphEdge(edge.getFinishNode(), edge.getStartNode(), edge.getWeight(), false, edge.getColor()));
            }
        }

        for (GraphEdge edge : newEdges) {
            graph.addEdge(edge);
        }

        for (GraphNode node : graph.getNodeList()) {
            if (!graph.getNeighbors(mostConnectiveNode).contains(node) && !node.equals(mostConnectiveNode)) {
                graph.getEdgeList().add(new GraphEdge(mostConnectiveNode, node, false, Color.BLACK));
                graph.getEdgeList().add(new GraphEdge(node, mostConnectiveNode, false, Color.BLACK));
            }
        }
    }


    public static HashMap<List<GraphNode>, Double> getDistances(Graph graph) {
        HashMap<List<GraphNode>, Double> result = new HashMap<>();
        for (GraphNode startNode : graph.getNodeList()){
            INNER:
            for (GraphNode finishNode : graph.getNodeList()) {
                for (GraphEdge edge : graph.getEdgeList()) {
                    if (edge.getStartNode().equals(startNode) && edge.getFinishNode().equals(finishNode)) {
                        result.put(List.of(startNode, finishNode), edge.getWeight() == null ? 1.0 : edge.getWeight());
                        continue INNER;
                    }
                }
                result.put(List.of(startNode, finishNode), INF);
            }
        }

        for (int k = 0; k < graph.getNodeList().size(); k++) {
            for (int j = 0; j < graph.getNodeList().size(); j++) {
                for (int i = 0; i < graph.getNodeList().size(); i++) {
                    if (graph.getNodeList().get(i) != graph.getNodeList().get(j)) {
                        result.put(List.of(graph.getNodeList().get(i), graph.getNodeList().get(j)),
                                Math.min(result.get(List.of(graph.getNodeList().get(i), graph.getNodeList().get(j))),
                                        result.get(List.of(graph.getNodeList().get(i), graph.getNodeList().get(k))) +
                                                result.get(List.of(graph.getNodeList().get(k), graph.getNodeList().get(j)))));
                    }
                }
            }
        }

        return result;
    }




    public static List<List<GraphNode>> findEulerCycles(Graph graph) {
        List<List<GraphNode>> result = new ArrayList<>();
        HashMap<GraphNode, Boolean> visited = new HashMap<>();

        for (GraphNode node : graph.getNodeList()) {
            for (GraphNode visitedNode : graph.getNodeList()) {
                visited.put(visitedNode, false);
            }
            List<GraphNode> currentPath = new ArrayList<>();
            dfsForEulerCycles(graph, node, node, visited, currentPath, result);
        }

        return result;
    }

    private static void dfsForEulerCycles(Graph graph, GraphNode startNode, GraphNode currentNode, HashMap<GraphNode, Boolean> visited, List<GraphNode> currentPath, List<List<GraphNode>> result) {
        currentPath.add(currentNode);

        if (startNode == currentNode && currentPath.size() > 1) {
            result.add(new ArrayList<>(currentPath));
        } else {
            for (GraphNode neighbor : graph.getNeighbors(currentNode)) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    dfsForEulerCycles(graph, startNode, neighbor, visited, currentPath, result);
                    visited.put(currentPath.get(currentPath.size() - 1), false);
                    currentPath.remove(currentPath.size() - 1);
                }
            }
        }
    }

    public static List<List<GraphNode>> findAllPaths(Graph graph, GraphNode start, GraphNode end) {
        List<List<GraphNode>> paths = new ArrayList<>();
        List<GraphNode> currentPath = new ArrayList<>();
        HashMap<GraphNode, Boolean> visited = new HashMap<>();

        for (GraphNode node : graph.getNodeList()) {
            visited.put(node, false);
        }

        currentPath.add(start);
        dfsForPaths(graph, start, end, visited, currentPath, paths);

        return paths;
    }


    private static void dfsForPaths(Graph graph, GraphNode current, GraphNode end, HashMap<GraphNode, Boolean> visited, List<GraphNode> currentPath, List<List<GraphNode>> paths) {
        visited.put(current, true);

        if (current == end) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            for (GraphNode neighbor : graph.getNeighbors(current)) {
                if (!visited.get(neighbor)) {
                    currentPath.add(neighbor);
                    dfsForPaths(graph, neighbor, end, visited, currentPath, paths);
                    currentPath.remove(currentPath.size() - 1);
                }
            }
        }

        visited.put(current, false); // Reset the node's visited status for other paths
    }

    public static double getDistanceBetweenNodes(Graph graph, GraphNode start, GraphNode end) {
        HashMap<List<GraphNode>, Double> distances = getDistances(graph);

        if (distances.containsKey(List.of(start, end))) {
            return distances.get(List.of(start, end));
        }

        return INF; // Возврат значения "бесконечность", если путь между узлами не найден
    }



    private static GraphNode getNodeByName(Graph graph, String name) {

        for (GraphNode node : graph.getNodeList()) {
            if (node.getInfo().equals(name)) {
                return node;
            }
        }

        return null;
    }
}
