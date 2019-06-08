package enumType;

public enum PerfilType {

    PACIENTE(1, "PACIENTE"),
    MEDICO(2, "MEDICO"),
    ADM(2,"ADM");

    private int cod;
    private String descricao;

    private PerfilType(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PerfilType toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (PerfilType x : PerfilType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        return null;
    }

}
