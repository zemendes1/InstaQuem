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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.*;


public class EditarPerfil extends Page implements Initializable {

    ArrayList<String> words_EP = new ArrayList<>();
    @FXML
    private ListView<String> SearchList = new ListView<>();
    @FXML
    private TextField SearchBar;
    @FXML
    private ImageView FotoDePerfil = new ImageView() ;
    @FXML
    private  Label Loggedusername = new Label();
    public String utilizador_selecionado_EP;
    @FXML
    private PasswordField ConfirmarPassword;
    @FXML
    private TextArea NovaDescricao;
    @FXML
    private PasswordField PasswordNova;
    @FXML
    private Label DescricaoAlterada , PasswordAlterada;

    @FXML
    private ImageView FotoOpcao1,FotoOpcao2,FotoOpcao3,FotoOpcao4,FotoOpcao5,FotoOpcao6, FotoOpcao0 = new ImageView() ;
    @FXML
    private ImageView Circulo1,Circulo2,Circulo3,Circulo4,Circulo5,Circulo6,Circulo0 = new ImageView() ;

    private int AuxFoto=7;

    @FXML
    private Button butaomudar,botao_apagar_perfil,botao_mudar_foto,botao_mudar_descricao;

    final Glow glow = new Glow(1);

    final Glow glow_foto = new Glow();
    @FXML
    private  ImageView imagem_da_app,image_voltar;
    @FXML
    private Text logout_button,text_meuperfil,text_menu,texto_sugestoes,texto_post, texto_amigos_ed;
    DropShadow dropShadow = new DropShadow();


    @FXML
    public void Action_Search_Appear(){
        if(SearchBar.getText().isEmpty()) {
            SearchList.setVisible(false);
        }
        else {
            SearchList.setVisible(true);
            SearchList.getItems().clear();
            SearchList.getItems().addAll(searchList(SearchBar.getText(),words_EP));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //lista de pesquisa
        ArrayList<User> bing = Login.getUserList();
        for(User user : bing){
            words_EP.add(user.getUsername());

        }
        SearchList.getItems().addAll(words_EP);
        SearchList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> utilizador_selecionado_EP = SearchList.getSelectionModel().getSelectedItem());

        //Foto de perfil + user name do user logado
        Loggedusername.setText(LoggedUser.getUsername());
        Image image1 = new Image(LoggedUser.getPic()+".png");
        FotoDePerfil.setImage(image1);

        //opcões de foto
        Image opcao1 = new Image("1.png");
        FotoOpcao1.setImage(opcao1);
        Image opcao2 = new Image("2.png");
        FotoOpcao2.setImage(opcao2);
        Image opcao3 = new Image("3.png");
        FotoOpcao3.setImage(opcao3);
        Image opcao4 = new Image("4.png");
        FotoOpcao4.setImage(opcao4);
        Image opcao5 = new Image("5.png");
        FotoOpcao5.setImage(opcao5);
        Image opcao6 = new Image("6.png");
        FotoOpcao6.setImage(opcao6);
        Image opcao0 = new Image("0.png");
        FotoOpcao0.setImage(opcao0);

        if(LoggedUser.AdminKey){
            botao_apagar_perfil.setVisible(false);
            botao_apagar_perfil.setDisable(true);
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

        if (utilizador_selecionado_EP != null && !utilizador_selecionado_EP.equals(LoggedUser.getUsername())) {
            utilizadorProcurado(getUser (utilizador_selecionado_EP));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }

        else if(utilizador_selecionado_EP != null && utilizador_selecionado_EP.equals(LoggedUser.getUsername())){
            utilizadorProcurado(getUser (utilizador_selecionado_EP));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }

    }

    public void go_to_logged_user() throws IOException{

        utilizadorProcurado(LoggedUser);
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);


    }

