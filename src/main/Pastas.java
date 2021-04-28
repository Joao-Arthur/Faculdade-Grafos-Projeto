package main;

public enum Pastas {
    RAIZ("C:\\Temp"),
    CONFIGURACAO("C:\\Temp\\Configuracao"),
    CONFIGURACAO_ARQUIVO("C:\\Temp\\Configuracao\\config.txt"),
    PROCESSADO("C:\\Temp\\Processado"),
    NAO_PROCESSADO("C:\\Temp\\NaoProcessado");

    public final String caminho;

    Pastas(final String caminho) {
        this.caminho = caminho;
    }

}
