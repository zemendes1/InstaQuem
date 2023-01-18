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


public class Caixa_amizade_controller extends Page implements Initializable {


    final Glow glow_amig = new Glow();

    final Glow glow_amig_foto = new Glow();

    @FXML
    private ImageView Foto_de_Perfil_Array_Amig;

    @FXML
    private Label Quer_ser_Amigo_Array_Amig;

    @FXML
    private HBox auxiliar_Amig;


    public void setData(Caixa_amizade not){


        Image image1 = new Image(not.getImagem_user()+".png");
        Foto_de_Perfil_Array_Amig.setImage(image1);

        Quer_ser_Amigo_Array_Amig.setText(not.getUser_pedido());


        auxiliar_Amig.setId(not.getUser_pedido());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void go_to_perfil_amig() throws IOException {

        utilizadorProcurado(User.getUser(auxiliar_Amig.getId()));
        this.changeScene("perfil_outro_utilizador.fxml", 1200, 800);
    }

    @FXML
    public void mouse_in_amig (){

        glow_amig.setLevel(0.6);
        glow_amig_foto.setLevel(0.2);


        if(Foto_de_Perfil_Array_Amig.isHover() || Quer_ser_Amigo_Array_Amig.isHover() ){
            Quer_ser_Amigo_Array_Amig.setEffect(glow_amig);
            Foto_de_Perfil_Array_Amig.setEffect(glow_amig_foto);

        }


        else{
            Foto_de_Perfil_Array_Amig.setEffect(null);
            Quer_ser_Amigo_Array_Amig.setEffect(null);

        }



    }


}
