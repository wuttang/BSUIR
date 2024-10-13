package com.example.lb5;

import com.example.lb5.models.Graph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphApplication extends Application {
    public static Graph currentGraph = new Graph();
    public static Graph graphForProduct = new Graph();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(GraphApplication.class.getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("GraphApplication");

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}