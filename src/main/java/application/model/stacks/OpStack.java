package application.model.stacks;

/**
 * This builds on the Stack class, and tailors specifically for storing Symbol values.
 *
 * @author vchap
 */
public class OpStack {

  private final Stack opStack;

  /**
   * Constructor that does not take an argument.
   */
  public OpStack() {
    opStack = new Stack();
  }

  /**
   * Constructor that takes a symbol as an argument.
   * @param symbol The symbol we want to initialise the stack with.
   */
  public OpStack(Symbol symbol) {
    opStack = new Stack(new Entry(symbol));
  }

  /**
   * Returns the current size of opStack.
   *
   * @return the size of opStack given as an integer.
   */
  public int size() {
    return opStack.size();
  }

  /**
   * Returns the Symbol at the top of the stack, without removing it.
   *
   * @return the Symbol at the top of the stack.
   * @throws EmptyStack if no Symbols have been added to the stack when top() is called.
   * @throws BadType if top() is called on the wrong Stack type
   */
  public Symbol top() throws EmptyStack, BadType {
    return opStack.top().getSymbol();
  }

  /**
   * Adds a new symbol to opStack.
   * @param sym The symbol we want to add to the stack.
   */
  public void push(Symbol sym) {
    opStack.push(new Entry(sym));
  }

  /**
   * Removes the symbol at the top of the stack and returns it.
   *
   * @return the symbol that was removed from the stack.
   * @throws EmptyStack if stack is empty when pop() is called.
   * @throws BadType if stack is of the wrong type.
   */
  public Symbol pop() throws EmptyStack, BadType {
    return opStack.pop().getSymbol();
  }

  /**
   * A method that displays a printable string representation of the OpStack.
   * TODO: Add test case for this method
   *
   * @return a meaningful string representing the StrStack
   */
  public String toString() {
    return opStack.toString();
  }
}
