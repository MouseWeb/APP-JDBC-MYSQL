package controller;

import java.io.Serializable;
import java.util.Objects;

public class NovoRelatoControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String dosagem;
    private String dataInicio;
    private String dataTermino;
    private String gravidade;
    private String descricao;
    private String medicaemnto;
    private String email;

    public NovoRelatoControle(){

    }

    public NovoRelatoControle(int id, String dosagem, String dataInicio, String dataTermino,
                              String gravidade, String descricao, String medicaemnto, String email) {
        this.id = id;
        this.dosagem = dosagem;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.gravidade = gravidade;
        this.descricao = descricao;
        this.medicaemnto = medicaemnto;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMedicaemnto() {
        return medicaemnto;
    }

    public void setMedicaemnto(String medicaemnto) {
        this.medicaemnto = medicaemnto;
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
        if (!(o instanceof NovoRelatoControle)) return false;
        NovoRelatoControle that = (NovoRelatoControle) o;
        return getId ( ) == that.getId ( ) &&
                getDosagem ( ).equals ( that.getDosagem ( ) ) &&
                getDataInicio ( ).equals ( that.getDataInicio ( ) ) &&
                getDataTermino ( ).equals ( that.getDataTermino ( ) ) &&
                getGravidade ( ).equals ( that.getGravidade ( ) ) &&
                getDescricao ( ).equals ( that.getDescricao ( ) ) &&
                getMedicaemnto ( ).equals ( that.getMedicaemnto ( ) ) &&
                getEmail ( ).equals ( that.getEmail ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId ( ), getDosagem ( ), getDataInicio ( ),
                getDataTermino ( ), getGravidade ( ), getDescricao ( ), getMedicaemnto ( ), getEmail ( ) );
    }

    @Override
    public String toString() {
        return "NovoRelatoControle{" +
                "id=" + id +
                ", dosagem='" + dosagem + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataTermino='" + dataTermino + '\'' +
                ", gravidade='" + gravidade + '\'' +
                ", descricao='" + descricao + '\'' +
                ", medicaemnto='" + medicaemnto + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
