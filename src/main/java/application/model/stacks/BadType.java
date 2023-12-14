package application.model.stacks;

/**
 * An exception that is thrown when a method is called on an 'Entry' object of the wrong type.
 *
 * @author Vince Chapman
 */
public class BadType extends Exception {
  public BadType(String message) {
    super(message);
  }
}
