package grafo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessaArquivoGrafo {
	private final String nomeArquivo;
	private final List<String> linhas;

	public ProcessaArquivoGrafo(final String nomeArquivo, final List<String> linhas) {
		this.nomeArquivo = nomeArquivo;
		this.linhas = linhas;
	}

	public void processa() {
		try {
			this.processaArquivo();
			// movearquivoparapastasucesso
		} catch (ValidacaoGrafoException e) {
			// movearquivoparapastaerro
		}
	}

	private void processaArquivo() {
		if (linhas.stream().anyMatch(linha -> linha.length() < 3))
			throw new ValidacaoGrafoException("O arquivo possui linhas com conteúdo inválido!");

		validaNumeroLinhas();
		final List<IElemento> elementos = this.linhas.stream().map(this::processaLinha).collect(Collectors.toList());

		final Cabecalho cabecalho = (Cabecalho) elementos.stream().filter(elemento -> elemento instanceof Cabecalho)
				.findFirst().get();
		final Trailer trailer = (Trailer) elementos.stream().filter(elemento -> elemento instanceof Trailer).findFirst()
				.get();
		final int pesoTotal = elementos.stream().filter(elemento -> elemento instanceof Peso)
				.map(elemento -> (Peso) elemento).map(peso -> peso.getPeso())
				.reduce(0, (subTotal, peso) -> subTotal + peso);
		final int totalNodos = (int)

		Stream.concat(
				elementos.stream().filter(elemento -> elemento instanceof Conexao).map(elemento -> (Conexao) elemento)
						.map(conexao -> conexao.getNodoOrigem()),
				elementos.stream().filter(elemento -> elemento instanceof Conexao).map(elemento -> (Conexao) elemento)
						.map(conexao -> conexao.getNodoDestino()))
				.distinct().count();

		System.out.println(totalNodos);
	}

	private void validaNumeroLinhas() {
		final List<TipoLinha> tiposDasLinhas = linhas.stream().map(linha -> linha.substring(0, 2)).map(TipoLinha::from)
				.collect(Collectors.toList());
		this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, null);
		for (TipoLinha tipoLinha : TipoLinha.values())
			this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, tipoLinha);
	}

	private void validaNumeroDeLinhasPorTipo(final List<TipoLinha> tiposDasLinhas, TipoLinha tipoLinha) {
		final long totalLinhasTipo = tiposDasLinhas.stream().filter(identificador -> identificador == tipoLinha)
				.count();
		final ValidadorNumeroDeLinhas validador = new ValidadorNumeroDeLinhas(this.nomeArquivo, tipoLinha,
				totalLinhasTipo);
		validador.valida();
	}

	private IElemento processaLinha(String linha) {
		final String tipoLinha = linha.substring(0, 2);
		final String conteudo = linha.substring(2);
		System.out.println(tipoLinha + " " + conteudo);
		return ElementoFactory.cria(TipoLinha.from(tipoLinha), conteudo);
	}

}
