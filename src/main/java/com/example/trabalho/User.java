package com.example.trabalho;
import java.util.ArrayList;
import java.util.Random;

public class User {

    private final String email;

    private final String username;

    private String password;

    public int numeroAmigos = 0;

    Random rand = new Random(); //instance of random class
    public int ProfilePic=rand.nextInt(7);

    public ArrayList<User>  ListaAmigos = new ArrayList<>(); // de interesse testar com string

    public ArrayList<User>  PedidosdeAmizade_Enviado = new ArrayList<>(); //mesmo

    public ArrayList<User>  PedidosdeAmizade_Recebido = new ArrayList<>(); //mesmo

    private String descricao = "";
    public ArrayList<Post> TodosOsPosts = new ArrayList<>();

    public boolean AdminKey=false;

    public boolean ModKey=false;

    public boolean silenciado=false;


    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public ArrayList<User> getPedidosdeAmizade_Recebido(){return this.PedidosdeAmizade_Recebido;}
    public ArrayList<User> getPedidosdeAmizade_Enviado(){return this.PedidosdeAmizade_Enviado;}
    public String getUsername() {
        return this.username;
    }
    public String getDescricao() {return this.descricao;}
    public void setDescricao(String descricao){this.descricao = descricao;}

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password){this.password = password;}

    public String getEmail() {
        return this.email;
    }
    public void setPic(int pic){this.ProfilePic=pic;}

    public int getPic() {return this.ProfilePic;}


    public static User getUser (String NomeDeUsuario){
        ArrayList<User> Utilizadores = Login.getUserList();

        for(User user : Utilizadores){
            if( user.getUsername().equals(NomeDeUsuario)){
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return "Username:"+username+" Password:"+password+" Email"+email;
    }
}