    public void go_back() throws IOException{

        if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){
            utilizadorProcurado(LoggedUser);
        }
        else{
            utilizadorProcurado(Utilizador_POU);
        }

        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);

    }


    public void go_to_escreverposts() throws IOException{
        this.changeScene("Escrever_posts.fxml", 1200, 800);
    }

    public void go_to_PaginaPrincipal() throws IOException{
        this.changeScene("pagina_principal.fxml", 1200, 800);
    }

    public void MudarPassword() {
        if(PasswordNova.getText().isEmpty()){
            PasswordAlterada.setText("Campo não preenchido");
        }
        else if(!PasswordNova.getText().equals(ConfirmarPassword.getText())){
            PasswordAlterada.setText("As Passwords não coincidem");
        }
        else if(PasswordNova.getLength()<7){
            PasswordAlterada.setText("Password demasiado curta");
        }
        else {
            if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){
                LoggedUser.setPassword(PasswordNova.getText());
            }
            else{
                Utilizador_POU.setPassword(PasswordNova.getText());
            }
            PasswordAlterada.setText("Password alterada com sucesso.");
            PasswordNova.setText("");ConfirmarPassword.setText("");
        }
    }
    public void MudarDescricao() {
        if(NovaDescricao.getText().isEmpty()){
            DescricaoAlterada.setText("Campo não preenchido");
        }
        else if (NovaDescricao.getText().equals((LoggedUser.getDescricao()))){
            DescricaoAlterada.setText("Nova descrição igual à atual");
        }
        else if (NovaDescricao.getLength()>30){
            DescricaoAlterada.setText("Descrição demasiado longa");
        }
        else{
            if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){
                LoggedUser.setDescricao(NovaDescricao.getText());
            }
            else{
                Utilizador_POU.setDescricao(NovaDescricao.getText());
            }

            DescricaoAlterada.setText("Descrição alterada com sucesso.");
            NovaDescricao.setText("");
        }
    }
    public void Pfp1() {
        Circulo1.setVisible(true);
        Circulo2.setVisible(false);
        Circulo3.setVisible(false);
        Circulo4.setVisible(false);
        Circulo5.setVisible(false);
        Circulo6.setVisible(false);
        Circulo0.setVisible(false);
        AuxFoto=1;
    }
    public void Pfp2(){
        Circulo1.setVisible(false);
        Circulo2.setVisible(true);
        Circulo3.setVisible(false);
        Circulo4.setVisible(false);
        Circulo5.setVisible(false);
        Circulo6.setVisible(false);
        Circulo0.setVisible(false);
        AuxFoto=2;
    }

    public void Pfp3() {
        Circulo1.setVisible(false);
        Circulo2.setVisible(false);
        Circulo3.setVisible(true);
        Circulo4.setVisible(false);
        Circulo5.setVisible(false);
        Circulo6.setVisible(false);
        Circulo0.setVisible(false);
        AuxFoto=3;
    }

    public void Pfp4(){
        Circulo1.setVisible(false);
        Circulo2.setVisible(false);
        Circulo3.setVisible(false);
        Circulo4.setVisible(true);
        Circulo5.setVisible(false);
        Circulo6.setVisible(false);
        Circulo0.setVisible(false);
        AuxFoto=4;
    }

    public void Pfp5(){
        Circulo1.setVisible(false);
        Circulo2.setVisible(false);
        Circulo3.setVisible(false);
        Circulo4.setVisible(false);
        Circulo5.setVisible(true);
        Circulo6.setVisible(false);
        Circulo0.setVisible(false);
        AuxFoto=5;
    }

    public void Pfp6(){
        Circulo1.setVisible(false);
        Circulo2.setVisible(false);
        Circulo3.setVisible(false);
        Circulo4.setVisible(false);
        Circulo5.setVisible(false);
        Circulo6.setVisible(true);
        Circulo0.setVisible(false);
        AuxFoto=6;
    }
    public void Pfp0() {
        Circulo1.setVisible(false);
        Circulo2.setVisible(false);
        Circulo3.setVisible(false);
        Circulo4.setVisible(false);
        Circulo5.setVisible(false);
        Circulo6.setVisible(false);
        Circulo0.setVisible(true);
        AuxFoto=0;
    }
    public void MudarFotoPerfil() throws IOException{
        if(AuxFoto>=0 && AuxFoto<=6){

            if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){
                LoggedUser.setPic(AuxFoto);
            }
            else{
                Utilizador_POU.setPic(AuxFoto);
                }
            }

            Circulo1.setVisible(false);
            Circulo2.setVisible(false);
            Circulo3.setVisible(false);
            Circulo4.setVisible(false);
            Circulo5.setVisible(false);
            Circulo6.setVisible(false);
            Circulo0.setVisible(false);
            AuxFoto=7;
            this.changeScene("Editar_Perfil.fxml", 1200, 800); //Tem mesmo de dar refresh para atualizar as fotos
    }

    @FXML
    void AbrirPopUp() throws IOException{
        this.changeScene("ApagarPerfilPopUp.fxml",1200,800);
    }

    public void go_to_lista_amigos() throws IOException{
        this.changeScene("Lista_Amigos.fxml", 1200, 800);
    }


    @FXML
    public void mouse_in () {

        if (Loggedusername.isHover() || FotoDePerfil.isHover()) {
            Loggedusername.setEffect(glow);
            FotoDePerfil.setEffect(glow_foto);

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
        } else if (botao_apagar_perfil.isHover()) {

            botao_apagar_perfil.setEffect(dropShadow);
        } else if (botao_mudar_descricao.isHover()) {

            botao_mudar_descricao.setEffect(dropShadow);
        } else if (botao_mudar_foto.isHover()) {

            botao_mudar_foto.setEffect(dropShadow);
        } else if (butaomudar.isHover()) {

            butaomudar.setEffect(dropShadow);
        } else if (FotoOpcao1.isHover()) {

            FotoOpcao1.setEffect(glow_foto);
        } else if (FotoOpcao2.isHover()) {

            FotoOpcao2.setEffect(glow_foto);
        } else if (FotoOpcao3.isHover()) {

            FotoOpcao3.setEffect(glow_foto);
        } else if (FotoOpcao4.isHover()) {

            FotoOpcao4.setEffect(glow_foto);
        } else if (FotoOpcao5.isHover()) {

            FotoOpcao5.setEffect(glow_foto);
        } else if (FotoOpcao6.isHover()) {

            FotoOpcao6.setEffect(glow_foto);
        } else if (FotoOpcao0.isHover()) {

            FotoOpcao0.setEffect(glow_foto);
        }
        else if(texto_amigos_ed.isHover()){
            texto_amigos_ed.setEffect(glow);
        }
        else {
            Loggedusername.setEffect(null);
            text_menu.setEffect(null);
            text_meuperfil.setEffect(null);
            imagem_da_app.setEffect(null);
            logout_button.setEffect(null);
            texto_sugestoes.setEffect(null);
            texto_post.setEffect(null);
            FotoDePerfil.setEffect(null);
            texto_amigos_ed.setEffect(null);

            botao_apagar_perfil.setEffect(null);
            botao_mudar_descricao.setEffect(null);
            botao_mudar_foto.setEffect(null);
            butaomudar.setEffect(null);
            image_voltar.setEffect(null);
            FotoOpcao0.setEffect(null);
            FotoOpcao1.setEffect(null);
            FotoOpcao2.setEffect(null);
            FotoOpcao3.setEffect(null);
            FotoOpcao4.setEffect(null);
            FotoOpcao5.setEffect(null);
            FotoOpcao6.setEffect(null);

        }
    }


}

