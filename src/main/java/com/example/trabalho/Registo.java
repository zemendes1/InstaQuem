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


public class Registo extends Page {

    @FXML
    public Label label_Register = new Label() , goBack_toLogin;
    @FXML
    public TextField username_Register = new TextField();
    @FXML
    public TextField email_Register = new TextField();
    @FXML
    public PasswordField password_Register = new PasswordField();
    @FXML
    public PasswordField password_1_Register = new PasswordField();

    DropShadow dropShadow = new DropShadow();
    final Glow glow = new Glow(1);

    @FXML
    private Button botao_criar_conta;



    @FXML
    public void keyPressed(KeyEvent event){//Permite utilizar o enter para criar conta
        if (event.getCode() == KeyCode.ENTER) {
            checkRegister();
        }
    }

    public void checkRegister(){

        RegistoLogica check = new RegistoLogica();

       switch (check.checkRegister_Logica(username_Register.getText(), email_Register.getText(), password_Register.getText(), password_1_Register.getText())){
           case(1) -> label_Register.setText("Necessita de preencher todos os campos.");

           case(2) -> label_Register.setText("As Passwords introduzidas não coincidem");

           case(3) -> label_Register.setText("Username demasiado curto");

           case(4) -> label_Register.setText("Username demasiado longo");

           case(5)->label_Register.setText( "Password demasiado curta");

           case(6)->label_Register.setText( "Password demasiado longa");

           case(7)->label_Register.setText("O email introduzido tem o formato errado");

           case(8)->label_Register.setText("Username possui carateres inválidos.");

           case(9)->label_Register.setText("Password possui carateres inválidos.");

           case(10)->label_Register.setText( "O username inserido já existe");

           case(11)->label_Register.setText("O email inserido já existe");

           case(0)->label_Register.setText("Conta Criada com Sucesso!");

       }


    }

    public void Action_goBack_toLogin() throws IOException{
        this.changeScene("hello-view.fxml", 600, 400);
    }

    @FXML
    public void mouse_in () {

        if (goBack_toLogin.isHover()) {
            goBack_toLogin.setEffect(glow);

        } else if (botao_criar_conta.isHover()) {

            botao_criar_conta.setEffect(dropShadow);

        }
        else {
            botao_criar_conta.setEffect(null);
            goBack_toLogin.setEffect(null);
        }
    }
}
