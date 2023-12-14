import org.junit.jupiter.api.Test;
import application.model.stacks.Type;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All tests for the Type enum
 *
 * @author Vince Chapman
 */
public class TypeTest {

    /**
     * TEST 1: A test case that asserts all enum constants can be assigned to a variable without any exceptions being thrown
     */
    @Test
    public void testingEnumTypeWorks() {
        assertDoesNotThrow(() -> {
            Type strType = Type.STRING;
            Type symType = Type.SYMBOL;
            Type numType = Type.NUMBER;
            Type invType = Type.INVALID;
        });
    }

    /**
     * TEST 2: This test case checks the `toString()` method for the `Type` enum works.
     */
    @Test
    public void testingTypeIsPrintable() {
        Type strType = Type.STRING;
        Type symType = Type.SYMBOL;
        Type numType = Type.NUMBER;
        Type invType = Type.INVALID;

        assertEquals("STRING", strType.toString());
        assertEquals("SYMBOL", symType.toString());
        assertEquals("NUMBER", numType.toString());
        assertEquals("INVALID", invType.toString());

    }
}
