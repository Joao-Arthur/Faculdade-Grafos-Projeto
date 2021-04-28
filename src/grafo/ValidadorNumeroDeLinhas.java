package grafo;

import util.StringUtil;

public class ValidadorNumeroDeLinhas {
    private final String nomeArquivo;
    private final TipoLinha tipoLinha;
    private final long numeroDeLinhas;

    public ValidadorNumeroDeLinhas(final String nomeArquivo, TipoLinha tipoLinha, long numeroDeLinhas) {
        this.nomeArquivo = nomeArquivo;
        this.tipoLinha = tipoLinha;
        this.numeroDeLinhas = numeroDeLinhas;
    }

    public void valida() {
        if (this.tipoLinha == null) {
            validaLinhasInvalidas();
            return;
        }

        switch (this.tipoLinha) {
            case CABECALHO:
            case TRAILER:
                validaMaximoLinhas();
            case CONEXAO:
            case PESO:
                validaMinimoLinhas();
                break;
        }
    }

    private void validaLinhasInvalidas() {
        if (numeroDeLinhas > 1)
            throw new ValidacaoGrafoException(
                    "O arquivo " + StringUtil.aspas(nomeArquivo) + " possui linhas inválidas!");
    }

    private void validaMinimoLinhas() {
        if (numeroDeLinhas < 1)
            throw new ValidacaoGrafoException("O arquivo " + StringUtil.aspas(nomeArquivo)
                    + " não possui nenhuma linha de " + StringUtil.aspas(this.tipoLinha.getDescricao()) + "!");
    }

    private void validaMaximoLinhas() {
        if (numeroDeLinhas > 1)
            throw new ValidacaoGrafoException("O arquivo " + StringUtil.aspas(nomeArquivo)
                    + " só pode ter uma linha de " + StringUtil.aspas(this.tipoLinha.getDescricao()) + "!");
    }
}
