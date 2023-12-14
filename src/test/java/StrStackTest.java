import application.model.stacks.BadType;
import application.model.stacks.EmptyStack;
import application.model.stacks.StrStack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * All test cases for the NumStack class
 *
 * @author vchap
 */
public class StrStackTest {

  private StrStack strStack;

  /**
   * Runs before each test case and resets strStack to a fresh instance of the StrStack class.
   */
  @BeforeEach
  public void initNumStack() {
    strStack = new StrStack();
  }

  /**
   * This test case asserts that strStack has been initialised properly
   */
  @Test
  public void testingStackConstructor() {
    assertNotNull(strStack);
    assertEquals(0, strStack.size());
  }

  /**
   * This test case asserts that NumStack displays correct size when a string is passed to the constructor
   */
  @Test
  public void testingStackConstructorWithString() {
    StrStack strStack1 = new StrStack("This is my string");
    assertNotNull(strStack1);
    assertEquals(1, strStack1.size());
  }

  /**
   * Tests that EmptyStack exception is thrown if stack is empty
   */
  @Test
  public void testingTopMethodOnEmptyClass() {
    assertThrowsExactly(EmptyStack.class, () -> strStack.top());
  }

  /**
   * Tests that push method works properly, and that top() returns the correct value after a
   * new string has been pushed to strStack
   */
  @Test
  public void testingPushMethod() throws BadType, EmptyStack {
    int originalSize = strStack.size();
    strStack.push("Blah blah");
    assertEquals(originalSize + 1, strStack.size());
    assertEquals("Blah blah", strStack.top());
  }

  /**
   * Tests that pop() method removes an item from stack, updates the stack size, and returns the
   * number that was removed from the stack.
   */
  @Test
  public void testingPopMethod() throws BadType, EmptyStack {
    // Asserts the stack is empty when we start the test
    assertEquals(0, strStack.size());
    assertThrowsExactly(EmptyStack.class, () -> strStack.top());

    // Asserting that EmptyStack exception is thrown if pop() called on empty stack
    assertThrowsExactly(EmptyStack.class, () -> strStack.pop());

    // Adding values to the stack
    strStack.push("This is a string");
    strStack.push("Another string");
    assertEquals(2, strStack.size());
    assertEquals("Another string", strStack.top());

    // Asserts that pop() returns the value that was removed
    assertEquals("Another string", strStack.pop());

    // Asserts that size has been updated accordingly
    assertEquals(1, strStack.size());

    // Assert that top now returns the new value at the top of the stack
    assertEquals("This is a string", strStack.top());
  }

  /**
   * Tests that the toString() method performs as expected
   */
  @Test
  public void testingToStringMethod() {
    // Testing that an empty string is printed initially
    assertEquals("[]", strStack.toString());

    // Pushing strings to the stack
    strStack.push("First");
    strStack.push("Second");
    strStack.push("3rd");

    // Testing that the strings we added are displayed in the string representation
    assertEquals("[First, Second, 3rd]", strStack.toString());
  }
}
