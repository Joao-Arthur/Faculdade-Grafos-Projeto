package util;

public class StringUtil {
	public static String aspas(final String texto) {
		return texto == null ? "\"\"" : "\"" + texto + "\"";
	}
}
