module com.example.lb {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens com.example.lb5 to com.fasterxml.jackson.databind;
    opens com.example.lb5.controllers to javafx.fxml;
    exports com.example.lb5;
    exports com.example.lb5.controllers;
    exports com.example.lb5.models;
}