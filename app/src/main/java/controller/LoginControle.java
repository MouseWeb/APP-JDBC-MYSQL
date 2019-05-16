package controller;

import java.io.Serializable;
import java.util.Objects;

public class LoginControle  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private String senha;

    public LoginControle(){

    }

    public LoginControle(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginControle)) return false;
        LoginControle that = (LoginControle) o;
        return getId ( ) == that.getId ( ) &&
                Objects.equals ( getLogin ( ), that.getLogin ( ) ) &&
                Objects.equals ( getSenha ( ), that.getSenha ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId ( ), getLogin ( ), getSenha ( ) );
    }

    @Override
    public String toString() {
        return "LoginControle{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

}
