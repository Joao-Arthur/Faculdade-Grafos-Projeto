package grafo;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProcessaArquivoGrafo {
    private final String nomeArquivo;
    private final List<String> linhas;

    public ProcessaArquivoGrafo(final String nomeArquivo, final List<String> linhas) {
        this.nomeArquivo = nomeArquivo;
        this.linhas = linhas;
    }

    public void processa() {
        if (linhas.stream().anyMatch(linha -> linha.length() < 3))
            throw new ValidacaoGrafoException("O arquivo possui linhas com conteúdo inválido!");
        
        this.validaNumeroLinhas();
        final List<Elemento> elementos = this.linhas.stream().map(this::processaLinha).collect(Collectors.toList());
        final var cabecalho = (Cabecalho) elementos.stream()
        		                                         .filter(elemento -> elemento instanceof Cabecalho)
                                                         .findFirst()
                                                         .get();
        final var trailer = (Trailer) elementos.stream()
        		                                   .filter(elemento -> elemento instanceof Trailer)
        		                                   .findFirst()
                                                   .get();
        final int pesoTotal = elementos.stream()
        		                        .filter(Peso.class::isInstance)
                                        .map(Peso.class::cast).map(Peso::getPeso)
                                        .reduce(0, (subTotal, peso) -> subTotal + peso);
        final var totalNodos = (int) elementos.stream()
        	                        	      .filter(Conexao.class::isInstance)
                                              .map(Conexao.class::cast).map(conexao -> Arrays.asList(conexao.getNodos()))
                                              .flatMap(Collection::stream)
                                              .distinct()
                                              .count();
        final var totalLinhasNodos = (int) elementos.stream()
        		                                    .filter(Conexao.class::isInstance).distinct()
                                                    .count();
        final var totalLinhasPesos = (int) elementos.stream()
        		                                    .filter(Peso.class::isInstance)
        		                                    .distinct()
                                                    .count();
        cabecalho.validaTotalNos(totalNodos);
        cabecalho.validaSomaPesos(pesoTotal);
        trailer.validaLinhasConexao(totalLinhasNodos);
        trailer.validaLinhasPesos(totalLinhasPesos);
    }

    private void validaNumeroLinhas() {
        final List<TipoLinha> tiposDasLinhas = linhas.stream().map(linha -> linha.substring(0, 2)).map(TipoLinha::from)
                .collect(Collectors.toList());
        this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, null);
        for (TipoLinha tipoLinha : TipoLinha.values())
            this.validaNumeroDeLinhasPorTipo(tiposDasLinhas, tipoLinha);
    }

    private void validaNumeroDeLinhasPorTipo(final List<TipoLinha> tiposDasLinhas, TipoLinha tipoLinha) {
        final var totalLinhasTipo = tiposDasLinhas.stream().filter(identificador -> identificador == tipoLinha)
                .count();
        final var validador = new ValidadorNumeroDeLinhas(this.nomeArquivo, tipoLinha,
                totalLinhasTipo);
        validador.valida();
    }

    private Elemento processaLinha(String linha) {
        final var tipoLinha = linha.substring(0, 2);
        final var conteudo = linha.substring(2);
        return ElementoFactory.cria(TipoLinha.from(tipoLinha), conteudo);
    }

}
