package com.example.trabalho;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.example.trabalho.UserProcurado.utilizadorAtual;


public class Login{

    private static final ArrayList<User> userList = fillListUsers();

    private static ArrayList<User> fillListUsers() {
        ArrayList<User> toReturn = new ArrayList<>();

        //Fetch from file
        Stream<String> lines = Stream.of();
        try {
            InputStream inputStream = Login.class.getResourceAsStream("/db.txt");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            lines = bufferedReader.lines();
        } catch (Exception e) {
            System.out.println("File not found!");
        }

        //Convert Stream to arraylist
        List<String> linesList = lines.toList();
        ArrayList<String> linesArrayList = new ArrayList<>(linesList);

        for(String line : linesArrayList) {
            try {
                String[] speratedLine = line.split(",");
                toReturn.add(new User(speratedLine[0].trim(), speratedLine[1].trim(), speratedLine[2].trim()));
            } catch (Exception ignored) {

            }
        }
        for (User user : toReturn) {
            if (user.getUsername().equals("admin")) {
                user.AdminKey = true;
            }
        }


        return toReturn;
    }

    public String checkLogin(String username, String password) {

        if(username.isEmpty() || password.isEmpty()) {
            return "Necessita de preencher todos os campos.";
        }

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    utilizadorAtual(user);
                    return "Login efetuado com Sucesso.";
                } else {
                    return "Password Errada";
                }
            }
        }
        return "Username não encontrado.";
    }


    public static ArrayList<User> getUserList(){
        return userList;
    }


}
