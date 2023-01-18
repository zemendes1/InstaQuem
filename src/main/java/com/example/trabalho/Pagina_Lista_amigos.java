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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


import static com.example.trabalho.Amigos.check_amizade;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.utilizadorProcurado;

public class Pagina_Lista_amigos extends Page implements Initializable{

    ArrayList<String> words = new ArrayList<>();

    @FXML
    private ListView<String> SearchList = new ListView<>();

    @FXML
    private TextField SearchBar;

    public String utilizador_selecionado;

    @FXML
    private  ImageView FotoDePerfil1 = new ImageView() ;

    @FXML
    private  Label Loggedusername1 = new Label();

    //glow
    final Glow glow = new Glow(1);

    final Glow glow_foto = new Glow();
    @FXML
    private  ImageView imagem_da_app;
    @FXML
    private Text logout_button,text_meuperfil,text_menu,texto_sugestoes,texto_post;

    @FXML
    private VBox Vbox_amigos;

    @FXML
    private HBox box_notificacoes;

    @FXML
    private Label Mensagem_Erro_Amig;

    @FXML
    private Text texto_lista_a;

    public static int numero_amigos=0;

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

    private List<Caixa_amizade> amizades_estabelecidas(){


        List<Caixa_amizade> ls = new ArrayList<>();


        for(int i = 0; i < LoggedUser.ListaAmigos.size(); i++){

            Caixa_amizade amig = new Caixa_amizade();

            amig.setImagem_user(LoggedUser.ListaAmigos.get(i).getPic());
            amig.setUser_pedido(LoggedUser.ListaAmigos.get(i).getUsername());
            ls.add(amig);

        }

        return ls;

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


        Loggedusername1.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic()+".png");
        FotoDePerfil1.setImage(image1);


        //nr de noitficacoes
        numero_amigos=0;

        for(User user : teste){
            if(check_amizade(LoggedUser,user)==3){//Se sao amigos
                numero_amigos++;
            }
        }



        //barra de notificacoes
        if(numero_amigos!=0){
            this.Mensagem_Erro_Amig.setVisible(false);
            this.box_notificacoes.setVisible(true);
            List <Caixa_amizade> nots = new ArrayList<>(amizades_estabelecidas());
            for (Caixa_amizade not : nots) {
                FXMLLoader fxmlLoadernot = new FXMLLoader();
                fxmlLoadernot.setLocation(getClass().getResource("Amizade.fxml"));

                try {
                    HBox hBox = fxmlLoadernot.load();
                    Caixa_amizade_controller cic = fxmlLoadernot.getController();
                    cic.setData(not);
                    Vbox_amigos.getChildren().add(hBox);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        else{
            this.Mensagem_Erro_Amig.setVisible(true);
            this.box_notificacoes.setVisible(false);
        }
    }





    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }



    public void userLogout() throws IOException{
        this.changeScene("hello-view.fxml", 600, 400);
    }

    public void go_to_PaginaPrincipal() throws IOException{
        this.changeScene("pagina_principal.fxml", 1200, 800);
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

    public void go_to_suggestions() throws IOException{
        this.changeScene("Sugestoes.fxml", 1200, 800);
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
        }
        else if(texto_lista_a.isHover()){
            texto_lista_a.setEffect(glow);
        }
        else {
            text_menu.setEffect(null);
            text_meuperfil.setEffect(null);
            imagem_da_app.setEffect(null);
            logout_button.setEffect(null);
            texto_sugestoes.setEffect(null);
            texto_post.setEffect(null);
            FotoDePerfil1.setEffect(null);
            Loggedusername1.setEffect(null);
            texto_lista_a.setEffect(null);

        }
    }
}