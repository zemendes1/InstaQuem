package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.trabalho.UserProcurado.utilizadorProcurado;


public class Notificacao_controller extends Page implements Initializable {

    @FXML
    private ImageView Foto_de_Perfil_Array_Not;

    @FXML
    private Label Quer_ser_Amigo_Array_Not;

    @FXML
    private HBox auxiliar;

    final Glow glow_not = new Glow();

    final Glow  glow_foto_not = new Glow();


    public void setData(Notificacao not){


        Image image1 = new Image(not.getImagem_user()+".png");
        Foto_de_Perfil_Array_Not.setImage(image1);

        Quer_ser_Amigo_Array_Not.setText("O " +not.getUser_pedido() + " quer ser seu amigo!");


        auxiliar.setId(not.getUser_pedido());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void go_to_perfil_notificacao_recebida() throws IOException {

        utilizadorProcurado(User.getUser(auxiliar.getId()));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }


    @FXML
    public void mouse_in_not (){

        glow_not.setLevel(0.6);
        glow_foto_not.setLevel(0.2);


        if(Foto_de_Perfil_Array_Not.isHover() || Quer_ser_Amigo_Array_Not.isHover() ){
            Quer_ser_Amigo_Array_Not.setEffect(glow_not);
            Foto_de_Perfil_Array_Not.setEffect(glow_foto_not);

        }


        else{
            Foto_de_Perfil_Array_Not.setEffect(null);
            Quer_ser_Amigo_Array_Not.setEffect(null);

        }



    }

}
