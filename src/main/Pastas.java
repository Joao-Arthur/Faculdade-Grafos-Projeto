package main;

public enum Pastas {
    RAIZ("C:\\Temp"),
    CONFIGURACAO(RAIZ.caminho + "\\Configuracao"),
    CONFIGURACAO_ARQUIVO(CONFIGURACAO.caminho + "\\config.txt"),
    PROCESSADO(RAIZ.caminho + "\\Processado"),
    NAO_PROCESSADO(RAIZ.caminho + "\\NaoProcessado");

    public final String caminho;

    Pastas(final String caminho) {
        this.caminho = caminho;
    }

}
