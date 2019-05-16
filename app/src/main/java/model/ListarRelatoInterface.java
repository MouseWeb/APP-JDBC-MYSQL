package model;

import java.util.List;
import controller.ListarRelatoControle;

public interface ListarRelatoInterface {

    void userFindById(String login, String senha);

    List <ListarRelatoControle> getListaRelato();

}
