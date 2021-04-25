package grafo;

import java.util.List;
import java.util.stream.Collectors;

import main.FinalizaExecucaoException;

public class ProcessaArquivoGrafo {
	private final String nomeArquivo;
	private final List<String> linhas;

	public ProcessaArquivoGrafo(final String nomeArquivo, final List<String> linhas) {
		this.nomeArquivo = nomeArquivo;
		this.linhas = linhas;
	}

	public void processa() {
		if (linhas.stream().anyMatch(linha -> linha.length() < 3))
			throw new FinalizaExecucaoException("O arquivo possui linhas com conteúdo inválido!");
		final List<TipoLinha> tiposDasLinhas = linhas.stream().map(linha -> linha.substring(0, 2)).map(TipoLinha::from)
				.collect(Collectors.toList());
		this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, null);
		this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, TipoLinha.CABECALHO);
		this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, TipoLinha.CONEXAO);
		this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, TipoLinha.PESO);
		this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, TipoLinha.TRAILER);
		this.linhas.forEach(this::processaLinha);
	}

	private void validaNumeroDeLinhasPorTipo(final List<TipoLinha> tiposDasLinhas, TipoLinha tipoLinha) {
		final long totalLinhasTipo = tiposDasLinhas.stream().filter(identificador -> identificador == tipoLinha)
				.count();
		final ValidadorNumeroDeLinhas validador = new ValidadorNumeroDeLinhas(this.nomeArquivo, tipoLinha,
				totalLinhasTipo);
		validador.valida();
	}

	private void processaLinha(String linha) {
		final String tipoLinha = linha.substring(0, 2);
		// ElementoFactory.cria(TipoLinha.from(tipoLinha));
		// validaHeaders(); //NN e SP, tamanho caracteres
		// validaResumoConexoes()
		// validaRegistroEResumoDeConexoes()
		// processarArquivo()
	}

}
