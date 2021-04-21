package main;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import util.StringUtil;

public class GerenciadorSistemaArquivos {
    public static void criaPasta(final String caminhoPasta) {
        final Path path = Paths.get(caminhoPasta);
        try {
            if (Files.exists(path) && Files.isDirectory(path))
                return;
            if (Files.exists(path))
                Files.delete(path);
            Files.createDirectory(path);
        } catch (AccessDeniedException e) {
            throw new FinalizaExecucaoException("Não foi possível deletar o arquivo " + StringUtil.aspas(caminhoPasta)
                    + ". Verifique suas permissões!");
        } catch (IOException e) {
            throw new FinalizaExecucaoException(
                    "Ocorreu um erro ao criar a pasta " + StringUtil.aspas(caminhoPasta) + "!");
        }
    }

    public static void criaArquivo(final String caminhoArquivo) {
        try {
            final Path path = Paths.get(caminhoArquivo);
            if (Files.exists(path) && Files.isRegularFile(path))
                return;
            Files.createFile(path);
        } catch (IOException e) {
            throw new FinalizaExecucaoException(
                    "Ocorreu um erro ao criar o arquivo " + StringUtil.aspas(caminhoArquivo) + "!");
        }
    }
}
