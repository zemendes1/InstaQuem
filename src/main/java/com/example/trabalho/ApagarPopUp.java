package com.example.trabalho;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.text.Text;


import java.io.IOException;
import java.util.Objects;

import static com.example.trabalho.PaginaPrincipal.ls;
import static com.example.trabalho.User.getUser;
import static com.example.trabalho.UserProcurado.LoggedUser;
import static com.example.trabalho.UserProcurado.Utilizador_POU;


public class ApagarPopUp extends Page{

    final Glow glow = new Glow(1);
    @FXML
    private Text text_x;
    @FXML
    private Button botao_nao,botao_sim;
    DropShadow dropShadow = new DropShadow();



    @FXML
    void ApagarPerfil() throws IOException{

        for(int i=0;i<ls.size();i++) { // remove Posts
            if (ls.get(i).getUsernameLikes().contains(Utilizador_POU.getUsername())) { //remove likes dos posts
                int nblikes = ls.get(i).getnBLikesAux();
                nblikes = nblikes - 1;
                ls.get(i).setnBLikesAux(nblikes);
                ls.get(i).setNbLikes(String.valueOf(nblikes));
                ls.get(i).getUsernameLikes().remove(Utilizador_POU.getUsername());
            }
            for (int c = 0; c < ls.get(i).getListaComentario().size(); c++) { //Ve todos os comments
                if (ls.get(i).getListaComentario().get(c).getUsernamesLiked().contains(Utilizador_POU.getUsername())) { //apaga likes dos comentarios
                    int nblikescomment = ls.get(i).getListaComentario().get(c).getnBLikesAux();
                    nblikescomment = nblikescomment - 1;
                    ls.get(i).getListaComentario().get(c).setnBLikesAux(nblikescomment);
                    ls.get(i).getListaComentario().get(c).setNbLikes(String.valueOf(nblikescomment));
                    ls.get(i).getUsernameLikes().remove(Utilizador_POU.getUsername());

                }
                if (ls.get(i).getListaComentario().get(c).getUsername().equals(Utilizador_POU.getUsername())) { //remove comentarios
                    int nbcommentsaux = Integer.parseInt(ls.get(i).getNbComments());
                    nbcommentsaux = nbcommentsaux - 1;
                    ls.get(i).setNbComments(String.valueOf(nbcommentsaux));
                    ls.get(i).getListaComentario().remove(c);
                    c--;
                }
            }
            if (ls.get(i).getUsername().equals(Utilizador_POU.getUsername())) {
                ls.remove(i);
                i--;
            }
        }

        User a = Objects.requireNonNull(getUser(Utilizador_POU.getUsername()));
        for(int i=0;i<a.PedidosdeAmizade_Enviado.size();i++){  //apaga dos Arrays de quem recebeu um pedido de amizade do User
          User L = Objects.requireNonNull(getUser(a.PedidosdeAmizade_Enviado.get(i).getUsername()));
          if(L.getPedidosdeAmizade_Recebido().contains(a)){
              L.getPedidosdeAmizade_Recebido().remove(a);
          }
        }
        for(int i=0;i<a.PedidosdeAmizade_Recebido.size();i++){ //apaga dos Arrays de quem pediu amizade ao User
            User L = Objects.requireNonNull(getUser(a.PedidosdeAmizade_Recebido.get(i).getUsername()));
            if(L.getPedidosdeAmizade_Enviado().contains(a)){
                L.getPedidosdeAmizade_Enviado().remove(a);
            }
        }
        for(int i = 0; i< a.ListaAmigos.size(); i++) { // remove Amizades
            Objects.requireNonNull(getUser(Utilizador_POU.getUsername())).ListaAmigos.get(i).ListaAmigos.remove(a);
        }

        Login.getUserList().remove(Utilizador_POU);//remove User

        if(LoggedUser.getUsername().equals(Utilizador_POU.getUsername())){
            this.changeScene("hello-view.fxml", 600, 400);
        }
        else{
            this.changeScene("pagina_principal.fxml", 1200, 800);
        }


    }

    @FXML
    void VoltarEditar() throws IOException{
        this.changeScene("Editar_Perfil.fxml",1200,800);
    }

    @FXML
    public void mouse_in (){
        dropShadow.setOffsetY(3.0);
        dropShadow.setOffsetX(3.0);

        if(text_x.isHover()){

            text_x.setEffect(glow);

        }

        else if(botao_nao.isHover()){

            botao_nao.setEffect(dropShadow);

        }
        else if(botao_sim.isHover()){

            botao_sim.setEffect(dropShadow);

        }
        else{
            text_x.setEffect(null);
            botao_sim.setEffect(null);
            botao_nao.setEffect(null);
        }
    }

}