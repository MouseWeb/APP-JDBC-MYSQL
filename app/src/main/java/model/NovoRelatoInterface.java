package model;

import Controller.NovoRelatoControle;

public interface NovoRelatoInterface {

    void userFindById(String login, String senha);

    void insertRelato(NovoRelatoControle n);

}
