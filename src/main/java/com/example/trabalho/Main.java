package com.example.trabalho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static Stage stg;

    @Override
    public void start(Stage primarystage) throws IOException {
        stg = primarystage;
        primarystage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primarystage.getIcons().add(new Image("logo-color.png"));
        primarystage.setTitle("InstaQuem");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}