package com.resumebuilder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new Label("Resume Builder started!"), 800, 800));
        primaryStage.setTitle("Resume Builder");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}