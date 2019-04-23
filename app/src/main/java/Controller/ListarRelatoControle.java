package Controller;

import java.util.Objects;

public class ListarRelatoControle {

    private int id;
    private String dosagem;
    private String dataInicio;
    private String dataTermino;
    private String gravidade;
    private String descricao;
    private String medicaemnto;

    public ListarRelatoControle(){

    }

    public ListarRelatoControle(int id, String dosagem, String dataInicio, String dataTermino, String gravidade, String descricao, String medicaemnto) {
        this.id = id;
        this.dosagem = dosagem;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.gravidade = gravidade;
        this.descricao = descricao;
        this.medicaemnto = medicaemnto;
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

    public String getDataInicio(String rel_qd_med_ini) {
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

    @Override
    public String toString() {
        return " Medicaemnto: " + medicaemnto + "\n" +
                " Dosagem: " + dosagem + "\n" +
                " Data Inicio: " + dataInicio + "\n" +
                " Data Termino: " + dataTermino + "\n" +
                " Gravidade: " + gravidade + "\n" +
                " Descricao: " + descricao ;
    }
}
