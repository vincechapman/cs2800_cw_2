package application.model.stacks;

/**
 * An exception thrown by the 'Stack' class when the stack is empty and pop() and top().
 *
 * @author Vince Chapman
 */
public class EmptyStack extends Exception {
  public EmptyStack(String message) {
    super(message);
  }
}
