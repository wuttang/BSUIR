package com.example.lb5.controllers;

import com.example.lb5.GraphApplication;
import com.example.lb5.MainFunction;
import com.example.lb5.models.AdjacencyMatrixElement;
import com.example.lb5.models.GraphEdge;
import com.example.lb5.models.GraphNode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

public class SimpleCyclesController implements Initializable {
    @FXML
    public TableView<List<GraphNode>> cyclesTable;
    @FXML
    public TableColumn<List<GraphNode>, String> cyclesColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cyclesColumn.setCellValueFactory(cellData -> {
            StringBuilder path = new StringBuilder();
            for (int i = 0; i < cellData.getValue().size() - 1; i++) path.append(cellData.getValue().get(i).getInfo()).append(" -> ");
            path.append(cellData.getValue().get(cellData.getValue().size() - 1).getInfo());

            return new SimpleStringProperty(new String(path));
        });


        cyclesTable.setItems(FXCollections.observableArrayList(MainFunction.findEulerCycles(GraphApplication.currentGraph)));
    }
}
