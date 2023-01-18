package com.example.trabalho;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.*;

public class Perfil_Outro_User extends Page implements Initializable { //_pou significa perfil outro utilizador

    ArrayList<String> words_pou = new ArrayList<>();

    Image icone_do_admin = new Image("admin.png");

    Image icone_de_mod = new Image("mod.png");

    final Glow glow_pou = new Glow();

    final Glow glow_pou2 = new Glow();

    final Glow glow_foto_pou = new Glow();

    DropShadow dropShadow = new DropShadow();

    @FXML
    private ListView<String> SearchList_pou = new ListView<>();
    @FXML
    private Label DescricaoUser = new Label();
    @FXML
    private TextField SearchBar_pou;
    @FXML
    private  ImageView FotoDePerfil_pou = new ImageView() ;
    @FXML
    private  Label Loggedusername_pou = new Label();
    @FXML
    private  ImageView FotoUtilizadorSelecionado;
    @FXML
    private ImageView icone_mod;
    @FXML
    private ImageView mod_options;
    @FXML
    private  Label NomeUtilizadorSelecionado;
    public String utilizador_selecionado_pou;
    @FXML
    private Button butao_do_perfil;
    @FXML
    private Button butao_aceitar;
    @FXML
    private Button butao_remover;
    @FXML
    private GridPane postGrid1;
    @FXML
    private Label text_options;
    @FXML
    private Label nr_amigos;

    @FXML
    private Text text_menu_pou;

    @FXML
    private Text text_meuperfil_pou;

    @FXML
    private Text logout_button;

    @FXML
    private Text texto_sugestoes_pou;

    @FXML
    private Text texto_post_pou;

    @FXML
    private ImageView imagem_da_app_pou;

    @FXML
    private Text texto_amigos_pou;


    @FXML
    public void Action_Search_Appear() {
        if(SearchBar_pou.getText().isEmpty()) {
            SearchList_pou.setVisible(false);
        }
        else {
            SearchList_pou.setVisible(true);
            SearchList_pou.getItems().clear();
            SearchList_pou.getItems().addAll(searchList(SearchBar_pou.getText(),words_pou));
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        glow_pou.setLevel(1.0);
        glow_pou2.setLevel(0.5);

        ArrayList<User> bong = Login.getUserList();
        for(User user : bong){
            words_pou.add(user.getUsername());

        }

        SearchList_pou.getItems().addAll(words_pou);
        //selecionar outro perfil
        SearchList_pou.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> utilizador_selecionado_pou = SearchList_pou.getSelectionModel().getSelectedItem());


        Loggedusername_pou.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic()+".png");
        FotoDePerfil_pou.setImage(image1);

        NomeUtilizadorSelecionado.setText(Utilizador_POU.getUsername());
        Image image2 = new Image(Utilizador_POU.getPic()+".png");
        FotoUtilizadorSelecionado.setImage(image2);


        DescricaoUser.setText(Utilizador_POU.getDescricao());

        //icone mod_options
        if((LoggedUser.AdminKey && !LoggedUser.getUsername().equals(Utilizador_POU.getUsername()) ) ||(LoggedUser.ModKey && !LoggedUser.getUsername().equals(Utilizador_POU.getUsername()) && !Utilizador_POU.ModKey && !Utilizador_POU.AdminKey)){
            mod_options.setDisable(false);
            mod_options.setVisible(true);
            text_options.setDisable(false);
            text_options.setVisible(true);
            text_options.setText("Opções de Moderação");
        }
        else{
            mod_options.setDisable(true);
            mod_options.setVisible(false);
            text_options.setDisable(true);
            text_options.setVisible(false);
            text_options.setText("");
        }

        //icone moderador
        if(Utilizador_POU.AdminKey){
            icone_mod.setImage(icone_do_admin);
            icone_mod.setVisible(true);
        }else{
            icone_mod.setImage(icone_de_mod);
            icone_mod.setVisible(Utilizador_POU.ModKey);
        }

