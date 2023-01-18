package com.example.trabalho;

import java.util.ArrayList;

import static com.example.trabalho.UserProcurado.LoggedUser;

public class Post {

    private int profileImgSrc;
    private String text;
    private String username;
    private String date;
    public String nbLikes;
    private int index;
    public int nBLikesAux;
    private String nbComments;
    public ArrayList<Comment> ListaComentarios = new ArrayList<>();
    private final ArrayList<String> UsernamesLiked = new ArrayList<>(); //para saber que users deram like no post

    public int getProfileImgSrc(){
        return profileImgSrc;
    }

    public void setProfileImgSrc( int profileImgSrc){
       this.profileImgSrc = profileImgSrc;
    }

    public ArrayList<String> getUsernameLikes(){return UsernamesLiked;}

    public String getText(){
        return text;
    }

    public void setText( String text){
        this.text = text;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername( String username){
        this.username = username;
    }

    public String getDate(){
        return date;
    }

    public void setDate( String date){
        this.date = date;
    }

    public String getNbLikes(){
        return nbLikes;
    }

    public void setNbLikes( String nbLikes){
        this.nbLikes = nbLikes;
    }
    public void setnBLikesAux(int nBLikesAux){this.nBLikesAux=nBLikesAux;}
    public int getnBLikesAux(){return nBLikesAux;}

    public int getIndex(){
        return index;
    }

    public void setIndex( int index){
        this.index = index;
    }

    public String getNbComments(){return nbComments;}
    public void setNbComments( String nbComments){
        this.nbComments = nbComments;
    }
    public ArrayList<Comment> getListaComentario(){ return ListaComentarios;}


    public void GiveLike(Post post) {

        if(post.UsernamesLiked.contains(LoggedUser.getUsername()) && !LoggedUser.silenciado){
            post.UsernamesLiked.remove(LoggedUser.getUsername());
            post.nBLikesAux = this.nBLikesAux - 1;
            post.nbLikes = String.valueOf(this.nBLikesAux);
        }
        else if (!post.UsernamesLiked.contains(LoggedUser.getUsername())&& !LoggedUser.silenciado) {
            post.UsernamesLiked.add(LoggedUser.getUsername());
            post.nBLikesAux = post.nBLikesAux + 1;
            post.nbLikes = String.valueOf(post.nBLikesAux);
        }
    }

}
