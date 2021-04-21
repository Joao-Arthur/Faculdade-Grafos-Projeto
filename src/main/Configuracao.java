package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.StringUtil;

public class Configuracao {
    private static final int NUMERO_CONFIGURACOES = 2;
    private final Map<String, String> configuracao = new HashMap<>(NUMERO_CONFIGURACOES);
    private final String caminhoArquivo;

    public Configuracao(final String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        configuracao.put("Processado", "C:\\Temp\\Processado");
        configuracao.put("Não Processado", "C:\\Temp\\NaoProcessado");
    }

    public void valida() {
        try {
            final Path path = Paths.get(caminhoArquivo);
            final List<String> linhas = Files.readAllLines(path);
            final int numeroLinhas = linhas.size();
            if (numeroLinhas > NUMERO_CONFIGURACOES)
                throw new FinalizaExecucaoException("O número de linhas do arquivo está incorreto!");
            if (numeroLinhas < NUMERO_CONFIGURACOES) {
                Files.write(path, configuracaoToList());
                return;
            }
            linhas.forEach(this::validaLinhaArquivo);
        } catch (IOException e) {
            throw new FinalizaExecucaoException("Ocorreu um erro ao validar o conteúdo do arquivo de configuração!");
        }
    }

    private List<String> configuracaoToList() {
        List<String> list = new ArrayList<>(NUMERO_CONFIGURACOES);
        configuracao.forEach((pastaConfigurada, caminhoPasta) -> {
            list.add(pastaConfigurada + "=" + caminhoPasta);
        });
        return list;
    }

    private void validaLinhaArquivo(final String linha) {
        final String[] conteudoLinha = linha.split("=");
        if (conteudoLinha.length < 2 || conteudoLinha.length > 2)
            throw new FinalizaExecucaoException(
                    "A linha " + StringUtil.aspas(linha) + " não é uma configuração válida!");
        final String chave = conteudoLinha[0];
        final String valor = conteudoLinha[1];
        configuracao.replace(chave, valor);
    }

    public void criaPastasConfiguracao() {
        configuracao.forEach((configuracaoPasta, caminhoPasta) -> {
            GerenciadorSistemaArquivos.criaPasta(caminhoPasta);
        });
    }
}
