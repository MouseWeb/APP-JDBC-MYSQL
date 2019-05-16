package controller;

import java.io.Serializable;
import java.util.Objects;

public class CadastroControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String user;
    private String senha;
    private String email;
    private String nivel = "M";

    public CadastroControle() {

    }

    public CadastroControle(int id, String nome, String user, String senha, String email, String nivel) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.email = email;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CadastroControle)) return false;
        CadastroControle that = (CadastroControle) o;
        return getId ( ) == that.getId ( ) &&
                getNome ( ).equals ( that.getNome ( ) ) &&
                getUser ( ).equals ( that.getUser ( ) ) &&
                getSenha ( ).equals ( that.getSenha ( ) ) &&
                getEmail ( ).equals ( that.getEmail ( ) ) &&
                getNivel ( ).equals ( that.getNivel ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId ( ), getNome ( ), getUser ( ), getSenha ( ), getEmail ( ), getNivel ( ) );
    }

    @Override
    public String toString() {
        return "CadastroControle{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", user='" + user + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }

}
