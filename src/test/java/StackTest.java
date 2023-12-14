import application.model.stacks.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    /**
     * This test case checks if the constructor of the `Stack` class works properly.
     * It calls the `Stack` class constructor and asserts that the returned object is not null.
     */
    @Test
    public void testingStackConstructor() {
        // Test 1
        // Initially we had not written a Stack class, so the test fails. To get this test to pass I just had to create a Stack class
        Stack stack = new Stack();
        assertNotNull(stack);
    }

    /**
     * A test case for the `size()` method when the stack is empty.
     * This test case checks if the `size()` method of the `Stack` class returns the correct size when the stack is empty.
     * It creates an instance of the `Stack` class and calls the `size()` method on it. Since the stack is empty, the expected
     * size is 0. The test asserts that the actual size returned by the `size()` method is equal to the expected size.
     */
    @Test
    public void testingSizeOnEmptyStack() {
        // Test 2
        // Initially there is no size() method, so the test fails. To get this test to pass I had to add a size() method and I faked a result of 0
        Stack stack = new Stack();
        assertEquals(0, stack.size());
    }

    /**
     *
     */
    @Test
    public void testingPushingAnEntryToTheStack() {
        // Test 3
        // Initially fails as we have not added a push method to the stack class
        // To get this test to pass I had to:
            // 1) Add a push method to the Stack class
            // 2) Add an entries attribute which is a list of entries (ArrayList)
            // 3) Add a size attribute which is 0 initially
            // 4) Increment the value of size by 1 when push method is called
            // 5) Modify size() method to now return the value of size attribute, rather than a faked value of 0

        // Instantiating Entry and Stack objects
        Stack stack = new Stack();
        Entry entry = new Entry("My string");

        // Size of stack should be 0 as it is empty
        assertEquals(0, stack.size());

        // Pushing entry to stack
        stack.push(entry);

        // Size of stack should now be 1
        assertEquals(1, stack.size());
    }

    /**
     * This test case simply ensures that EmptyStack exception is thrown when top() is called on an empty stack,
     * and no exception is thrown when top() is called on a non-empty stack
     */
    @Test
    public void testingEmptyStackExceptionOnTopMethod() {
        // Test 4
        // Initially fails we have not written a top method OR an EmptyStack exception
        // To pass this test I had to:
            // 1) Write a top method for the Stack class
            // 2) Write an EmptyStack exception
            // 3) Within top() method, check if size is 0, if so throw EmptyStack exception

        // Instantiating Stack object
        Stack stack = new Stack();

        // Testing EmptyStack exception thrown, as no entries have been pushed to the stack
        EmptyStack thrown1 = assertThrowsExactly(EmptyStack.class, () -> stack.top());

        // Testing EmptyStack exception displays correct message
        assertEquals("Cannot call top() on empty stack", thrown1.getMessage());

        // Instantiating Entry object
        Entry entry = new Entry(1.0f);

        // Pushing entry to stack
        stack.push(entry);

        // Testing top() does NOT throw any exception as stack isn't empty and top() is being used properly
        assertDoesNotThrow(() -> stack.top());
    }

    /**
     *
     */
    @Test
    public void testingTopMethodReturnsCorrectValue() throws EmptyStack, BadType {
        // Test 5
        //

        // Instantiating Stack object and Entry object
        Stack stack = new Stack();
        Entry entry = new Entry(1.0f);

        // Pushing entry to stack and checking size is correct
        stack.push(entry);
        assertEquals(1, stack.size());

        // Testing top() returns correct value
        assertEquals(1.0f, stack.top().getValue());

        // Testing pushing a string to the stack
        stack.push(new Entry("My string"));
        assertEquals(2, stack.size());

        // Testing top() returns correct value
        assertEquals("My string", stack.top().getString());

        // Testing still throws BadType exception correctly
        assertThrowsExactly(BadType.class, () -> stack.top().getValue());
    }

    /**
     *
     */
    @Test
    public void testingEmptyStackExceptionOnPopMethod() {
        // Test 6
        //

        // Instantiating Stack object
        Stack stack = new Stack();

        // Ensuring stack is empty
        assertEquals(0, stack.size());

        // Ensuring EmptyStack exception is thrown
        EmptyStack thrown = assertThrowsExactly(EmptyStack.class, () -> stack.pop());

        // Ensuring correct error message is displayed by exception
        assertEquals("Cannot call pop() on empty stack", thrown.getMessage());
    }

    /**
     *
     */
    @Test
    public void testingPopMethod() throws EmptyStack, BadType {
        // Test 7
        //

        Stack stack = new Stack();

        assertEquals(0, stack.size());

        stack.push(new Entry(1.0f));
        assertEquals(1, stack.size());

        stack.push(new Entry("This is my test string"));
        assertEquals(2, stack.size());

        assertEquals("This is my test string", stack.pop().getString());
        assertEquals(1, stack.size());

        assertEquals(1.0f, stack.top().getValue());
    }

    /**
     * This test case simply ensures that Stack can be extended indefinitely i.e. there is no fixed length of the stack,
     * other than those imposed by java or system limits.
     */
    @Test
    public void testingStackCanBeExtendedIndefinitely() {
        // Test 8
        // Didn't have to change anything to get this test to pass

        Stack stack = new Stack();
        stack.push(new Entry(1.0f));
        stack.push(new Entry("This is my test string"));
        stack.push(new Entry(2.0f));
        stack.push(new Entry("Another test string"));
        stack.push(new Entry(Symbol.LEFT_BRACKET));
        stack.push(new Entry(3.0f));
        stack.push(new Entry(1.0f));
        stack.push(new Entry("This is my test string"));
        stack.push(new Entry(2.0f));
        stack.push(new Entry("Another test string"));
        stack.push(new Entry(Symbol.LEFT_BRACKET));
        stack.push(new Entry(3.0f));
        stack.push(new Entry(1.0f));
        stack.push(new Entry("This is my test string"));
        stack.push(new Entry(2.0f));
        stack.push(new Entry("Another test string"));
        stack.push(new Entry(Symbol.LEFT_BRACKET));
        stack.push(new Entry(3.0f));
        assertEquals(18, stack.size());
    }

    /**
     * Testing Stack displays a meaningful string when printed
     */
    @Test
    public void testingToStringMethod() {
        Stack stack = new Stack();
        stack.push(new Entry(1.0f));
        stack.push(new Entry("This is a string"));
        stack.push(new Entry(Symbol.RIGHT_BRACKET));
        stack.push(new Entry(Symbol.LEFT_BRACKET));
        System.out.println("Stack: " + stack);
        assertEquals("[1.0, This is a string, ), (]", stack.toString());
    }
}
