package util;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import junit.framework.TestCase;

@DisplayName("Colocar aspas em uma string deve funcionar")
public class StringUtilTest extends TestCase {
    @Test
    public void testAspasNull() {
        assertEquals("\"\"", StringUtil.aspas(null));
    }

    @Test
    public void testAspasTextoVazio() {
        assertEquals("\"\"", StringUtil.aspas(""));
    }

    @Test
    public void testAspasTexto() {
        assertEquals("\"Yellow Submarine\"", StringUtil.aspas("Yellow Submarine"));
    }
}
