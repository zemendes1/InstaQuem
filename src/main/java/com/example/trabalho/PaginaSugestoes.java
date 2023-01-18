package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.trabalho.Amigos.check_amizade;
import static com.example.trabalho.Amigos.envia_pedido;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.utilizadorProcurado;

public class PaginaSugestoes extends Page implements Initializable{

    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> NaoAmigos = new ArrayList<>();

    @FXML
    private ListView<String> SearchList = new ListView<>();

    @FXML
    private TextField SearchBar;

    public String utilizador_selecionado;

    @FXML
    private  ImageView FotoDePerfil1 = new ImageView() ;

    @FXML
    private  Label Loggedusername1 = new Label();

    @FXML
    private  ImageView foto1,foto2,foto3;

    @FXML
    private  Label nome1,nome2,nome3,Mensagem_de_Erro;

    @FXML
    private Button botao1, botao2, botao3;

    private int sugestao1,sugestao2,sugestao3;
    //glow
    final Glow glow = new Glow(1);

    final Glow glow_foto = new Glow();
    @FXML
    private  ImageView imagem_da_app;

    @FXML
    private Text texto_amigos_s;
    @FXML
    private Text logout_button,text_meuperfil,text_menu,texto_sugestoes,texto_post;

    DropShadow dropShadow = new DropShadow();

