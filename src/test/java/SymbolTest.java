import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import application.model.stacks.Symbol;

import java.util.Arrays;


public class SymbolTest {

    /**
     * TEST 1: Testing the Symbol enum works.
     */
    @Test
    public void testingSymbolClass() {
        // Test 1
        // Initially we had not written a Symbol enum, so the test fails. To get this test to pass I had to create a Symbol enum
        Symbol symbol = Symbol.LEFT_BRACKET;
        assertNotNull(symbol);
    }

    /**
     * TEST 2: Testing that the Symbol enum works for all valid values
     */
    @Test
    public void testingSymbolWithValidValues() {
        // Test 2

        // To get this test to pass I added all the enum constants as outlined in the requirements.
        assertDoesNotThrow(() -> {
            Symbol sym1 = Symbol.LEFT_BRACKET;
            assertNotNull(sym1);

            Symbol sym2 = Symbol.RIGHT_BRACKET;
            assertNotNull(sym2);

            Symbol sym3 = Symbol.TIMES;
            assertNotNull(sym3);

            Symbol sym4 = Symbol.DIVIDE;
            assertNotNull(sym4);

            Symbol sym5 = Symbol.PLUS;
            assertNotNull(sym5);

            Symbol sym6 = Symbol.MINUS;
            assertNotNull(sym6);

            Symbol sym7 = Symbol.INVALID;
            assertNotNull(sym7);
        });
    }

    /**
     * TEST 3: Write a test for the toString method
     * Test that each Symbol returns a "nice" string when toString is called on it.
     * E.g. Symbol.LEFT_BRACKET should return "("
     */
    @Test
    public void testingSymbolIsPrintable() {
        // Test 3

        // To get this test to pass I had to override the toString method and use a switch case to return
        // the correct "nice" string for each of the symbols

        assertEquals("(", Symbol.LEFT_BRACKET.toString());
        assertEquals(")", Symbol.RIGHT_BRACKET.toString());
        assertEquals("*", Symbol.TIMES.toString());
        assertEquals("/", Symbol.DIVIDE.toString());
        assertEquals("+", Symbol.PLUS.toString());
        assertEquals("-", Symbol.MINUS.toString());
        assertEquals("invalid", Symbol.INVALID.toString());
    }

    /**
     * Testing comparability in line with order of precedence
     * e.g. Symbol.TIMES > Symbol.PLUS == true
     */
    @Test
    public void testingOrderOfPrecedence() {
        Symbol[] symbols = new Symbol[]{
                Symbol.MINUS,
                Symbol.RIGHT_BRACKET,
                Symbol.DIVIDE,
                Symbol.PLUS,
                Symbol.TIMES,
                Symbol.LEFT_BRACKET,
                Symbol.INVALID
        };

        Arrays.sort(symbols);

        assertEquals("[invalid, -, +, /, *, ), (]", Arrays.toString(symbols));
    }
}
