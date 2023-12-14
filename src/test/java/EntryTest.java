import application.model.stacks.BadType;
import application.model.stacks.Entry;
import application.model.stacks.Symbol;
import application.model.stacks.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {

//    Commented out as this test now fails as I've developed the Entry class further
//    /**
//     * This test case checks constructor for the `Entry` class works, by creating an instance of `Entry` and ensuring it is not null.
//     * For this test case we are not passing any arguments into the constructor.
//     */
//    @Test
//    public void testingEmptyEntryConstructor() {
//        // Test 1
//        // Initially the test fails as we have not written an Entry class. To get this test to pass I had to create an Entry class
//        Entry entry = new Entry();
//        assertNotNull(entry);
//    }

    /**
     * TEST 2: Testing the constructors of the Entry class with arguments of type float or String.
     */
    @Test
    public void testingConstructorWithFloatOrString() {
        // Test 2
        // To get this test to pass I had to write constructor methods to take arguments of type float or String

        // Testing constructor with float
        float floatArg = 0.5f;
        Entry entry1 = new Entry(floatArg);
        assertNotNull(entry1);

        // Testing constructor with String
        String stringArg = "myString";
        Entry entry2 = new Entry(stringArg);
        assertNotNull(entry2);
    }

    /**
     * TEST 3: This test case checks constructor for the `Entry` class works when a Symbol is passed to the constructor
     */
    @Test
    public void testingConstructorWithSymbol() {
        // Test 3
        // To pass this test I had to write my `Symbol` class and then add a constructor to `Entry` that takes an instance of `Symbol` as an argument

        Symbol symbolArg = Symbol.LEFT_BRACKET;
        Entry entry3 = new Entry(symbolArg);
        assertNotNull(entry3);
    }

    /**
     * TEST 4: A test case for the `getType()` method, that asserts that the correct `Type` is returned when the `getType()` method is called
     * (depending on what value type was passed into the constructor)
     * E.g. Entry(1.0f).getType() should return Type.NUMBER
     */
    @Test
    public void testingGetTypeMethod() {
        // Test 4
        // Initially fails as no `Type` class has been written

        // To get this test to pass I had to:
            // 1) Write a Type class
            // 2) Add a getType() method to Entry
            // 3) Adjust my constructors to set the private attribute `type` accordingly

        // Testing Type.NUMBER
        Entry floatEntry = new Entry(1.0f);
        assertEquals(Type.NUMBER, floatEntry.getType());

        // Testing Type.STRING
        Entry stringEntry = new Entry("myString");
        assertEquals(Type.STRING, stringEntry.getType());

        // Testing Type.SYMBOL
        Entry symbolEntry = new Entry(Symbol.LEFT_BRACKET);
        assertEquals(Type.SYMBOL, symbolEntry.getType());
    }

    /**
     * TEST 5: A test case for the `getString()` method, that asserts that the correct string is returned when the `getString()` method is called.
     * This test assumes that getString() is being used properly
     * E.g. Entry("My string").getString() should return "My string"
     *
     * @throws BadType As we are not handling BadType exceptions here (this test case assumes getString() is being used properly)
     */
    @Test
    public void testingGetStringMethod() throws BadType {
        // Test 5
        // Initially fails as no getString() method has been written
        // To pass the test I had to:
            // 1) Write a getString() method to Entry
            // 2) Adjust my constructor to set the private attribute `str` when a string argument is passed

        Entry entry = new Entry("Test Driven Development");
        assertEquals("Test Driven Development", entry.getString());
    }

    /**
     * TEST 6: A test case that throws a BadType exception when getString() method is called on an Entry object that was not
     * constructed with a string argument.
     * E.g. Entry(1.0f).getString() should throw BadType exception
     */
    @Test
    public void testingGetStringMethodThrowsBadType() {
        // Test 6

        // Initially: test fails as we have not created a BadType exception or adjusted our getString method to throw it
        // To pass this test I had to:
            // 1) Create a custom exception: BadType which extends Exception
            // 2) Adjust the getString() method to throw BadType
            // 3) Adjust getString() to check type is Type.STRING before returning string value, else throw BadType with appropriate message

        // Asserting correct error is thrown when getString() is called on a float Entry
        Entry floatEntry = new Entry(1.0f);
        BadType thrown1 = assertThrowsExactly(BadType.class, () -> floatEntry.getString()); // TODO: Review slides to see if lectures uses method references instead of lambda functions e.g. floatEntry::getString instead of () -> floatEntry.getString() as we're currently getting a warning about this
        assertEquals("getString() called on wrong type. This Entry object is of type: NUMBER", thrown1.getMessage());

        // Asserting correct error is thrown when getString() is called on a Symbol Entry
        Entry symbolEntry = new Entry(Symbol.LEFT_BRACKET);
        BadType thrown2 = assertThrowsExactly(BadType.class, () -> symbolEntry.getString());
        assertEquals("getString() called on wrong type. This Entry object is of type: SYMBOL", thrown2.getMessage());

        // Asserting NO error is thrown when getString() is called on a string Entry
        Entry stringEntry = new Entry("myString");
        assertDoesNotThrow(() -> stringEntry.getString());
    }

    /**
     * TEST 7: A test case for the `getSymbol()` method, that asserts that the correct `Symbol` is returned when the `getSymbol()` method is called.
     */
    @Test
    public void testingGetSymbolMethod() throws BadType {
        // Test 7

        // Initially: the test fails as there is no getSymbol() method
        // To pass this test I had to:
            // 1) Write a getSymbol() method to Entry
            // 2) Adjust my constructor to set the private attribute `other` when a symbol argument is passed

        Entry symbolEntry = new Entry(Symbol.RIGHT_BRACKET);
        assertEquals(Symbol.RIGHT_BRACKET, symbolEntry.getSymbol());
    }

    /**
     * TEST 8: A test case that throws a BadType exception when getSymbol() method is called on an Entry object that was not
     */
    @Test
    public void testingBadTypeThrownForGetSymbolMethod() {
        // Test 8
        // Initially: test fails as we are not throwing BadType errors when getSymbol() is called on wrong type
        // To pass this test I had to:
            // Adjust getSymbol() so that it checks type is symbol before returning symbol value, else throw BadType with appropriate message

        Entry stringEntry = new Entry("This is a string");
        BadType thrown1 = assertThrowsExactly(BadType.class, () -> stringEntry.getSymbol());
        assertEquals("getSymbol() called on wrong type. This Entry object is of type: STRING", thrown1.getMessage());

        Entry numberEntry = new Entry(1.0f);
        BadType thrown2 = assertThrowsExactly(BadType.class, () -> numberEntry.getSymbol());
        assertEquals("getSymbol() called on wrong type. This Entry object is of type: NUMBER", thrown2.getMessage());

        Entry symbolEntry = new Entry(Symbol.LEFT_BRACKET);
        assertDoesNotThrow(() -> symbolEntry.getSymbol());
    }

    /**
     * TEST 9: A test case for the `getValue()` method, that asserts that the correct float is returned when the `getValue()` method is called
     */
    @Test
    public void testingGetValueMethod() throws BadType {
        // Test 9
        // Initially fails as we have not written a getValue method
        // To pass this test I had to:
            // 1) Write a getValue() method in Entry class
            // 2) Adjust constructor to set the private attribute `number` when a float argument is passed

        Entry floatEntry = new Entry(1.0f);
        assertEquals(1.0f, floatEntry.getValue());
    }

    /**
     * TEST 10: A test case that throws a BadType exception when getValue() method is called on an Entry object that was not
     */
    @Test
    public void testingBadTypeThrownForGetValueMethod() {
        // Test 10
        // Initially: test fails as we are not throwing BadType errors when getValue() is called on wrong type
        // To pass this test I had to:
            // Adjust getValue() so that it checks type is number before returning number value, else throw BadType with appropriate message

        // Should throw BadType exception
        Entry stringEntry = new Entry("This is a string");
        BadType thrown1 = assertThrowsExactly(BadType.class, () -> stringEntry.getValue());
        assertEquals("getValue() called on wrong type. This Entry object is of type: STRING", thrown1.getMessage());

        // Should throw BadType exception
        Entry symbolEntry = new Entry(Symbol.LEFT_BRACKET);
        BadType thrown2 = assertThrowsExactly(BadType.class, () -> symbolEntry.getValue());
        assertEquals("getValue() called on wrong type. This Entry object is of type: SYMBOL", thrown2.getMessage());

        // Should not throw anything as method is being used correctly
        Entry numberEntry = new Entry(1.0f);
        assertDoesNotThrow(() -> numberEntry.getValue());
    }

    /**
     * TEST 11: A test case that throws a BadType exception when constructor is called with invalid argument
     */
    @Test
    public void testingConstructorWithInvalidArgument() {
        // Test 11
        //
        //

        // Testing with char
        char charArg = 'a';
        Entry entry1 = new Entry(charArg);
        assertEquals(Type.INVALID, entry1.getType());

        // Testing with multiple arguments
        Entry entry2 = new Entry('a', 12, Symbol.DIVIDE);
        assertEquals(Type.INVALID, entry2.getType());

        // Testing with int array
        int[] arrEntry = new int[] {1, 2, 3};
        Entry entry3 = new Entry((Object) arrEntry);
        assertEquals(Type.INVALID, entry3.getType());
    }

    /**
     * TEST 12: A test case that compares equality based on type and value
     */
    @Test
    public void testingEqualsMethod() {
        // Test 12

        // Initially fails as we have not overridden equals method to determine equality based on type and value, so instead
        // it considers every instance of Entry as different, regardless of type or value

        // To pass this test I had to:
            // 1) Override equals method to check for equality based on type and value

        // Should be equal
        assertEquals(new Entry(1.0f), new Entry(1.0f));
        assertEquals(new Entry(1.0f), new Entry(1.000f));
        assertEquals(new Entry("This is a string"), new Entry("This is a string"));
        assertEquals(new Entry(Symbol.DIVIDE), new Entry(Symbol.DIVIDE));

        // Should not be equal
        assertNotEquals(new Entry(1.0f), new Entry(2.0f));
    }

    /**
     * TEST 13: A test case that compares hashcodes, and ensures appropriate hashcodes are returned
     */
    @Test
    public void testingHashcodeMethod() {
        // Test 13
        // Initially fails

        // Hashcodes should be the same
        assertEquals(new Entry(1.0f).hashCode(), new Entry(1.0f).hashCode());
        assertEquals(new Entry(1f).hashCode(), new Entry(1.000f).hashCode());
        assertEquals(new Entry("This is a string").hashCode(), new Entry("This is a string").hashCode());
        assertEquals(new Entry(Symbol.DIVIDE).hashCode(), new Entry(Symbol.DIVIDE).hashCode());

        // Hashcodes should be different
        assertNotEquals(new Entry(1.0f).hashCode(), new Entry(2.0f).hashCode());
        assertNotEquals(new Entry(1.0f).hashCode(), new Entry(10f).hashCode());
        assertNotEquals(new Entry("THIS IS A STRING").hashCode(), new Entry("this is a string").hashCode());
        assertNotEquals(new Entry("this is a string").hashCode(), new Entry("this is a string       ").hashCode());
        assertNotEquals(new Entry(Symbol.PLUS).hashCode(), new Entry(Symbol.MINUS).hashCode());
        assertNotEquals(new Entry(1f).hashCode(), new Entry("1.0").hashCode());
        assertNotEquals(new Entry(Symbol.RIGHT_BRACKET).hashCode(), new Entry(")").hashCode());

        // Testing hashcode() result for invalid type
        int[] arrEntry = new int[] {1, 2, 3};
        assertEquals(-1, new Entry((Object) arrEntry).hashCode());
    }

    /**
     * TEST 14: A test case that tests the toString method
     */
    @Test
    public void testingEntryIsPrintable() {
        // Test 14

        Entry floatEntry = new Entry(1.0f);
        assertEquals("1.0", floatEntry.toString());

        Entry stringEntry = new Entry("This is a string");
        assertEquals("This is a string", stringEntry.toString());

        Entry symbolEntry = new Entry(Symbol.RIGHT_BRACKET);
        assertEquals(")", symbolEntry.toString());
    }
}
