package com.example.trabalho;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegistoLogica {

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    Pattern emailpat = Pattern.compile(emailRegex);

    String usernameRgex =   "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

    Pattern usernamepat = Pattern.compile(usernameRgex);

    String passwordRgex =   "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){5,18}[a-zA-Z0-9]$";

    Pattern passwordpat = Pattern.compile(passwordRgex);

    public int checkRegister_Logica(String Username, String Email, String Password, String Password1) {//Verifica dados do registo

        if(Username.isEmpty() || Email.isEmpty() ||Password.isEmpty() ||  Password1.isEmpty()) {
            return 1;
            //"Necessita de preencher todos os campos.";
        }

        else if(!Password.equals(Password1) ){
            return 2;
            //"As Passwords introduzidas não coincidem";
        }
        else if(Username.length()<5){
            return 3;
            //"Username demasiado curto";
        }
        else if(Username.length()>20){
            return 4;
            //"Username demasiado longo";
        }

        else if(Password.length()<7){
            return 5;
           // "Password demasiado curta";
        }
        else if(Password.length()>20){
            return 6;
            // "Password demasiado longa";
        }

        else if(!emailpat.matcher(Email).matches()){
            return 7;
            //"O email introduzido tem o formato errado";
        }
        else if(!usernamepat.matcher(Username).matches()){
            return 8;
            //return "Username possui carateres inválidos.";
        }

        else if (!passwordpat.matcher(Password).matches()) {
            return 9;
            //"Password possui carateres inválidos.";
        }

        ArrayList<User> lista;
        lista = Login.getUserList();

        for (User user : lista) {

            if (user.getUsername().equals(Username)) {
                return 10;
                // "O username inserido já existe";
            } else if (user.getEmail().equals(Email)) {
                return 11;
                //"O email inserido já existe";

            }

        }



        return criaconta(Username, Email, Password);
        //"Conta Criada com Sucesso!"
    }

    public int criaconta(String Username, String Email, String Password){
        Login.getUserList().add((new User(Email, Username, Password)));
        return 0;
    }


}