        //botoes
        if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){

            butao_aceitar.setOpacity(0);
            butao_remover.setOpacity(0);
            butao_aceitar.setDisable(true);
            butao_remover.setDisable(true);
            butao_do_perfil.setText("Editar");
        }
        else {

            switch (Amigos.check_amizade(Utilizador_POU, LoggedUser)) {
                case 0 -> {
                    butao_do_perfil.setText("Adicionar Amizade");
                    butao_aceitar.setOpacity(0);
                    butao_remover.setOpacity(0);

                }
                case 1 -> {
                    butao_do_perfil.setText("Cancelar Pedido");
                    butao_aceitar.setOpacity(0);
                    butao_remover.setOpacity(0);
                }
                case 2 -> {
                    butao_do_perfil.setOpacity(0);
                    butao_aceitar.setOpacity(1);
                    butao_remover.setOpacity(1);
                    butao_do_perfil.setDisable(true);
                    butao_aceitar.setDisable(false);
                    butao_remover.setDisable(false);
                }
                case 3 -> {
                    butao_do_perfil.setText("Remover Amizade");
                    butao_aceitar.setOpacity(0);
                    butao_remover.setOpacity(0);
                }
            }
        }
        List<Post> posts = new ArrayList<>(Utilizador_POU.TodosOsPosts);
        int collumns = 0; int rows=1;
        for (Post post : posts) {
            post.setProfileImgSrc(Objects.requireNonNull(getUser(post.getUsername())).ProfilePic);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Publicacao.fxml"));
            try {
                VBox postBox = fxmlLoader.load();

                Publicacao postcontroller = fxmlLoader.getController();
                postcontroller.SetData(post);

                if (collumns == 1) {
                    collumns = 0;
                    ++rows;
                }

                postGrid1.add(postBox, collumns++, rows);
                //GridPane.setMargin(postBox, new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        int amigos_comum=0;

        //nr de amigos
        if(LoggedUser == Utilizador_POU){
            if(Utilizador_POU.ListaAmigos.size()==1) {nr_amigos.setText(Utilizador_POU.ListaAmigos.size()+" Amigo");}
            else{nr_amigos.setText(Utilizador_POU.ListaAmigos.size()+" Amigos");}
        }
        else{
            for(int i=0 ; i<Utilizador_POU.ListaAmigos.size() ; i++){
               if( LoggedUser.ListaAmigos.contains(Utilizador_POU.ListaAmigos.get(i))){amigos_comum++;}
            }
            if(Utilizador_POU.ListaAmigos.size()==1) {nr_amigos.setText(Utilizador_POU.ListaAmigos.size()+" Amigo  "+amigos_comum+" em Comum");}
            else{nr_amigos.setText(Utilizador_POU.ListaAmigos.size()+" Amigos  "+amigos_comum+" em Comum");}
        }

    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word ->
                input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }

    public void mandar_remover_pedido () throws IOException {

        if(!LoggedUser.getUsername().equals(Utilizador_POU.getUsername())) {

            Amigos mandar = new Amigos();

            switch (Amigos.check_amizade(Utilizador_POU, LoggedUser)) {
                case 0 -> {
                    Amigos.envia_pedido(Utilizador_POU, LoggedUser);
                    butao_do_perfil.setText("Cancelar Pedido");

                }

                case 1 -> {
                    mandar.cancela_pedido(Utilizador_POU, LoggedUser);
                    butao_do_perfil.setText("Adicionar Amizade");

                }

                case 3 -> {
                    mandar.remove_amizade(Utilizador_POU, LoggedUser);
                    butao_do_perfil.setText("Adicionar Amizade");
                }

            }
        }

        else{

            this.changeScene("Editar_Perfil.fxml", 1200, 800);

        }

    }

    public void resposta_ao_pedido_adicionar() throws IOException {
        Amigos resposta_do_pedido = new Amigos();

        resposta_do_pedido.aceita_pedido(Utilizador_POU,LoggedUser);

        butao_do_perfil.setText("Remover Amizade");
        butao_do_perfil.setOpacity(1);
        butao_aceitar.setOpacity(0);
        butao_remover.setOpacity(0);
        butao_do_perfil.setDisable(false);
        butao_aceitar.setDisable(true);
        butao_remover.setDisable(true);

        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void resposta_ao_pedido_remover() throws IOException {
        Amigos resposta_do_pedido = new Amigos();

        resposta_do_pedido.rejeita_pedido(Utilizador_POU,LoggedUser);

        butao_do_perfil.setText("Adicionar Amizade");
        butao_do_perfil.setOpacity(1);
        butao_aceitar.setOpacity(0);
        butao_remover.setOpacity(0);
        butao_do_perfil.setDisable(false);
        butao_aceitar.setDisable(true);
        butao_remover.setDisable(true);

        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_escreverposts() throws IOException{
        this.changeScene("Escrever_posts.fxml", 1200, 800);
    }

    public void go_to_suggestions() throws IOException{
        this.changeScene("Sugestoes.fxml", 1200, 800);
    }
    public void userLogout() throws IOException{
        this.changeScene("hello-view.fxml", 600, 400);
    }

    public void go_to_outro_user() throws IOException{

        if(utilizador_selecionado_pou != null && utilizador_selecionado_pou.equals(LoggedUser.getUsername())){
            utilizadorProcurado(getUser (utilizador_selecionado_pou));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }

        else if(utilizador_selecionado_pou != null && !utilizador_selecionado_pou.equals(LoggedUser.getUsername())) {
            utilizadorProcurado(getUser (utilizador_selecionado_pou));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }

    }

    public void go_to_logged_user() throws IOException{

        utilizadorProcurado(LoggedUser);
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);


    }

    public void go_to_PaginaPrincipal() throws IOException{
        this.changeScene("pagina_principal.fxml", 1200, 800);
    }

    public void go_to_ModOptions() throws IOException{
        this.changeScene("Mod_Options.fxml", 1200, 800);
    }

    public void go_to_lista_amigos() throws IOException{
        this.changeScene("Lista_Amigos.fxml", 1200, 800);
    }


    @FXML
    public void mouse_in_perfil_user (){

        dropShadow.setOffsetY(3.0);
        dropShadow.setOffsetX(3.0);

        if(butao_do_perfil.isHover()){
            butao_do_perfil.setEffect(dropShadow);
        }

        else if(butao_aceitar.isHover()){
            butao_aceitar.setEffect(dropShadow);
        }

        else if(butao_remover.isHover()){
            butao_remover.setEffect(dropShadow);
        }


        else if (Loggedusername_pou.isHover() || FotoDePerfil_pou.isHover()){

            Loggedusername_pou.setEffect(glow_pou);
            FotoDePerfil_pou.setEffect(glow_foto_pou);

        }
        else if(text_menu_pou.isHover()){

            text_menu_pou.setEffect(glow_pou);

        }
        else if(text_meuperfil_pou.isHover()){

            text_meuperfil_pou.setEffect(glow_pou);

        }
        else if(imagem_da_app_pou.isHover()){

            imagem_da_app_pou.setEffect(glow_pou);

        }
        else if(this.logout_button.isHover()){

            this.logout_button.setEffect(glow_pou);

        }
        else if(texto_sugestoes_pou.isHover()){

            texto_sugestoes_pou.setEffect(glow_pou);

        }
        else if(texto_post_pou.isHover()){

            texto_post_pou.setEffect(glow_pou);
        }
        else if(mod_options.isHover() || text_options.isHover()){

            mod_options.setEffect(glow_pou2);
            text_options.setEffect(glow_pou2);
        }
        else if(texto_amigos_pou.isHover()){
            texto_amigos_pou.setEffect(glow_pou);
        }
        else{

            Loggedusername_pou.setEffect(null);
            text_menu_pou.setEffect(null);
            text_meuperfil_pou.setEffect(null);
            imagem_da_app_pou.setEffect(null);
            this.logout_button.setEffect(null);
            texto_sugestoes_pou.setEffect(null);
            texto_post_pou.setEffect(null);
            FotoDePerfil_pou.setEffect(null);
            mod_options.setEffect(null);
            text_options.setEffect(null);
            butao_do_perfil.setEffect(null);
            butao_aceitar.setEffect(null);
            butao_remover.setEffect(null);
            texto_amigos_pou.setEffect(null);

        }



    }


}

