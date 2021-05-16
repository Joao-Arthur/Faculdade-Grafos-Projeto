package grafo;

public class Cabecalho extends Elemento {
    private final int totalNos;
    private final int somaPesos;

    public Cabecalho(final String conteudo) {
        super(conteudo);
        this.totalNos = Integer.parseInt(conteudo.substring(0, 2));
        this.somaPesos = Integer.parseInt(conteudo.substring(2));
    }

    @Override
    int getTamanhoMaximo() {
        return 100001;
    }

    @Override
    void validaConteudoLinha() {
    	//TODO
    }

    public void validaTotalNos(int totalNos) {
        if (this.totalNos != totalNos)
            throw new ValidacaoGrafoException("Total de nós difere entre o cabeçalho e as linhas!");
    }

    public void validaSomaPesos(int somaPesos) {
        if (this.somaPesos != somaPesos)
            throw new ValidacaoGrafoException("Soma dos pesos difere entre o cabeçalho e as linhas!");
    }
}
