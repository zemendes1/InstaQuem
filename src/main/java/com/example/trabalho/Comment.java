package com.example.trabalho;

import java.util.ArrayList;

import static com.example.trabalho.UserProcurado.LoggedUser;

public class Comment {

    private int profileImgSrc;
    private String text;
    private String username;
    private String date;
    public String nbLikes;
    public int nBLikesAux;

    private final ArrayList<String> UsernamesLiked = new ArrayList<>(); //para saber que users deram like no post

    public int getProfileImgSrc(){
        return profileImgSrc;
    }

    public void setProfileImgSrc( int profileImgSrc){
        this.profileImgSrc = profileImgSrc;
    }

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
    public int getnBLikesAux(){return nBLikesAux;}
    public void setnBLikesAux(int nBLikesAux){this.nBLikesAux=nBLikesAux;}

    public ArrayList<String> getUsernamesLiked(){return UsernamesLiked;}

    public void GiveLike(Comment comment) {

        if (!comment.UsernamesLiked.contains(LoggedUser.getUsername()) && !LoggedUser.silenciado) {
            comment.UsernamesLiked.add(LoggedUser.getUsername());
            comment.nBLikesAux = comment.nBLikesAux + 1;
            comment.nbLikes = String.valueOf(comment.nBLikesAux);
        }
        else if (comment.UsernamesLiked.contains(LoggedUser.getUsername()) && !LoggedUser.silenciado) {
            comment.UsernamesLiked.remove(LoggedUser.getUsername());
            comment.nBLikesAux = this.nBLikesAux - 1;
            comment.nbLikes = String.valueOf(this.nBLikesAux);
        }
    }


}
