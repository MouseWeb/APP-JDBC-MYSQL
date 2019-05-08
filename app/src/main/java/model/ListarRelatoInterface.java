package model;

import java.util.List;
import Controller.ListarRelatoControle;

public interface ListarRelatoInterface {

    void buscarRelatos(String login, String senha);
    List <ListarRelatoControle> getListaRelato();

}
