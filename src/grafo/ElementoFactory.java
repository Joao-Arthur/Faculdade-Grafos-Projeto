package grafo;

public class ElementoFactory {
    public static Elemento cria(final TipoLinha tipoLinha, final String conteudo) {
        switch (tipoLinha) {
            case CABECALHO:
                return new Cabecalho(conteudo);
            case CONEXAO:
                return new Conexao(conteudo);
            case PESO:
                return new Peso(conteudo);
            case TRAILER:
                return new Trailer(conteudo);
            default:
                return null;
        }
    }
}
