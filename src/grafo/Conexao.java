package grafo;

public class Conexao implements IElemento {
	public static final int TAMANHO_MAXIMO = 5;
	private final String conteudo;
	private static final String SEPARADOR_NODOS = "=";

	public Conexao(final String conteudo) {
		this.conteudo = conteudo;
		this.validaTamanhoConteudo();
		this.validaConteudoLinha();
	}

	@Override
	public void validaTamanhoConteudo() {
		if (this.conteudo.length() > TAMANHO_MAXIMO)
			throw new ValidacaoGrafoException("Tamanho da linha excedido na \"conexão\"!");
	}

	@Override
	public void validaConteudoLinha() {
		final String[] nodos = this.conteudo.split(SEPARADOR_NODOS);
		if (nodos.length < 2 || nodos.length > 2)
			throw new ValidacaoGrafoException("conteúdo da linha inválido na \"conexão\"!");
	}

	public String getNodoOrigem() {
		return this.conteudo.split(SEPARADOR_NODOS)[0];
	}

	public String getNodoDestino() {
		return this.conteudo.split(SEPARADOR_NODOS)[1];
	}
}
