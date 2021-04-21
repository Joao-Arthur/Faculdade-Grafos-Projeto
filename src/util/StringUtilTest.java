package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class StringUtilTest {
    @Test
    @DisplayName("Colocar aspas em um texto deve funcionar")
    public void testAspas() {
        assertEquals("\"\"", StringUtil.aspas(null));
        assertEquals("\"\"", StringUtil.aspas(""));
        assertEquals("\"Yellow Submarine\"", StringUtil.aspas("Yellow Submarine"));
    }
}
