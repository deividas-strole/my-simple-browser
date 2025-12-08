package com.visitormaker.mysimplebrowser;//package com.visitormaker.mysimplebrowser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SimpleWebBrowser extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a WebView and WebEngine
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // URL TextField
        TextField urlField = new TextField();
        urlField.setPromptText("Enter URL...");
        urlField.setOnAction(event -> webEngine.load(urlField.getText()));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> webEngine.executeScript("history.back()"));

        Button forwardButton = new Button("Forward");
        forwardButton.setOnAction(event -> webEngine.executeScript("history.forward()"));

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> webEngine.reload());

        webEngine.load("https://www.google.com");

        ToolBar toolBar = new ToolBar(backButton, forwardButton, refreshButton, urlField);

        webEngine.locationProperty().addListener((obs, oldLocation, newLocation) -> urlField.setText(newLocation));

        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(webView);

        // Set up the scene and stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Simple Web Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);

    }
}
