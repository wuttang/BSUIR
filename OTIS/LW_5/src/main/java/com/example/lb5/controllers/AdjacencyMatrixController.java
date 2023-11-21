package com.example.lb5.controllers;

import com.example.lb5.GraphApplication;
import com.example.lb5.models.AdjacencyMatrixElement;
import com.example.lb5.models.GraphEdge;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdjacencyMatrixController implements Initializable {

    @FXML
    public TableView<AdjacencyMatrixElement> adjacencyTable;
    @FXML
    public TableColumn<AdjacencyMatrixElement, String> firstNodeColumn;
    @FXML
    public TableColumn<AdjacencyMatrixElement, String> secondNodeColumn;
    @FXML
    public TableColumn<AdjacencyMatrixElement, String> pathColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<AdjacencyMatrixElement> adjacencyMatrixElements = FXCollections.observableArrayList();
        for (GraphEdge edge : GraphApplication.currentGraph.getEdgeList()) {
            adjacencyMatrixElements.add(new AdjacencyMatrixElement(edge.getStartNode().getInfo(), edge.getFinishNode().getInfo(),
                    edge.getWeight() != null ? edge.getWeight() : 1));
        }
        firstNodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartNodeName()));
        secondNodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinishNodeName()));
        pathColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getPathLength().toString()));

        adjacencyTable.setItems(adjacencyMatrixElements);
    }
}
