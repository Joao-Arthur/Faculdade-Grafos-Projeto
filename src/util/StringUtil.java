package util;

public class StringUtil {
	public static String aspas(String texto) {
		return texto == null ? "\"\"" : "\"" + texto + "\"";
	}
}
