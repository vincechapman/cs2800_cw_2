package application.model.stacks;

import java.util.ArrayList;
import java.util.List;

/**
 * Our custom stack class that implements Last-in, First-out list
 * functionality for our Entry instances.
 *
 * @author vchap
 */
public class Stack {

  private final List<Entry> entries;
  private int size;

  /**
   * Constructor method that takes no arguments.
   */
  public Stack() {
    entries = new ArrayList<Entry>();
    size = 0;
  }

  /**
   * Constructor method that takes an Entry object as an argument.
   * @param init an Entry object to be added to the stack when it is initialised
   */
  public Stack(Entry init) {
    entries = new ArrayList<Entry>();
    entries.add(init);
    size = 1;
  }

  // Methods

  /**
   * Returns the size of the stack.
   *
   * @return the size of the stack
   */
  public int size() {
    return size;
  }

  /**
   * Pushes an entry onto the stack.
   *
   * @param entry the entry to be pushed onto the stack
   */
  public void push(Entry entry) {
    entries.add(entry);
    size++;
  }

  /**
   * Retrieves the top element of the stack. (But does not remove it)
   *
   * @return the top element of the stack
   * @throws EmptyStack if the stack is empty
   */
  public Entry top() throws EmptyStack {
    if (size == 0) {
      throw new EmptyStack("Cannot call top() on empty stack");
    } else {
      return entries.get(size - 1);
    }
  }

  /**
   * Removes and returns the top element from the stack.
   *
   * @return the top element of the stack
   * @throws EmptyStack if the stack is empty
   */
  public Entry pop() throws EmptyStack {
    if (size == 0) {
      throw new EmptyStack("Cannot call pop() on empty stack");

    } else {
      Entry entry = entries.remove(size - 1);
      size--;
      return entry;

    }
  }

  /**
   * This method allows entries list to be printed.
   * @return The list entries as a meaningful string.
   */
  public String toString() {
    return entries.toString();
  }
}
