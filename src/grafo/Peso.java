package grafo;

public class Peso extends Elemento {
    private static final String SEPARADOR_NODOS = ";";
    private static final String SEPARADOR_PESO = "=";

    public Peso(final String conteudo) {
        super(conteudo);
    }

    @Override
    int getTamanhoMaximo() {
        return 10003;
    }

    @Override
    void validaConteudoLinha() {
        final String[] nodos = this.conteudo.split(SEPARADOR_NODOS + "|" + SEPARADOR_PESO);
        if (nodos.length < 3 || nodos.length > 3)
            throw new ValidacaoGrafoException("conteúdo da linha inválido no \"peso\"!");
    }

    public int getPeso() {
        return Integer.parseInt(conteudo.split(SEPARADOR_PESO)[1]);
    }

}
