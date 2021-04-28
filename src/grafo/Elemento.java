package grafo;

public abstract class Elemento {
    final String conteudo;

    abstract int getTamanhoMaximo();

    abstract void validaConteudoLinha();

    public Elemento(final String conteudo) {
        this.conteudo = conteudo;
        this.validaTamanhoConteudo();
        this.validaConteudoLinha();
    }

    final void validaTamanhoConteudo() {
        if (this.conteudo.length() > this.getTamanhoMaximo())
            throw new ValidacaoGrafoException("Tamanho da linha excedido!");
    }
}
