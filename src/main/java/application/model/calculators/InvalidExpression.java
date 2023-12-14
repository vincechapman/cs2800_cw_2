package application.model.calculators;

/**
 * An expression was passed to a method that could not be evaluated.
 */
public class InvalidExpression extends Exception {
  public InvalidExpression(String message) {
    super(message);
  }
}
