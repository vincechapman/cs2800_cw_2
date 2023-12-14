package application.model.stacks;

/**
 * This builds on the Stack class, and tailors specifically for storing float values.
 *
 * @author vchap
 */
public class StrStack {

  private final Stack stack;

  /**
   * A constructor with no arguments - initialises an empty stack.
   */
  public StrStack() {
    stack = new Stack();
  }

  /**
   * A constructor that takes a string argument, and initialises stack
   * with this argument as the first value.
   *
   * @param str a string we want to add to the stack when we're initialising the stack
   */
  public StrStack(String str) {
    stack = new Stack(new Entry(str));
  }

  /**
   * A method for adding strings to the stack.
   *
   * @param str the string we want to add to the stack
   */
  public void push(String str) {
    stack.push(new Entry(str));
  }

  /**
   * Returns the current size of the string stack.
   *
   * @return the size of the stack, given as an integer
   */
  public int size() {
    return stack.size();
  }

  /**
   * Returns the value at the top of the stack, without removing it
   * or changing the stack in any way.
   *
   * @return The value at the top of the stack.
   * @throws EmptyStack if stack is empty when top() method is called
   * @throws BadType if this method is called on the wrong stack type
   */
  public String top() throws EmptyStack, BadType {
    return stack.top().getString();
  }

  /**
   * Removes the value that is at the top of the stack and returns it.
   *
   * @return The value that was removed from the stack.
   * @throws EmptyStack if stack is empty when pop() method is called
   * @throws BadType if this method is called on the wrong stack type
   */
  public String pop() throws EmptyStack, BadType {
    return stack.pop().getString();
  }

  /**
   * A method that displays a printable string representation of the StrStack.
   *
   * @return a meaningful string representing the StrStack
   */
  public String toString() {
    return stack.toString();
  }
}
