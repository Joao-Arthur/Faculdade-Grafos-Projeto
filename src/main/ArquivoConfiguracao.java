package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.StringUtil;

public class ArquivoConfiguracao {
	private static final int NUMERO_LINHAS_ARQUIVO = 2;
	private static final int PARTES_CONTEUDO_LINHA = 3;
	private static final String[] LINHAS_PADRAO = new String[] { "Processado=C:\\Temp\\Processado",
			"Não Processado=C:\\Temp\\NaoProcessado" };

	public void cria(final String filePath) throws FinalizaExecucaoException {
		try {
			final Path path = Paths.get(filePath);
			if (Files.exists(path) && Files.isRegularFile(path))
				return;
			Files.createFile(Paths.get(filePath));
		} catch (IOException e) {
			throw new FinalizaExecucaoException(
					"Ocorreu um erro ao criar o arquivo " + StringUtil.aspas(filePath) + "!");
		}
	}

	public void valida(final String filePath) throws FinalizaExecucaoException {
		try {
			final Path path = Paths.get(filePath);
			final List<String> linhas = Files.readAllLines(path);
			final int numeroLinhas = linhas.size();
			if (numeroLinhas < NUMERO_LINHAS_ARQUIVO) {
				List<String> list = new ArrayList<>(Arrays.asList(LINHAS_PADRAO));
				Files.write(path, list);
			} else if (numeroLinhas == NUMERO_LINHAS_ARQUIVO) {
				for (String linha : linhas)
					validaLinhaArquivo(linha);
			} else {
				throw new FinalizaExecucaoException("O número de linhas do arquivo está incorreto!");
			}
		} catch (IOException e) {
			throw new FinalizaExecucaoException("Ocorreu um erro ao validar o conteúdo do arquivo de configuração!");
		}
	}

	private void validaLinhaArquivo(final String linha) throws FinalizaExecucaoException {
		final String[] conteudoLinha = linha.split("=");
		if (conteudoLinha.length < PARTES_CONTEUDO_LINHA || conteudoLinha.length > PARTES_CONTEUDO_LINHA)
			throw new FinalizaExecucaoException(
					"A linha " + StringUtil.aspas(linha) + " não é uma configuração válida!");
		final String chave = conteudoLinha[0];
		final String valor = conteudoLinha[2];
	}
}
