package model;

import java.util.List;

import controller.NovoRelatoControle;

public interface NovoRelatoInterface {

    void userFindById(String login, String senha);

    void insertRelato(NovoRelatoControle n);

    List <NovoRelatoControle> getMailUser();
}
