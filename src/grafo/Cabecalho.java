package grafo;

public class Cabecalho implements IElemento {
	public static final int TAMANHO_MAXIMO = 100001;
	private final String conteudo;
	private final int totalNos;
	private final int somaPesos;

	public Cabecalho(final String conteudo) {
		this.conteudo = conteudo;
		this.validaTamanhoConteudo();
		this.validaConteudoLinha();
		this.totalNos = Integer.parseInt(conteudo.substring(0, 2));
		this.somaPesos = Integer.parseInt(conteudo.substring(2));
	}

	@Override
	public void validaTamanhoConteudo() {
		if (this.conteudo.length() > TAMANHO_MAXIMO)
			throw new ValidacaoGrafoException("Tamanho da linha excedido no \"cabeçalho\"!");
	}

	@Override
	public void validaConteudoLinha() {
	}

	public void validaTotalNos(int totalNos) {
		if (this.totalNos != totalNos)
			throw new ValidacaoGrafoException("Total de nós difere entre o cabeçalho e as linhas!");
	}

	public void validaSomaPesos(int somaPesos) {
		if (this.somaPesos != somaPesos)
			throw new ValidacaoGrafoException("Soma dos pesos difere entre o cabeçalho e as linhas!");
	}
}
