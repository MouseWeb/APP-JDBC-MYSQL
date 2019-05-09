package Controller;

import java.io.Serializable;
import java.util.Objects;

public class PerfilControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String telefone;
    private String login;
    private String senha;

    public PerfilControle(){

    }

    public PerfilControle(String nome, String email, String telefone, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        if (!(o instanceof PerfilControle)) return false;
        PerfilControle that = (PerfilControle) o;
        return getNome ( ).equals ( that.getNome ( ) ) &&
                getEmail ( ).equals ( that.getEmail ( ) ) &&
                getTelefone ( ).equals ( that.getTelefone ( ) ) &&
                getLogin ( ).equals ( that.getLogin ( ) ) &&
                getSenha ( ).equals ( that.getSenha ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getNome ( ), getEmail ( ), getTelefone ( ), getLogin ( ), getSenha ( ) );
    }

    @Override
    public String toString() {
        return "PerfilControle{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

}
