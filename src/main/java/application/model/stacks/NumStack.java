package application.model.stacks;

/**
 * This builds on the Stack class, and tailors specifically for storing float values.
 *
 * @author vchap
 */
public class NumStack {

  private final Stack stack;

  /**
   * A constructor with no arguments - initialises an empty stack.
   */
  public NumStack() {
    stack = new Stack();
  }

  /**
   * A constructor that takes a float argument, and initialises stack
   * with this argument as the first value.
   *
   * @param num a number we want to add to the stack when we're initialising the stack
   */
  public NumStack(float num) {
    stack = new Stack(new Entry(num));
  }

  /**
   * A method for adding numbers to the stack.
   *
   * @param num the number we want to add to the stack
   */
  public void push(float num) {
    stack.push(new Entry(num));
  }

  /**
   * Returns the current size of the num stack.
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
   * @throws BadType if this method is somehow called on a String, Symbol, or OpType stack
   */
  public float top() throws EmptyStack, BadType {
    return stack.top().getValue();
  }

  /**
   * Removes the value that is at the top of the stack and returns it.
   *
   * @return The value that was removed from the stack.
   * @throws EmptyStack if stack is empty when pop() method is called
   * @throws BadType if this method is somehow called on a String, Symbol, or OpType stack
   */
  public float pop() throws EmptyStack, BadType {
    return stack.pop().getValue();
  }
}
