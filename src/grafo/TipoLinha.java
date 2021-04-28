package grafo;

public enum TipoLinha {
    CABECALHO, CONEXAO, PESO, TRAILER;

    public static TipoLinha from(final String valor) {
        switch (valor) {
            case "00":
                return CABECALHO;
            case "01":
                return CONEXAO;
            case "02":
                return PESO;
            case "09":
                return TRAILER;
            default:
                return null;
        }
    }

    public String getDescricao() {
        switch (this) {
            case CABECALHO:
                return "cabeçalho";
            case CONEXAO:
                return "conexão";
            case PESO:
                return "peso";
            case TRAILER:
                return "trailer";
            default:
                return null;
        }
    }
}
