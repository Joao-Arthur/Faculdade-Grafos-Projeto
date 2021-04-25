package grafo;

public class ElementoFactory {
	public static IElemento cria(TipoLinha tipoLinha) {
		switch (tipoLinha) {
			case CABECALHO:
				return new Cabecalho();
			case CONEXAO:
				return new Conexao();
			case PESO:
				return new Peso();
			case TRAILER:
				return new Trailer();
			default:
				return null;
		}
	}
}
