package model;

import java.util.List;
import Controller.ListarRelatoControle;

public interface ListarRelatoInterface {

    void userFindById(String login, String senha);

    List <ListarRelatoControle> getListaRelato();

}
