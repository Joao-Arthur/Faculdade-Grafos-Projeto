package grafo;

public class Peso implements IElemento {
	public static final int TAMANHO_MAXIMO = 10003;
	private final String conteudo;
	private static final String SEPARADOR_NODOS = ";";
	private static final String SEPARADOR_PESO = "=";

	public Peso(final String conteudo) {
		this.conteudo = conteudo;
		this.validaTamanhoConteudo();
		this.validaConteudoLinha();
	}

	@Override
	public void validaTamanhoConteudo() {
		if (this.conteudo.length() > TAMANHO_MAXIMO)
			throw new ValidacaoGrafoException("Tamanho da linha excedido no \"peso\"!");
	}

	@Override
	public void validaConteudoLinha() {
		final String[] nodos = this.conteudo.split(SEPARADOR_NODOS + "|" + SEPARADOR_PESO);
		if (nodos.length < 3 || nodos.length > 3)
			throw new ValidacaoGrafoException("conteúdo da linha inválido na \"conexão\"!");
	}

	public int getPeso() {
		return Integer.parseInt(conteudo.split(SEPARADOR_PESO)[1]);
	}
}
