package util;

public interface StringUtil {
    public static String aspas(final String texto) {
        return texto == null ? "\"\"" : "\"" + texto + "\"";
    }
}
