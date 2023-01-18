package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.trabalho.PaginaPrincipal.ls;
import static com.example.trabalho.Publicacao.SelectedPostIndex;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.utilizadorProcurado;


public class  PaginaComentario extends Page implements Initializable {

    ArrayList<String> words = new ArrayList<>();
    @FXML
    private ListView<String> SearchList = new ListView<>();
    @FXML
    private TextField SearchBar;
    @FXML
    private ImageView FotoDePerfil = new ImageView() ;
    @FXML
    private  Label Loggedusername = new Label();
    @FXML
    public VBox Vbox_not;
    @FXML
    private GridPane PostSelecionado;
    @FXML
    private GridPane postGrid;
    public String utilizador_selecionado;
    public static int indexPost;
    public static int notificacoes_total=0;
    final Glow glow = new Glow(1);

    final Glow glow_foto = new Glow();
    @FXML
    private  ImageView imagem_da_app,escrever_icon;
    @FXML
    private Text logout_button,text_meuperfil,text_menu,texto_sugestoes,texto_post;
    @FXML
    private Label escrever;
    @FXML
    private ImageView  voltar=new ImageView();

    @FXML
    private Text texto_lista;


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


    private List<Notificacao> notifications(){


        List<Notificacao> ls = new ArrayList<>();


        for(int i = 0; i < LoggedUser.PedidosdeAmizade_Recebido.size(); i++){

            Notificacao noti = new Notificacao();

            noti.setImagem_user(LoggedUser.PedidosdeAmizade_Recebido.get(i).getPic());
            noti.setUser_pedido(LoggedUser.PedidosdeAmizade_Recebido.get(i).getUsername());
            ls.add(noti);

        }

        return ls;

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //lista de pesquisa
        ArrayList<User> bing = Login.getUserList();
        for(User user : bing){
            words.add(user.getUsername());

        }
        SearchList.getItems().addAll(words);
        SearchList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> utilizador_selecionado = SearchList.getSelectionModel().getSelectedItem());

        //Foto de perfil + user name do user logado
        Loggedusername.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic()+".png");
        FotoDePerfil.setImage(image1);


        //barra de notificacoes
        if(notificacoes_total!=0){
            List <Notificacao> nots = new ArrayList<>(notifications());
            for (Notificacao not : nots) {
                FXMLLoader fxmlLoadernot = new FXMLLoader();
                fxmlLoadernot.setLocation(getClass().getResource("notificacao.fxml"));

                try {
                    HBox hBox = fxmlLoadernot.load();
                    Notificacao_controller cic = fxmlLoadernot.getController();
                    cic.setData(not);
                    Vbox_not.getChildren().add(hBox);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        //adicionar post selecionado ao topo
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("Publicacao.fxml"));
        try {
            VBox postBox = fxmlLoader1.load();
            Publicacao postcontroller = fxmlLoader1.getController();
            postcontroller.SetData(ls.get(SelectedPostIndex));
            PostSelecionado.add(postBox, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //timeline comments
        List<Comment> comment1;
        comment1 = ls.get(SelectedPostIndex).getListaComentario();

        int collumns = 0; int rows=1;
        for (Comment comment : comment1) {

            //atualiza foto
            comment.setProfileImgSrc(Objects.requireNonNull(getUser(comment.getUsername())).getPic());

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Comentario.fxml"));
            try {
                VBox commentBox = fxmlLoader.load();

                Comentario commentcontroller = fxmlLoader.getController();
                commentcontroller.SetData(comment);

                if (collumns == 1) {
                    collumns = 0;
                    ++rows;
                }

                postGrid.add(commentBox, collumns++, rows);
                //GridPane.setMargin(postBox, new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }

    public void userLogout() throws IOException{
        this.changeScene("hello-view.fxml", 600, 400);
    }

    public void go_to_suggestions() throws IOException{
        this.changeScene("Sugestoes.fxml", 1200, 800);
    }

    public void go_to_outro_user() throws IOException{
        if (utilizador_selecionado != null && !utilizador_selecionado.equals(LoggedUser.getUsername())) {
            utilizadorProcurado(getUser (utilizador_selecionado));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }
        else if(utilizador_selecionado != null && utilizador_selecionado.equals(LoggedUser.getUsername())){
            utilizadorProcurado(getUser (utilizador_selecionado));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }
    }

    public void go_to_logged_user() throws IOException{
        utilizadorProcurado(LoggedUser);
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }
    public void go_to_escreverposts() throws IOException{
        this.changeScene("Escrever_posts.fxml", 1200, 800);
    }

    public void go_to_pagina_principal() throws IOException {
        this.changeScene("pagina_principal.fxml", 1200, 800);
    }

    public void go_to_escrever_comentario() throws IOException {
        this.changeScene("Escrever_comments.fxml", 1200, 800);
        indexPost=SelectedPostIndex;
    }

    @FXML
    public void mouse_in (){


        if (Loggedusername.isHover() || FotoDePerfil.isHover()){
            Loggedusername.setEffect(glow);
            FotoDePerfil.setEffect(glow_foto);

        }
        else if(text_menu.isHover()){

            text_menu.setEffect(glow);

        }
        else if(text_meuperfil.isHover()){

            text_meuperfil.setEffect(glow);

        }
        else if(imagem_da_app.isHover()){

            imagem_da_app.setEffect(glow);

        }
        else if(logout_button.isHover()){

            logout_button.setEffect(glow);

        }
        else if(texto_sugestoes.isHover()){

            texto_sugestoes.setEffect(glow);

        }
        else if(texto_post.isHover()){

            texto_post.setEffect(glow);
        }else if(escrever.isHover() || escrever_icon.isHover()){
            escrever_icon.setEffect(glow);
            escrever.setEffect(glow);
        }else if(voltar.isHover()){

            voltar.setEffect(glow);
        }
        else if(texto_lista.isHover()){
            texto_lista.setEffect(glow);
        }
        else{
            Loggedusername.setEffect(null);
            text_menu.setEffect(null);
            text_meuperfil.setEffect(null);
            imagem_da_app.setEffect(null);
            logout_button.setEffect(null);
            texto_sugestoes.setEffect(null);
            texto_post.setEffect(null);
            FotoDePerfil.setEffect(null);
            voltar.setEffect(null);
            escrever.setEffect(null);
            escrever_icon.setEffect(null);
            texto_lista.setEffect(null);
        }



    }

    public void go_to_lista_amigos() throws IOException{
        this.changeScene("Lista_Amigos.fxml", 1200, 800);
    }
}