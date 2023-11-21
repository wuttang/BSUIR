package com.example.lb5.controllers;

import com.example.lb5.GraphApplication;
import com.example.lb5.MainFunction;
import com.example.lb5.models.Graph;
import com.example.lb5.models.GraphEdge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import com.example.lb5.models.GraphNode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.stage.FileChooser;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    public TextField nodeName;
    @FXML
    public Canvas canvas;
    @FXML
    public TextField edgeWeight;
    @FXML
    public RadioButton orientToggle;
    @FXML
    public RadioButton nonorientToggle;
    @FXML
    public ColorPicker nodeColorPicker;
    @FXML
    public ColorPicker edgeColorPicker;
    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void clickOnCanvas(MouseEvent mouseEvent) {
        if (!MainFunction.isNodeClicked(mouseEvent.getX(), mouseEvent.getY())) {
            setNodeOnCanvas(mouseEvent);
        } else {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                GraphNode deletedNode = MainFunction.getNodeByCoord(mouseEvent.getX(), mouseEvent.getY());
                GraphApplication.currentGraph.getNodeList().removeIf(el -> el == deletedNode);
                GraphApplication.currentGraph.getEdgeList().removeIf(el -> el.getStartNode() == deletedNode || el.getFinishNode() == deletedNode);
                updateCanvas();
            } else {
                setEdgeBetweenNodes(mouseEvent);
            }
        }
    }

    @FXML
    public void setEdgeBetweenNodes(MouseEvent mouseEvent) {
        boolean isOrient = orientToggle.isSelected();
        GraphEdge currentEdge;
        if (GraphEdge.getLastAddedEdge() == null || GraphEdge.getLastAddedEdge().getFinishNode() != null) {
            if (edgeWeight.getText().isEmpty()) {
                currentEdge = new GraphEdge(MainFunction.getNodeByCoord(mouseEvent.getX(), mouseEvent.getY()), null, isOrient, edgeColorPicker.getValue());
                GraphApplication.currentGraph.addEdge(currentEdge);
            }
            else {
                currentEdge = new GraphEdge(MainFunction.getNodeByCoord(mouseEvent.getX(), mouseEvent.getY()), null, Double.parseDouble(edgeWeight.getText()), isOrient, null);
                GraphApplication.currentGraph.addEdge(currentEdge);
            }
        } else {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            currentEdge = GraphEdge.getLastAddedEdge();
            currentEdge.setFinishNode(MainFunction.getNodeByCoord(mouseEvent.getX(), mouseEvent.getY()));
            currentEdge.setColor(edgeColorPicker.getValue());
            if (!currentEdge.isOrient()) {
                GraphEdge reversedCurrentEdge = new GraphEdge(currentEdge.getFinishNode(), currentEdge.getStartNode(), currentEdge.getWeight(), currentEdge.isOrient(), null);
                GraphApplication.currentGraph.addEdge(reversedCurrentEdge);
            }
            gc.setFill(currentEdge.getColor());
            if (currentEdge.isOrient()) {
                drawArrow(gc, currentEdge.getStartNode().getCoordX(), currentEdge.getStartNode().getCoordY(),
                        currentEdge.getFinishNode().getCoordX(), currentEdge.getFinishNode().getCoordY(), currentEdge.getColor());
                gc.setFill(currentEdge.getStartNode().getColor());
                gc.fillOval(currentEdge.getStartNode().getCoordX() - 10, currentEdge.getStartNode().getCoordY() - 10, 20, 20);
                gc.setFill(currentEdge.getFinishNode().getColor());
                gc.fillOval(currentEdge.getFinishNode().getCoordX() - 10, currentEdge.getFinishNode().getCoordY() - 10, 20, 20);
            } else {
                gc.setStroke(currentEdge.getColor());
                gc.strokeLine(currentEdge.getStartNode().getCoordX(), currentEdge.getStartNode().getCoordY(),
                        currentEdge.getFinishNode().getCoordX(), currentEdge.getFinishNode().getCoordY());
                gc.setFill(currentEdge.getStartNode().getColor());
                gc.fillOval(currentEdge.getStartNode().getCoordX() - 10, currentEdge.getStartNode().getCoordY() - 10, 20, 20);
                gc.setFill(currentEdge.getFinishNode().getColor());
                gc.fillOval(currentEdge.getFinishNode().getCoordX() - 10, currentEdge.getFinishNode().getCoordY() - 10, 20, 20);
            }

            gc.setFill(Color.BLUE);
            if (currentEdge.getWeight() != null) gc.fillText(Double.toString(currentEdge.getWeight()), (currentEdge.getStartNode().getCoordX() + currentEdge.getFinishNode().getCoordX()) / 2,
                    (currentEdge.getStartNode().getCoordY() + currentEdge.getFinishNode().getCoordY()) / 2);

            GraphApplication.currentGraph.getEdgeList().forEach(el -> System.out.println(el.getStartNode().getCoordX() + " " + el.getStartNode().getCoordY()));
            GraphApplication.currentGraph.getEdgeList().forEach(el -> System.out.println(el.getFinishNode().getCoordX() + " " + el.getFinishNode().getCoordY()));
        }
    }

    @FXML
    public void updateCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clearCanvas();
        for (GraphNode node : GraphApplication.currentGraph.getNodeList()) {
            gc.setFill(node.getColor());
            gc.fillOval(node.getCoordX() - 10, node.getCoordY() - 10, 20, 20);
            gc.setFill(Color.BLUE);
            gc.fillText(node.getInfo(), node.getCoordX() + 10, node.getCoordY() - 10);
        }

        for (GraphEdge edge : GraphApplication.currentGraph.getEdgeList()) {
            gc.setStroke(edge.getColor());
            if (!edge.isOrient()) {
                gc.strokeLine(edge.getStartNode().getCoordX(), edge.getStartNode().getCoordY(),
                        edge.getFinishNode().getCoordX(), edge.getFinishNode().getCoordY());
            } else {
                drawArrow(gc, edge.getStartNode().getCoordX(), edge.getStartNode().getCoordY(),
                        edge.getFinishNode().getCoordX(), edge.getFinishNode().getCoordY(), edge.getColor());
            }
            gc.setFill(edge.getStartNode().getColor());
            gc.fillOval(edge.getStartNode().getCoordX() - 10, edge.getStartNode().getCoordY() - 10, 20, 20);
            gc.setFill(edge.getFinishNode().getColor());
            gc.fillOval(edge.getFinishNode().getCoordX() - 10, edge.getFinishNode().getCoordY() - 10, 20, 20);
            gc.setFill(Color.BLUE);
            if (edge.getWeight() != null) gc.fillText(Double.toString(edge.getWeight()), (edge.getStartNode().getCoordX() + edge.getFinishNode().getCoordX()) / 2,
                    (edge.getStartNode().getCoordY() + edge.getFinishNode().getCoordY()) / 2);
        }
    }

    @FXML
    public void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    public void setNodeOnCanvas(MouseEvent mouseEvent) {
        GraphNode currentNode = new GraphNode(mouseEvent.getX(), mouseEvent.getY(), nodeName.getText(), nodeColorPicker.getValue());
        GraphApplication.currentGraph.addNode(currentNode);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(currentNode.getColor());
        gc.fillOval(currentNode.getCoordX() - 10, currentNode.getCoordY() - 10, 20, 20);
        gc.setFill(Color.BLUE);
        gc.fillText(currentNode.getInfo(), mouseEvent.getX() + 10, mouseEvent.getY() - 10);
    }

    public void drawArrow(GraphicsContext gc, double startCoordX, double startCoordY, double finishCoordX, double finishCoordY, Color color) {
        gc.setStroke(color);
        gc.strokeLine(startCoordX, startCoordY, finishCoordX, finishCoordY);

        double arrowAngle = Math.toRadians(15);
        double arrowLength = 20.0;
        double lineAngle = Math.atan2(finishCoordY - startCoordY, finishCoordX - startCoordX);

        double x1 = finishCoordX - arrowLength * Math.cos(lineAngle + arrowAngle);
        double y1 = finishCoordY - arrowLength * Math.sin(lineAngle + arrowAngle);
        double x2 = finishCoordX - arrowLength * Math.cos(lineAngle - arrowAngle);
        double y2 = finishCoordY - arrowLength * Math.sin(lineAngle - arrowAngle);

        double offsetX = 10.0 * Math.cos(lineAngle);
        double offsetY = 10.0 * Math.sin(lineAngle);

        gc.strokeLine(finishCoordX - offsetX, finishCoordY - offsetY, x1, y1);
        gc.strokeLine(finishCoordX - offsetX, finishCoordY - offsetY, x2, y2);
    }

    @FXML
    public void showAdjacencyMatrix(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(GraphApplication.class.getResource("/fxml/adjacency_matrix_form.fxml"));
        Scene adjacencyMatrixScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(adjacencyMatrixScene);

        stage.show();
    }

    public void isGraphConnected(ActionEvent actionEvent) throws CloneNotSupportedException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация о связности графа");
        if (MainFunction.isConnected(GraphApplication.currentGraph)) alert.setHeaderText("Данный граф является связным");
        else alert.setHeaderText("Данный граф не является связным");

        alert.showAndWait();
    }

    public void leadToConnectedGraph(ActionEvent actionEvent) throws CloneNotSupportedException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (MainFunction.isConnected(GraphApplication.currentGraph)) return;
        MainFunction.makeConnectiveGraph(GraphApplication.currentGraph, gc);
        updateCanvas();
    }

    @FXML
    public void clearGraph(ActionEvent actionEvent) {
        GraphApplication.currentGraph.setEdgeList(new ArrayList<>());
        GraphApplication.currentGraph.setNodeList(new ArrayList<>());
        updateCanvas();
    }

    public void findCycles(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(GraphApplication.class.getResource("/fxml/simple_cycles.fxml"));
        Scene findCyclesScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(findCyclesScene);

        stage.show();
    }

    @FXML
    public void findAllPathsBetweenNodes(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Поиск путей");
        dialog.setHeaderText("Введите имена начальной и конечной вершин:");
        dialog.setContentText("Имена вершин через запятую:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            String[] nodes = input.split(",");
            if (nodes.length == 2) {
                GraphNode startNode = getNodeByName(nodes[0].trim());
                GraphNode endNode = getNodeByName(nodes[1].trim());
                if (startNode != null && endNode != null) {
                    List<List<GraphNode>> paths = MainFunction.findAllPaths(GraphApplication.currentGraph, startNode, endNode);
                    displayPaths(paths);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Вершины не найдены");
                    alert.setContentText("Проверьте правильность введенных имен вершин.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неправильный формат ввода");
                alert.setContentText("Введите имена начальной и конечной вершин через запятую.");
                alert.showAndWait();
            }
        });
    }

    private GraphNode getNodeByName(String nodeName) {
        for (GraphNode node : GraphApplication.currentGraph.getNodeList()) {
            if (node.getInfo().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    private void displayPaths(List<List<GraphNode>> paths) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Пути между вершинами");
        if (paths.isEmpty()) {
            alert.setHeaderText("Нет путей между выбранными вершинами.");
        } else {
            StringBuilder pathsString = new StringBuilder("Найденные пути:\n");
            for (List<GraphNode> path : paths) {
                pathsString.append(path.stream().map(GraphNode::getInfo).collect(Collectors.joining(" -> ")));
                pathsString.append("\n");
            }
            alert.setHeaderText(pathsString.toString());
        }
        alert.showAndWait();
    }

    @FXML
    public void findDistanceBetweenNodes(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Найти расстояние");
        dialog.setHeaderText("Введите имена начальной и конечной вершин:");
        dialog.setContentText("Имена вершин через запятую:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            String[] nodes = input.split(",");
            if (nodes.length == 2) {
                GraphNode startNode = getNodeByName(nodes[0].trim());
                GraphNode endNode = getNodeByName(nodes[1].trim());
                if (startNode != null && endNode != null) {
                    double distance = MainFunction.getDistanceBetweenNodes(GraphApplication.currentGraph, startNode, endNode);
                    displayDistance(startNode.getInfo(), endNode.getInfo(), distance);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Вершины не найдены");
                    alert.setContentText("Проверьте правильность введенных имен вершин.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неправильный формат ввода");
                alert.setContentText("Введите имена начальной и конечной вершин через запятую.");
                alert.showAndWait();
            }
        });
    }

    private void displayDistance(String startNode, String endNode, double distance) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Расстояние между вершинами");
        if (distance == MainFunction.INF) {
            alert.setHeaderText(String.format("Между вершинами %s и %s нет пути.", startNode, endNode));
        } else {
            alert.setHeaderText(String.format("Расстояние между вершинами %s и %s: %.2f", startNode, endNode, distance));
        }
        alert.showAndWait();
    }

    @FXML
    private void onSaveButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Optional: makes the JSON file more readable

            Map<String, Object> graphMap = new HashMap<>();
            graphMap.put("nodes", GraphApplication.currentGraph.getNodeList());
            graphMap.put("edges", GraphApplication.currentGraph.getEdgeList());

            List<Map<String, Object>> nodeColors = new ArrayList<>();
            for (GraphNode node : GraphApplication.currentGraph.getNodeList()) {
                Map<String, Object> nodeColor = new HashMap<>();
                Color color = node.getColor();
                List<Double> colorComponents = Arrays.asList(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
                nodeColor.put("color", colorComponents);
                nodeColors.add(nodeColor);
            }
            graphMap.put("nodeColors", nodeColors);

            try {
                objectMapper.writeValue(selectedFile, graphMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void onOpenButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> graphMap = objectMapper.readValue(selectedFile, new TypeReference<Map<String, Object>>() {});

                List<Map<String, Object>> nodeColors = (List<Map<String, Object>>) graphMap.get("nodeColors");
                for (Map<String, Object> nodeColor : nodeColors) {
                    List<Double> colorComponents = (List<Double>) nodeColor.get("color");
                    Color color = new Color(colorComponents.get(0), colorComponents.get(1), colorComponents.get(2), colorComponents.get(3));
                }

                // Deserialize nodes and edges separately
                List<GraphNode> nodeList = objectMapper.convertValue(graphMap.get("nodes"), new TypeReference<List<GraphNode>>() {});
                List<GraphEdge> edgeList = objectMapper.convertValue(graphMap.get("edges"), new TypeReference<List<GraphEdge>>() {});

                GraphApplication.currentGraph.setNodeList(nodeList);
                GraphApplication.currentGraph.setEdgeList(edgeList);

                updateCanvas();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}