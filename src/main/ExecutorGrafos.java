package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JOptionPane;
import grafo.ProcessaArquivoGrafo;
import util.StringUtil;

public class ExecutorGrafos {
    private final Configuracao configuracao;

    public ExecutorGrafos() {
        configuracao = new Configuracao(Pastas.CONFIGURACAO_ARQUIVO.caminho);
    }

    private void executaFluxo() {
        GerenciadorSistemaArquivos.criaPasta(Pastas.RAIZ.caminho);
        GerenciadorSistemaArquivos.criaPasta(Pastas.CONFIGURACAO.caminho);
        GerenciadorSistemaArquivos.criaArquivo(Pastas.CONFIGURACAO_ARQUIVO.caminho);
        configuracao.valida();
        configuracao.criaPastasConfiguracao();
        this.leArquivosRota();
    }

    private void leArquivosRota() {
        try {
            Files.walk(Paths.get(Pastas.RAIZ.caminho), 1).filter(Files::isRegularFile)
                    .filter((Path arquivo) -> arquivo.getFileName().toString().startsWith("rota"))
                    .filter((Path arquivo) -> arquivo.getFileName().toString().endsWith(".txt"))
                    .forEach(this::processaArquivoRota);
        } catch (IOException e) {
            throw new FinalizaExecucaoException("Não foi possível ler os arquivos de rota!");
        }
    }

    private void processaArquivoRota(Path arquivo) {
        try {
            List<String> linhas = Files.readAllLines(arquivo);
            final ProcessaArquivoGrafo processadorGrafo = new ProcessaArquivoGrafo(arquivo.getFileName().toString(),
                    linhas);
            processadorGrafo.processa();
        } catch (IOException e) {
            throw new FinalizaExecucaoException(
                    "Não foi possível ler o arquivo" + StringUtil.aspas(arquivo.toString()) + "!");
        }
    }

    public void executa() {
        try {
            this.executaFluxo();
        } catch (FinalizaExecucaoException e) {
            this.mostraMensagemErroExecucao(e.getMessage());
        }
    }

    private void mostraMensagemErroExecucao(final String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ooops!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

}
