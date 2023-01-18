package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Login_Controlador extends Page {

    @FXML
    private Label label;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    Login Logica2 = new Login();

    DropShadow dropShadow = new DropShadow();
    final Glow glow = new Glow(1);

    @FXML
    private Button botao_login;
    @FXML
    private Label go_toCreate;


    @FXML
    void keyPressed(KeyEvent event) throws IOException{
        if (event.getCode() == KeyCode.ENTER) {
            userLogin();
        }
    }


    public void userLogin() throws IOException{
        label.setText(Logica2.checkLogin(username.getText(), password.getText()));
        if(label.getText().equals("Login efetuado com Sucesso.")) {
            this.changeScene("pagina_principal.fxml", 1200, 800);
        }
    }

    public void Action_go_toCreate() throws IOException{
        this.changeScene("registo.fxml", 600, 400);
    }

    @FXML
    public void mouse_in () {

        if (go_toCreate.isHover()) {
            go_toCreate.setEffect(glow);

        } else if (botao_login.isHover()) {

            botao_login.setEffect(dropShadow);

        }
        else {
            botao_login.setEffect(null);
            go_toCreate.setEffect(null);
        }
    }
}
