package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.trabalho.PaginaPrincipal.ls;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.utilizadorProcurado;

public class EscreverPosts extends Page implements Initializable {

    ArrayList<String> words_ep = new ArrayList<>();
    @FXML
    private ListView<String> SearchList_ep = new ListView<>();
    @FXML
    private TextField SearchBar_ep;
    @FXML
    private TextArea poststring;
    @FXML
    private ImageView FotoDePerfil_ep = new ImageView() ;
    @FXML
    private  Label Loggedusername_ep = new Label();
    @FXML
    private  Label mensagem = new Label();
    public String utilizador_selecionado_ep;
    final Glow glow = new Glow(1);

    final Glow glow_foto = new Glow();
    @FXML
    private  ImageView imagem_da_app;
    @FXML
    private Text logout_button,text_meuperfil,text_menu,texto_sugestoes,texto_post,texto_amigos_po;
    @FXML
    private Button botao_post,botao_voltar;
    DropShadow dropShadow = new DropShadow();


    @FXML
    public void Action_Search_Appear(){
        if(SearchBar_ep.getText().isEmpty()) {
            SearchList_ep.setVisible(false);
        }
        else {
            SearchList_ep.setVisible(true);
            SearchList_ep.getItems().clear();
            SearchList_ep.getItems().addAll(searchList(SearchBar_ep.getText(),words_ep));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<User> bing = Login.getUserList();
        for(User user : bing){
            words_ep.add(user.getUsername());

        }

        SearchList_ep.getItems().addAll(words_ep);

        //selecionar outro perfil
        SearchList_ep.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> utilizador_selecionado_ep = SearchList_ep.getSelectionModel().getSelectedItem());

        Loggedusername_ep.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic()+".png");
        FotoDePerfil_ep.setImage(image1);


    }

    public void GuardarPost() throws IOException{
        String Texto = poststring.getText();
        int Tamanho = poststring.getLength();

        if (Tamanho <1 && !LoggedUser.silenciado){
            mensagem.setText("Impossível publicar um post vazio.");

        }
        else if (Tamanho>1500 && !LoggedUser.silenciado){
            mensagem.setText("Ultrapassou o limite de 1500 caracteres.");
        }
        else if (!LoggedUser.silenciado){
            Post post =new Post();

            post.setProfileImgSrc(LoggedUser.getPic());
            post.setText(Texto);
            post.setDate(getDate());
            post.setUsername(LoggedUser.getUsername());
            post.setNbLikes("0");
            post.setNbComments("0");
            post.setIndex(ls.size());

            ls.add(post);
            LoggedUser.TodosOsPosts.add(post);
            this.changeScene("pagina_principal.fxml", 1200, 800);
        }
        else {
            mensagem.setText("Não tem permissões para publicar!");
        }
    }

    private String getDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        return dateFormat.format(date);
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
        if (utilizador_selecionado_ep != null && !utilizador_selecionado_ep.equals(LoggedUser.getUsername())) {
            utilizadorProcurado(getUser (utilizador_selecionado_ep));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }
    }
    public void go_to_pprincipal() throws IOException{
        this.changeScene("pagina_principal.fxml", 1200, 800);
    }

    public void go_to_logged_user() throws IOException{
        utilizadorProcurado(LoggedUser);
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_escreverposts() throws IOException{
        this.changeScene("Escrever_posts.fxml", 1200, 800);
    }

    public void go_to_lista_amigos() throws IOException{
        this.changeScene("Lista_Amigos.fxml", 1200, 800);
    }


    @FXML
    public void mouse_in (){
        dropShadow.setOffsetY(3.0);
        dropShadow.setOffsetX(3.0);


        if (Loggedusername_ep.isHover() || FotoDePerfil_ep.isHover()){

            Loggedusername_ep.setEffect(glow);
            FotoDePerfil_ep.setEffect(glow_foto);

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
        }
        else if(botao_post.isHover()){

            botao_post.setEffect(dropShadow);

        }
        else if(botao_voltar.isHover()){

            botao_voltar.setEffect(dropShadow);

        }
        else if(texto_amigos_po.isHover()){
            texto_amigos_po.setEffect(glow);
        }
        else{
            Loggedusername_ep.setEffect(null);
            text_menu.setEffect(null);
            text_meuperfil.setEffect(null);
            imagem_da_app.setEffect(null);
            logout_button.setEffect(null);
            texto_sugestoes.setEffect(null);
            texto_post.setEffect(null);
            FotoDePerfil_ep.setEffect(null);
            botao_voltar.setEffect(null);
            botao_post.setEffect(null);
            texto_amigos_po.setEffect(null);
        }
    }

}
