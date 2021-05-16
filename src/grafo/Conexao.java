package grafo;

public class Conexao extends Elemento {
    private static final String SEPARADOR_NODOS = "=";

    public Conexao(final String conteudo) {
        super(conteudo);
    }

    @Override
    int getTamanhoMaximo() {
        return 5;
    }

    @Override
    void validaConteudoLinha() {
        final var nodos = this.conteudo.split(SEPARADOR_NODOS);
        if (nodos.length < 2 || nodos.length > 2)
            throw new ValidacaoGrafoException("conteúdo da linha inválido na \"conexão\"!");
    }

    public String[] getNodos() {
        return this.conteudo.split(SEPARADOR_NODOS);
    }
}
