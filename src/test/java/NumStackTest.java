import application.model.stacks.BadType;
import application.model.stacks.EmptyStack;
import application.model.stacks.NumStack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * All test cases for the NumStack class
 *
 * @author vchap
 */
public class NumStackTest {

  private NumStack numStack;

  /**
   * Runs before each test case and resets numStack to a fresh instance of the NumStack class.
   */
  @BeforeEach
  public void initNumStack() {
    numStack = new NumStack();
  }

  /**
   * This test case asserts that NumStack has been initialised properly
   */
  @Test
  public void testingStackConstructor() {
    assertNotNull(numStack);
    assertEquals(0, numStack.size());
  }

  /**
   * This test case asserts that NumStack displays correct size when a float is passed to the constructor
   */
  @Test
  public void testingStackConstructorWithFloat() {
    NumStack numStack1 = new NumStack(3.0f);
    assertNotNull(numStack1);
    assertEquals(1, numStack1.size());
  }

  /**
   * Tests that EmptyStack exception is thrown if stack is empty
   */
  @Test
  public void testingTopMethodOnEmptyClass() {
    assertThrowsExactly(EmptyStack.class, () -> numStack.top());
  }

  /**
   * Tests that push method works properly, and that top() returns the correct value after a
   * new number has been pushed to NumStack
   */
  @Test
  public void testingPushMethod() throws BadType, EmptyStack {
    int originalSize = numStack.size();
    numStack.push(2.0f);
    assertEquals(originalSize + 1, numStack.size());
    assertEquals(2.0f, numStack.top());
  }

  /**
   * Tests that pop() method removes an item from stack, updates the stack size, and returns the
   * number that was removed from the stack.
   */
  @Test
  public void testingPopMethod() throws BadType, EmptyStack {
    // Asserts the stack is empty when we start the test
    assertEquals(0, numStack.size());
    assertThrowsExactly(EmptyStack.class, () -> numStack.top());

    // Asserting that EmptyStack exception is thrown if pop() called on empty stack
    assertThrowsExactly(EmptyStack.class, () -> numStack.pop());

    // Adding values to the stack
    numStack.push(3.4f);
    numStack.push(7.3f);
    assertEquals(2, numStack.size());
    assertEquals(7.3f, numStack.top());

    // Asserts that pop() returns the value that was removed
    assertEquals(7.3f, numStack.pop());

    // Asserts that size has been updated accordingly
    assertEquals(1, numStack.size());

    // Assert that top now returns the new value at the top of the stack
    assertEquals(3.4f, numStack.top());
  }
}
