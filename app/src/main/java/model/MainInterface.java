package model;

import java.util.List;

import controller.MainControle;

public interface MainInterface {

    void userFindById(String login, String senha);

    List <MainControle> getPerfilMenu();

}
