package model;

import java.util.List;

import Controller.PerfilControle;

public interface PerfilInterface {

    void buscarPerfil(String login, String senha);
    void update(PerfilControle obj);
    List <PerfilControle> getListaPerfil();

}