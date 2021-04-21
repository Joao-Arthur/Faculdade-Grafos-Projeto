package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import java.nio.file.AccessDeniedException;
import util.StringUtil;

public class ExecucaoGrafos {
	private final static String TEMP_FOLDER_PATH = "C:\\Temp";
	private final static String CONFIGURACAO_FOLDER_PATH = "C:\\Temp\\Configuracao";
	private final static String CONFIG_FILE_PATH = "C:\\Temp\\Configuracao\\config.txt";

	private void criaPasta(final String phisicalPath) throws FinalizaExecucaoException {
		final Path path = Paths.get(phisicalPath);
		try {
			if (Files.exists(path)) {
				if (Files.isDirectory(path))
					return;
				Files.delete(path);
			}
			Files.createDirectory(path);
		} catch (AccessDeniedException e) {
			throw new FinalizaExecucaoException("Não foi possível deletar o arquivo " + StringUtil.aspas(phisicalPath)
					+ ". Verifique suas permissões!");
		} catch (IOException e) {
			throw new FinalizaExecucaoException(
					"Ocorreu um erro ao criar a pasta " + StringUtil.aspas(phisicalPath) + "!");
		}
	}

	private void criaArquivoConfiguracao(final String filePath) throws FinalizaExecucaoException {
		new ArquivoConfiguracao().cria(filePath);
	}

	private void validaArquivoConfiguracao(final String filePath) throws FinalizaExecucaoException {
		new ArquivoConfiguracao().valida(filePath);
	}

	private void executaFluxo() throws FinalizaExecucaoException {
		criaPasta(ExecucaoGrafos.TEMP_FOLDER_PATH);
		criaPasta(ExecucaoGrafos.CONFIGURACAO_FOLDER_PATH);
		criaArquivoConfiguracao(ExecucaoGrafos.CONFIG_FILE_PATH);
		validaArquivoConfiguracao(ExecucaoGrafos.CONFIG_FILE_PATH);
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
