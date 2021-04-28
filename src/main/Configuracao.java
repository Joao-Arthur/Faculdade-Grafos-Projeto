package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import util.StringUtil;

public class Configuracao {
    private final Map<String, String> configuracao = new HashMap<>();
    private final String caminhoArquivo;

    public Configuracao(final String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        this.configuracao.put("Processado", Pastas.PROCESSADO.caminho);
        this.configuracao.put("Não Processado", Pastas.NAO_PROCESSADO.caminho);
    }

    public void carrega() {
        try {
            final Path path = Paths.get(caminhoArquivo);
            final List<String> linhas = Files.readAllLines(path);
            final int numeroLinhas = linhas.size();
            if (numeroLinhas > 2)
                throw new FinalizaExecucaoException("O número de linhas do arquivo está incorreto!");
            if (numeroLinhas < 2) {
                Files.write(path, this.getConfiguracaoPadrao());
                return;
            }
            linhas.forEach(this::validaLinhaArquivo);
        } catch (IOException e) {
            throw new FinalizaExecucaoException("Ocorreu um erro ao carregar o arquivo de configuração!");
        }
    }

    private List<String> getConfiguracaoPadrao() {
        return this.configuracao.entrySet().stream()
                .map((entrada) -> (String) entrada.getKey() + "=" + entrada.getValue()).collect(Collectors.toList());
    }

    private void validaLinhaArquivo(final String linha) {
        final String[] conteudoLinha = linha.split("=");
        if (conteudoLinha.length < 2 || conteudoLinha.length > 2)
            throw new FinalizaExecucaoException(
                    "A linha " + StringUtil.aspas(linha) + " não é uma configuração válida!");
        final String chave = conteudoLinha[0];
        final String valor = conteudoLinha[1];
        this.configuracao.replace(chave, valor);
    }

    public void criaPastasConfiguracao() {
        this.configuracao.values().stream().forEach(GerenciadorSistemaArquivos::criaPasta);
    }

    public String getPastaProcessado() {
        return this.configuracao.get("Processado");
    }

    public String getPastaNaoProcessado() {
        return this.configuracao.get("Não Processado");
    }
}
