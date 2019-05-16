package model;

import controller.CadastroControle;

public interface CadastroInterface {

    boolean checkLogin(String login, String senha);

    void create(CadastroControle obj);

}
