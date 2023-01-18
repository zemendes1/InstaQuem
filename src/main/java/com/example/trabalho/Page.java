package com.example.trabalho;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

//Classe que possui metodos comuns a todas as páginas
public class Page {

   public static String CurrentScene = "";

    public void changeScene(String fxml, int x, int y) throws IOException {
        CurrentScene=fxml;
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Main.stg.setScene(new Scene(pane, x, y));
        Main.stg.centerOnScreen();
        Main.stg.getScene().setRoot(pane);
    }


}
