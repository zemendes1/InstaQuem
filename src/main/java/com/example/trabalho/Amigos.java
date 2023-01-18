package com.example.trabalho;

public class Amigos {
    public void aceita_pedido(User UserRecetor, User UserPediu){

            UserRecetor.PedidosdeAmizade_Recebido.remove(UserPediu);
            UserPediu.PedidosdeAmizade_Enviado.remove(UserRecetor);
            UserPediu.PedidosdeAmizade_Recebido.remove(UserRecetor);
            UserRecetor.PedidosdeAmizade_Enviado.remove(UserPediu);


            UserRecetor.ListaAmigos.add(UserPediu);
            UserPediu.ListaAmigos.add(UserRecetor);
            UserRecetor.numeroAmigos++;
            UserPediu.numeroAmigos++;

    }
    public void rejeita_pedido(User UserRecetor, User UserPediu){

        UserRecetor.PedidosdeAmizade_Recebido.remove(UserPediu);
        UserPediu.PedidosdeAmizade_Enviado.remove(UserRecetor);
        UserRecetor.PedidosdeAmizade_Enviado.remove(UserPediu);
        UserPediu.PedidosdeAmizade_Recebido.remove(UserRecetor);

    }

    public void cancela_pedido(User UserRecetor, User UserPediu){

        UserRecetor.PedidosdeAmizade_Recebido.remove(UserPediu);
        UserPediu.PedidosdeAmizade_Enviado.remove(UserRecetor);
        UserRecetor.PedidosdeAmizade_Enviado.remove(UserPediu);
        UserPediu.PedidosdeAmizade_Recebido.remove(UserRecetor);

    }

    public static void envia_pedido(User UserRecetor, User UserPediu){

        if(!UserRecetor.PedidosdeAmizade_Recebido.contains(UserPediu) && !UserPediu.PedidosdeAmizade_Enviado.contains(UserRecetor) && !UserRecetor.PedidosdeAmizade_Enviado.contains(UserPediu) && !UserPediu.PedidosdeAmizade_Recebido.contains(UserRecetor)){

            UserRecetor.PedidosdeAmizade_Recebido.add(UserPediu);
            UserPediu.PedidosdeAmizade_Enviado.add(UserRecetor);

        }
    }

    public void remove_amizade(User UserRecetor, User UserPediu){

        if(UserPediu.ListaAmigos.contains(UserRecetor) && UserRecetor.ListaAmigos.contains(UserPediu)){ //verifica
            UserPediu.ListaAmigos.remove(UserRecetor);
            UserRecetor.ListaAmigos.remove(UserPediu);

        }
    }

    public static int check_amizade(User UserRecetor, User UserPediu){


        if(UserRecetor.PedidosdeAmizade_Recebido.contains(UserPediu) && UserPediu.PedidosdeAmizade_Enviado.contains(UserRecetor)){
            return 1;
        }

        else if(UserRecetor.PedidosdeAmizade_Enviado.contains(UserPediu) && UserPediu.PedidosdeAmizade_Recebido.contains(UserRecetor)){
            return 2;
        }

        else if(UserRecetor.ListaAmigos.contains(UserPediu) && UserPediu.ListaAmigos.contains(UserRecetor)){
            return 3;
        }

        return 0;
    }

}
//Caso 0 não são amigos nem existe nenhum pedido de amizade.
//Caso 1 recebeu um pedido de amizade a...

//Caso 2 enviou um pedido de amizade a...
//Caso 3 são amigos.