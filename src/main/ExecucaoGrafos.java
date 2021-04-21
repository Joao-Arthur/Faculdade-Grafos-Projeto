package main;

import javax.swing.JOptionPane;

public class ExecucaoGrafos {
    private final static String TEMP_FOLDER_PATH = "C:\\Temp";
    private final static String CONFIGURACAO_FOLDER_PATH = "C:\\Temp\\Configuracao";
    private final static String CONFIG_FILE_PATH = "C:\\Temp\\Configuracao\\config.txt";
    private final Configuracao configuracao;

    public ExecucaoGrafos() {
        configuracao = new Configuracao(CONFIG_FILE_PATH);
    }

    private void executaFluxo() {
        GerenciadorSistemaArquivos.criaPasta(TEMP_FOLDER_PATH);
        GerenciadorSistemaArquivos.criaPasta(CONFIGURACAO_FOLDER_PATH);
        GerenciadorSistemaArquivos.criaArquivo(CONFIG_FILE_PATH);
        configuracao.valida();
        configuracao.criaPastasConfiguracao();
        // validaHeaders(); //NN e SP, tamanho caracteres
        // validaResumoConexoes()
        // validaRegistroEResumoDeConexoes()
        // processarArquivo()
    }

    private void mostraMensagemErroExecucao(final String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ooops!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public void executa() {
        try {
            executaFluxo();
        } catch (FinalizaExecucaoException e) {
            mostraMensagemErroExecucao(e.getMessage());
        }
    }
}