    @FXML
    public void Action_Search_Appear(){
        if(SearchBar.getText().isEmpty()) {
            SearchList.setVisible(false);
        }
        else {
            SearchList.setVisible(true);
            SearchList.getItems().clear();
            SearchList.getItems().addAll(searchList(SearchBar.getText(),words));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<User> teste = Login.getUserList();
        for(User user : teste){
            words.add(user.getUsername());

        }
        SearchList.getItems().addAll(words);
        //selecionar outro perfil
        SearchList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> utilizador_selecionado = SearchList.getSelectionModel().getSelectedItem());

        VerificaAmigos();

        Loggedusername1.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic()+".png");
        FotoDePerfil1.setImage(image1);
    }

    private void VerificaAmigos(){

        if(NaoAmigos.size()>0){NaoAmigos.clear();}

        ArrayList<User> NaoAmizades = Login.getUserList();
        for(User user : NaoAmizades){
            if(check_amizade(LoggedUser, user)==0 && !LoggedUser.getUsername().equals(user.getUsername())){//alterar quando conseguir saber quais sao as amizades
                NaoAmigos.add(user.getUsername());
            }
        }

        List<Integer> list = new ArrayList<>();//criar array de numeros random
        IntStream.range(0, NaoAmigos.size()).forEach(list::add);
        Collections.shuffle(list);



        if(NaoAmigos.size() == 0 ){
            foto1.setVisible(false);nome1.setVisible(false);botao1.setVisible(false);
            foto2.setVisible(false);nome2.setVisible(false);botao2.setVisible(false);
            foto3.setVisible(false);nome3.setVisible(false);botao3.setVisible(false);
            Mensagem_de_Erro.setText("Não existem novos amigos para adicionar");
        }
        else if(NaoAmigos.size() == 1 ){
            sugestao1= list.get(0);//vai buscar numero random
            nome1.setText(NaoAmigos.get(sugestao1));
            Image image1 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao1))).getPic()+".png");
            foto1.setImage(image1);
            botao1.setVisible(true);

            foto2.setVisible(false);nome2.setVisible(false);botao2.setVisible(false);
            foto3.setVisible(false);nome3.setVisible(false);botao3.setVisible(false);
        }
        else if(NaoAmigos.size() == 2 ){

            sugestao1= list.get(0);//vai buscar numero random
            nome1.setText(NaoAmigos.get(sugestao1));
            Image image1 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao1))).getPic()+".png");
            foto1.setImage(image1);
            botao1.setVisible(true);

            sugestao2= list.get(1);
            nome2.setText(NaoAmigos.get(sugestao2));
            Image image2 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao2))).getPic()+".png");
            foto2.setImage(image2);
            botao2.setVisible(true);

            foto3.setVisible(false);nome3.setVisible(false);botao3.setVisible(false);
        }
        else {

            sugestao1= list.get(0);
            nome1.setText(NaoAmigos.get(sugestao1));
            Image image1 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao1))).getPic()+".png");
            foto1.setImage(image1);
            botao1.setVisible(true);

            sugestao2= list.get(1);
            nome2.setText(NaoAmigos.get(sugestao2));
            Image image2 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao2))).getPic()+".png");
            foto2.setImage(image2);
            botao2.setVisible(true);


            sugestao3= list.get(2);
            nome3.setText(NaoAmigos.get(sugestao3));
            Image image3 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao3))).getPic()+".png");
            foto3.setImage(image3);
            botao3.setVisible(true);
        }

    }

    private void VerificaAmigos(int i ){

        Random rand = new Random();

        if(NaoAmigos.size()>0){ NaoAmigos.clear();}

        ArrayList<User> NaoAmizades = Login.getUserList();
        for(User user : NaoAmizades){
            if(check_amizade(LoggedUser, user)==0 && !LoggedUser.getUsername().equals(user.getUsername())){//alterar quando conseguir saber quais sao as amizades
                NaoAmigos.add(user.getUsername());
            }
        }


        if(NaoAmigos.size()>2){
            switch (i) {
                case 1 -> {
                    sugestao1= rand.nextInt(NaoAmigos.size());
                    while(NaoAmigos.get(sugestao1).equals(nome2.getText()) || NaoAmigos.get(sugestao1).equals(nome3.getText())){
                        sugestao1= rand.nextInt(NaoAmigos.size());
                    }

                    nome1.setText(NaoAmigos.get(sugestao1));
                    Image image1 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao1))).getPic()+".png");
                    foto1.setImage(image1);
                    botao1.setVisible(true);
                }
                case 2 -> {
                    sugestao2= rand.nextInt(NaoAmigos.size());
                    while(NaoAmigos.get(sugestao2).equals(nome1.getText()) || NaoAmigos.get(sugestao2).equals(nome3.getText())){
                        sugestao2= rand.nextInt(NaoAmigos.size());
                    }
                    nome2.setText(NaoAmigos.get(sugestao2));
                    Image image1 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao2))).getPic()+".png");
                    foto2.setImage(image1);
                    botao2.setVisible(true);
                }
                case 3 -> {
                    sugestao3= rand.nextInt(NaoAmigos.size());
                    while(NaoAmigos.get(sugestao3).equals(nome1.getText()) || NaoAmigos.get(sugestao3).equals(nome2.getText())){
                        sugestao3= rand.nextInt(NaoAmigos.size());
                    }
                    nome3.setText(NaoAmigos.get(sugestao3));
                    Image image1 = new Image(Objects.requireNonNull(getUser(NaoAmigos.get(sugestao3))).getPic()+".png");
                    foto3.setImage(image1);
                    botao3.setVisible(true);
                }
            }

        }

        if(NaoAmigos.size()<3){
            switch (i) {
                case 1 -> {
                    foto1.setVisible(false);
                    nome1.setVisible(false);
                    botao1.setVisible(false);
                }
                case 2 -> {
                    foto2.setVisible(false);
                    nome2.setVisible(false);
                    botao2.setVisible(false);
                }
                case 3 -> {
                    foto3.setVisible(false);
                    nome3.setVisible(false);
                    botao3.setVisible(false);
                }
            }
        }

        if(NaoAmigos.size() == 0 ){
            foto1.setVisible(false);nome1.setVisible(false);botao1.setVisible(false);
            foto2.setVisible(false);nome2.setVisible(false);botao2.setVisible(false);
            foto3.setVisible(false);nome3.setVisible(false);botao3.setVisible(false);
            Mensagem_de_Erro.setText("Não existem novos amigos para adicionar");
        }

    }


    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }

    public void EnviaPedido1() {
        envia_pedido(Objects.requireNonNull(getUser(nome1.getText())), LoggedUser);
        VerificaAmigos(1);
    }

    public void EnviaPedido2() {
        envia_pedido(Objects.requireNonNull(getUser(nome2.getText())), LoggedUser);
        VerificaAmigos(2);
    }

    public void EnviaPedido3() {
        envia_pedido(Objects.requireNonNull(getUser(nome3.getText())), LoggedUser);
        VerificaAmigos(3);
    }


    public void userLogout() throws IOException{
        this.changeScene("hello-view.fxml", 600, 400);
    }

    public void go_to_PaginaPrincipal() throws IOException{
        this.changeScene("pagina_principal.fxml", 1200, 800);
    }

    public void go_to_outro_user_1() throws IOException{
        utilizadorProcurado(getUser(nome1.getText()));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_outro_user_2() throws IOException{
        utilizadorProcurado(getUser(nome2.getText()));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_outro_user_3() throws IOException{
        utilizadorProcurado(getUser(nome3.getText()));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_outro_user() throws IOException{
        if (utilizador_selecionado != null && !utilizador_selecionado.equals(LoggedUser.getUsername())) {
            utilizadorProcurado(getUser (utilizador_selecionado));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }
    }

    public void go_to_escreverposts() throws IOException{
        this.changeScene("Escrever_posts.fxml", 1200, 800);
    }

    public void go_to_logged_user() throws IOException{
        utilizadorProcurado(LoggedUser);
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_lista_amigos() throws IOException{
        this.changeScene("Lista_Amigos.fxml", 1200, 800);
    }

    @FXML
    public void mouse_in () {

        if (Loggedusername1.isHover() || FotoDePerfil1.isHover()) {
            Loggedusername1.setEffect(glow);
            FotoDePerfil1.setEffect(glow_foto);

        } else if (text_menu.isHover()) {

            text_menu.setEffect(glow);

        } else if (text_meuperfil.isHover()) {

            text_meuperfil.setEffect(glow);

        } else if (imagem_da_app.isHover()) {

            imagem_da_app.setEffect(glow);

        } else if (logout_button.isHover()) {

            logout_button.setEffect(glow);

        } else if (texto_sugestoes.isHover()) {

            texto_sugestoes.setEffect(glow);

        } else if (texto_post.isHover()) {

            texto_post.setEffect(glow);
        } else if (nome1.isHover() || foto1.isHover()) {

            nome1.setEffect(glow);foto1.setEffect(glow_foto);
        } else if (nome2.isHover() || foto2.isHover()) {

            nome2.setEffect(glow);foto2.setEffect(glow_foto);
        } else if (nome3.isHover() || foto3.isHover()) {

            nome3.setEffect(glow);foto3.setEffect(glow_foto);
        }
        else if (botao1.isHover()) {

            botao1.setEffect(dropShadow);

        }
        else if (botao2.isHover()) {

            botao2.setEffect(dropShadow);

        }
        else if (botao3.isHover()) {

            botao3.setEffect(dropShadow);

        }
        else if(texto_amigos_s.isHover()){
            texto_amigos_s.setEffect(glow);
        }
        else {
            Loggedusername1.setEffect(null);
            text_menu.setEffect(null);
            text_meuperfil.setEffect(null);
            imagem_da_app.setEffect(null);
            logout_button.setEffect(null);
            texto_sugestoes.setEffect(null);
            texto_post.setEffect(null);
            FotoDePerfil1.setEffect(null);
            nome1.setEffect(null);
            nome2.setEffect(null);
            nome3.setEffect(null);
            foto1.setEffect(null);
            foto2.setEffect(null);
            foto3.setEffect(null);
            botao1.setEffect(null);
            botao2.setEffect(null);
            botao3.setEffect(null);
            texto_amigos_s.setEffect(null);
        }
    }
}