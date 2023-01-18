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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.*;


public class  ModOptions extends Page implements Initializable {

    @FXML
    private ImageView FotoDePerfil_pou;
    @FXML
    private Label Loggedusername_pou;
    @FXML
    private TextField SearchBar_pou;
    @FXML
    private ListView<String> SearchList_pou;
    @FXML
    private Button botao_admin,butao_do_perfil_admin ,botao_silencio;

    public String utilizador_selecionado_pou;
    ArrayList<String> words_pou = new ArrayList<>();
    private  String NomeUtilizadorPorEditar;

    final Glow glow = new Glow(1);

    final Glow glow_foto = new Glow();
    @FXML
    private  ImageView imagem_da_app,image_voltar;
    @FXML
    private Text logout_button,text_meuperfil,text_menu,texto_sugestoes,texto_post,texto_amigos_m;
    DropShadow dropShadow = new DropShadow();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<User> bong = Login.getUserList();
        for (User user : bong) {
            words_pou.add(user.getUsername());

        }

        SearchList_pou.getItems().addAll(words_pou);
        //selecionar outro perfil
        SearchList_pou.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> utilizador_selecionado_pou = SearchList_pou.getSelectionModel().getSelectedItem());


        Loggedusername_pou.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic() + ".png");
        FotoDePerfil_pou.setImage(image1);

        NomeUtilizadorPorEditar=Utilizador_POU.getUsername();


        if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){
            butao_do_perfil_admin.setVisible(false);
            butao_do_perfil_admin.setDisable(true);
        }
        else {

            if(LoggedUser.AdminKey || (LoggedUser.ModKey && !Utilizador_POU.AdminKey && !Utilizador_POU.ModKey)){
                botao_admin.setOpacity(1);
                botao_admin.setDisable(false);
                butao_do_perfil_admin.setVisible(true);
                butao_do_perfil_admin.setDisable(false);
                botao_silencio.setVisible(true);
                botao_silencio.setDisable(false);

                if(Utilizador_POU.ModKey){
                    botao_admin.setText("Despromover de Moderador");
                }
                else{
                    botao_admin.setText("Promover para Moderador");
                }

                if(Utilizador_POU.silenciado){
                    botao_silencio.setText("Desativar Silenciar");
                }
                else{
                    botao_silencio.setText("Silenciar Utilizador");
                }

            }
            else{
                botao_admin.setOpacity(0);
                botao_admin.setDisable(true);
                botao_silencio.setVisible(false);
                botao_silencio.setDisable(true);

            }

        }


    }



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

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word ->
                input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }

    public void go_to_edita_perfil_como_admin() throws IOException {
        this.changeScene("Editar_Perfil.fxml", 1200, 800);
    }

    public void go_to_escreverposts() throws IOException{
        this.changeScene("Escrever_posts.fxml", 1200, 800);
    }

    public void go_to_logged_user() throws IOException{
        utilizadorProcurado(LoggedUser);
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_PaginaPrincipal() throws IOException{
        this.changeScene("pagina_principal.fxml", 1200, 800);
    }

    public void promover_a_admin() throws IOException{

        Utilizador_POU.ModKey= !Utilizador_POU.ModKey;

        if(Utilizador_POU.ModKey){
            if(LoggedUser.AdminKey){
                botao_admin.setText("Despromover de Moderador");
            }
            else if(LoggedUser.ModKey){
                botao_admin.setDisable(true);
                botao_admin.setOpacity(0);
                botao_admin.setText("Despromover de Moderador");
            }
        }
        else{
            botao_admin.setText("Promover para Moderador");
        }
        utilizadorProcurado(getUser ( NomeUtilizadorPorEditar));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }


    public void go_to_suggestions() throws IOException{
        this.changeScene("Sugestoes.fxml", 1200, 800);
    }

    public void userLogout() throws IOException{
        this.changeScene("hello-view.fxml", 600, 400);
    }

    public void go_Back() throws IOException{
        utilizadorProcurado(getUser ( NomeUtilizadorPorEditar));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    public void go_to_lista_amigos() throws IOException{
        this.changeScene("Lista_Amigos.fxml", 1200, 800);
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


    public void Silenciar_User() {

        if(!Utilizador_POU.silenciado){
            Utilizador_POU.silenciado = true;
            botao_silencio.setText("Desativar Silenciar");
        }
        else{
            Utilizador_POU.silenciado = false;
            botao_silencio.setText("Silenciar Utilizador");
        }

    }

    @FXML
    public void mouse_in () {

        if (Loggedusername_pou.isHover() || FotoDePerfil_pou.isHover()) {
            Loggedusername_pou.setEffect(glow);
            FotoDePerfil_pou.setEffect(glow_foto);

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
        } else if (image_voltar.isHover()) {

            image_voltar.setEffect(glow);
        } else if (botao_admin.isHover()) {

            botao_admin.setEffect(dropShadow);
        } else if (butao_do_perfil_admin.isHover()) {

            butao_do_perfil_admin.setEffect(dropShadow);
        } else if (botao_silencio.isHover()) {

            botao_silencio.setEffect(dropShadow);
        }
        else if(texto_amigos_m.isHover()){
            texto_amigos_m.setEffect(glow);
        }
        else {
            Loggedusername_pou.setEffect(null);
            text_menu.setEffect(null);
            text_meuperfil.setEffect(null);
            imagem_da_app.setEffect(null);
            logout_button.setEffect(null);
            texto_sugestoes.setEffect(null);
            texto_post.setEffect(null);
            FotoDePerfil_pou.setEffect(null);
            texto_amigos_m.setEffect(null);
            botao_admin.setEffect(null);
            butao_do_perfil_admin.setEffect(null);
            botao_silencio.setEffect(null);
            image_voltar.setEffect(null);


        }
    }
}
