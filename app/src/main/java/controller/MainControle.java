package controller;

import java.io.Serializable;
import java.util.Objects;

public class MainControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;

    public MainControle(){

    }

    public MainControle(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainControle)) return false;
        MainControle that = (MainControle) o;
        return getNome ( ).equals ( that.getNome ( ) ) &&
                getEmail ( ).equals ( that.getEmail ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getNome ( ), getEmail ( ) );
    }

    @Override
    public String toString() {
        return "MainControle{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
