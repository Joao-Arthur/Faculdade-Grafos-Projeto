package grafo;

public class Trailer extends Elemento {
	private final int linhasConexao;
	private final int linhasPeso;
	private static final String SEPARADOR_TOTAL_LINHAS = ";";

	public Trailer(final String conteudo) {
		super(conteudo);
		final String[] totalLinhas = this.getTotalLinhas();
		this.linhasConexao = Integer.parseInt(totalLinhas[0]);
		this.linhasPeso = Integer.parseInt(totalLinhas[1]);
	}

	@Override
	int getTamanhoMaximo() {
		return 5;
	}

	@Override
	void validaConteudoLinha() {
		final String[] totalLinhas = this.getTotalLinhas();
		if (totalLinhas.length < 2 || totalLinhas.length > 2)
			throw new ValidacaoGrafoException("conteúdo da linha inválido no \"trailer\"!");
	}

	private String[] getTotalLinhas() {
		return this.conteudo.split(SEPARADOR_TOTAL_LINHAS);
	}

	public void validaLinhasConexao(int linhasConexao) {
		if (this.linhasConexao != linhasConexao)
			throw new ValidacaoGrafoException("Total de linhas de conexões difere entre o trailer e o arquivo!");
	}

	public void validaLinhasPesos(int linhasPeso) {
		if (this.linhasPeso != linhasPeso)
			throw new ValidacaoGrafoException("Total de linhas de pesos difere entre o trailer e o arquivo!");
	}

}
