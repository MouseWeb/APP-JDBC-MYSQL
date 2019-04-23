package Controller;

import java.util.Objects;

public class CadastroControle {

    private int id;
    private String nome;
    private String user;
    private String senha;
    private String nivel = "M";

    public CadastroControle() {

    }

    public CadastroControle(int id, String nome, String user, String senha, String nivel) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CadastroControle)) return false;
        CadastroControle that = (CadastroControle) o;
        return getId ( ) == that.getId ( ) &&
                Objects.equals ( getNome ( ), that.getNome ( ) ) &&
                Objects.equals ( getUser ( ), that.getUser ( ) ) &&
                Objects.equals ( getSenha ( ), that.getSenha ( ) ) &&
                Objects.equals ( getNivel ( ), that.getNivel ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId ( ), getNome ( ), getUser ( ), getSenha ( ), getNivel ( ) );
    }

    @Override
    public String toString() {
        return "CadastroControle{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", user='" + user + '\'' +
                ", senha='" + senha + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
