package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

import static com.example.trabalho.PaginaComentario.indexPost;
import static com.example.trabalho.PaginaPrincipal.ls;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.utilizadorProcurado;

public class Comentario extends Page{
    @FXML
    private ImageView FotoDePerfilComment,image_like;
    @FXML
    private Label UsernameComment;
    @FXML
    private Label date;
    @FXML
    private Label likes;
    @FXML
    private TextArea TextoDoComment;
    @FXML
    private ImageView caixote;

    Comment comment1 = new Comment();

    final Glow glow_post = new Glow();

    public void SetData (Comment comment)
    {
        comment1 = comment;
        Image image = new Image(comment.getProfileImgSrc()+".png");
        FotoDePerfilComment.setImage(image);
        UsernameComment.setText(comment.getUsername());
        TextoDoComment.setText(comment.getText());
        date.setText(comment.getDate());
        likes.setText(comment.getNbLikes());
        caixote.setVisible(pode_apagar());
    }

    public void Liker() {
        comment1.GiveLike(comment1);
        likes.setText((comment1.getNbLikes()));
    }

    public void Delete() throws IOException{
        if (pode_apagar()) {
            ls.get(indexPost).ListaComentarios.remove(comment1);

            int number = Integer.parseInt(ls.get(indexPost).getNbComments());
            ls.get(indexPost).setNbComments(String.valueOf(number-1));

            this.changeScene("pagina_principal.fxml", 1200, 800);
        }

    }
    public void go_to_outro_user() throws IOException{
        if (comment1.getUsername() != null){
            utilizadorProcurado(getUser (comment1.getUsername()));
            this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
        }
    }

    public boolean pode_apagar() {
        if (LoggedUser.AdminKey) {
            return true;
        } // admin apaga um post
        else if (comment1.getUsername().equals(LoggedUser.getUsername())) {
            return true;
        }//utilizador apaga o próprio post
        else return LoggedUser.ModKey && !Objects.requireNonNull(getUser(comment1.getUsername())).AdminKey && !Objects.requireNonNull(getUser(comment1.getUsername())).ModKey;
    }

    @FXML
    public void mouse_in_comentario (){

        glow_post.setLevel(0.6);


        if(caixote.isHover()){
            caixote.setEffect(glow_post);

        }

        else if(image_like.isHover()){
            likes.setEffect(glow_post);
            image_like.setEffect(glow_post);
        }

        else if(FotoDePerfilComment.isHover() || UsernameComment.isHover()){
            UsernameComment.setEffect(glow_post);
            glow_post.setLevel(0.3);
            FotoDePerfilComment.setEffect(glow_post);
        }

        else{
            FotoDePerfilComment.setEffect(null);
            UsernameComment.setEffect(null);
            likes.setEffect(null);
            image_like.setEffect(null);
            caixote.setEffect(null);

        }



    }
}
