package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.Objects;

import static com.example.trabalho.PaginaPrincipal.*;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.utilizadorProcurado;

public class Publicacao extends Page {


    final Glow glow_post = new Glow();


    @FXML
    private ImageView FotoDePerfilPost;
    @FXML
    private TextArea TextoDoPost;
    @FXML
    private Label UsernamePost;
    @FXML
    private Label likes;
    @FXML
    private Label date;
    @FXML
    private Label comments;
    @FXML
    private ImageView caixote;

    @FXML
    private ImageView image_like;

    @FXML
    private ImageView image_comment;
    Post post1 = new Post();
    public static int SelectedPostIndex;



    public void SetData(Post post) {
        post1 = post;
        Image image = new Image(post.getProfileImgSrc() + ".png");
        FotoDePerfilPost.setImage(image);
        UsernamePost.setText(post.getUsername());
        TextoDoPost.setText(post.getText());
        date.setText(post.getDate());
        likes.setText(post.getNbLikes());
        comments.setText(post.getNbComments());
        caixote.setVisible(pode_apagar());
    }


    public void Liker() {
        post1.GiveLike(post1);
        likes.setText((post1.getNbLikes()));
    }

    public void Delete() throws IOException {
        if (pode_apagar()) {
            ls.remove(post1);
            Objects.requireNonNull(getUser(post1.getUsername())).TodosOsPosts.remove(post1);
            this.changeScene("pagina_principal.fxml", 1200, 800);
        }

    }

    public void go_to_outro_user() throws IOException {
        if (post1.getUsername() != null) {
            utilizadorProcurado(getUser(post1.getUsername()));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }
    }

    public void go_to_comments() throws IOException {
        if (!CurrentScene.equals("pagina_comentario.fxml")) {
            SelectedPostIndex = post1.getIndex();
            this.changeScene("pagina_comentario.fxml", 1200, 800);
        }
    }

    public boolean pode_apagar() {
        if (LoggedUser.AdminKey) {
            return true;
        } // admin apaga um post
        else if (post1.getUsername().equals(LoggedUser.getUsername())) {
            return true;
        }//utilizador apaga o próprio post
        else return LoggedUser.ModKey && !Objects.requireNonNull(getUser(post1.getUsername())).AdminKey && !Objects.requireNonNull(getUser(post1.getUsername())).ModKey;
    }


    @FXML
    public void mouse_in_publicacao (){

        glow_post.setLevel(0.6);


        if(caixote.isHover()){
            caixote.setEffect(glow_post);

        }

        else if(image_like.isHover()){
            likes.setEffect(glow_post);
            image_like.setEffect(glow_post);
        }

        else if(image_comment.isHover()){
            image_comment.setEffect(glow_post);
            comments.setEffect(glow_post);
        }

        else if(FotoDePerfilPost.isHover() || UsernamePost.isHover()){
            UsernamePost.setEffect(glow_post);
            glow_post.setLevel(0.3);
            FotoDePerfilPost.setEffect(glow_post);
        }

        else{
            FotoDePerfilPost.setEffect(null);
            UsernamePost.setEffect(null);
            image_comment.setEffect(null);
            comments.setEffect(null);
            likes.setEffect(null);
            image_like.setEffect(null);
            caixote.setEffect(null);

        }



    }

}