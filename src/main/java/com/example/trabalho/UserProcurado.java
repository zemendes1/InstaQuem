package com.example.trabalho;

public class UserProcurado {

    public static User LoggedUser;
    public static User Utilizador_POU;

    public  static void utilizadorAtual(User utilizador){
        LoggedUser = utilizador;
    }

    public static void utilizadorProcurado(User utilizador){
        Utilizador_POU = utilizador;
    }
}
