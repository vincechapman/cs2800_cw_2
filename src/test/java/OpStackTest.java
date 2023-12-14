import application.model.stacks.BadType;
import application.model.stacks.EmptyStack;
import application.model.stacks.OpStack;
import application.model.stacks.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All the test cases for OpStack class
 *
 * @author vchap
 */
public class OpStackTest {

  private OpStack opStack;

  /**
   * Runs before each test case and resets opStack to a fresh instance of the opStack class.
   */
  @BeforeEach
  public void initOpStack() {
    opStack = new OpStack();
  }

  /**
   * This test case asserts that opStack has been initialised properly
   */
  @Test
  public void testingStackConstructors() {
    assertNotNull(opStack);
    assertEquals(0, opStack.size());

    OpStack opStack2 = new OpStack(Symbol.DIVIDE);
    assertEquals(1, opStack2.size());
  }

  /**
   * Tests that EmptyStack exception is thrown if stack is empty
   */
  @Test
  public void testingTopMethodOnEmptyClass() {
    assertThrowsExactly(EmptyStack.class, () -> opStack.top());
  }

  /**
   * Tests that push method works properly, and that top() returns the correct value after a
   * new number has been pushed to OpStack
   */
  @Test
  public void testingPushMethod() throws BadType, EmptyStack {
    int originalSize = opStack.size();
    opStack.push(Symbol.PLUS);
    assertEquals(originalSize + 1, opStack.size());
    assertEquals(Symbol.PLUS, opStack.top());
  }

  /**
   * Tests that pop() method removes a symbol from stack, updates the stack size, and returns the
   * symbol that was removed from the stack.
   */
  @Test
  public void testingPopMethod() throws BadType, EmptyStack {
    // Asserts the stack is empty when we start the test
    assertEquals(0, opStack.size());
    assertThrowsExactly(EmptyStack.class, () -> opStack.top());

    // Asserting that EmptyStack exception is thrown if pop() called on empty stack
    assertThrowsExactly(EmptyStack.class, () -> opStack.pop());

    // Adding values to the stack
    opStack.push(Symbol.LEFT_BRACKET);
    opStack.push(Symbol.MINUS);
    assertEquals(2, opStack.size());
    assertEquals(Symbol.MINUS, opStack.top());

    // Asserts that pop() returns the value that was removed
    assertEquals(Symbol.MINUS, opStack.pop());

    // Asserts that size has been updated accordingly
    assertEquals(1, opStack.size());

    // Assert that top now returns the new value at the top of the stack
    assertEquals(Symbol.LEFT_BRACKET, opStack.top());
  }
}
