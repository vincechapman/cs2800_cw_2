package application.model.calculators;

import application.model.stacks.BadType;
import application.model.stacks.EmptyStack;
import application.model.stacks.OpStack;
import application.model.stacks.Symbol;

/**
 * Handles infix expressions.
 *
 * @author vchap
 */
public class StandardCalc implements Calculator {

  private OpStack values;
  private RevPolishCalc rpCalc;

  /**
   * Constructor method that initialised rpCalc with an instance of RevPolishCalc class.
   */
  private StandardCalc() {
    rpCalc = RevPolishCalc.getInstance();
  }

  /**
   * This method is responsible for converting an infix expression to postfix
   * and then passing it the evaluate method in RevPolishCalc.
   *
   * @param expression the question to be answered
   * @param infix      how to evaluate the question: true means infix, false means reverse polish
   * @return the answer, given as a float value
   * @throws InvalidExpression thrown if the expression provided is not in proper infix form
   */
  @Override
  public float evaluate(String expression, Boolean infix) throws InvalidExpression {

    expression = expression.trim(); // Removing any leading/trailing whitespaces

    // Quick validity checks
    if (!infix) {
      throw new InvalidExpression("Infix argument should be true");

    } else if (expression.isEmpty()) {
      throw new InvalidExpression("Empty expression");

    } else if ("+-*/".indexOf(expression.charAt(expression.length() - 1)) != -1) {
      throw new InvalidExpression("Expression cannot end with an operator");
    }

    // TODO: Add more checks for expression validity
    //  e.g. ( 5 * ( 6 7 ) - 2 is currently getting through

    // Initialising/resetting values (operator stack)
    values = new OpStack();

    // We'll append to this string as we piece the postfix expression together
    StringBuilder postfix = new StringBuilder();

    // Looping through each part of the expression
    for (String part : expression.split("\\s+")) {

      // If part is an operand/number, append it to the postfix string
      if (!"+-*/()".contains(part)) {
        try {
          Float.parseFloat(part);
          postfix.append(part).append(" ");

        } catch (Exception e) {
          throw new InvalidExpression("");
        }

        // Else if part is not an operand...
      } else {

        // Converting part to equivalent Symbol enum and assigning to operator variable
        Symbol operator;
        switch (part) {
          case "+":
            operator = Symbol.PLUS;
            break;
          case "-":
            operator = Symbol.MINUS;
            break;
          case "*":
            operator = Symbol.TIMES;
            break;
          case "/":
            operator = Symbol.DIVIDE;
            break;
          case "(":
            operator = Symbol.LEFT_BRACKET;
            break;
          case ")":
            operator = Symbol.RIGHT_BRACKET;
            break;
          default:
            throw new InvalidExpression("Invalid token given: " + part);
        }

        try {
          // If part is "(" push to operator stack
          if (operator.equals(Symbol.LEFT_BRACKET)) {
            values.push(operator);

            // Else if part is ")"...
          } else if (operator.equals(Symbol.RIGHT_BRACKET)) {
            while (!values.top().equals(Symbol.LEFT_BRACKET)) {
              // ...pop from stack and append to postfix string until top of stack is "("...
              postfix.append(values.pop()).append(" ");
            }

            // ...then discard "(" that was in stack
            values.pop();

            // Else if part is not parenthesis
          } else {

            // Checking if the stack currently contains a "("
            boolean containsLeftBracket = values
                    .toString()
                    .contains(Symbol.LEFT_BRACKET.toString());

            // Loop while top of stack has higher precedence and stack is not empty
            // or until all the values above most recent left bracket have been popped
            while (values.size() != 0 && values.top().ordinal() > operator.ordinal()
                    && (!containsLeftBracket || !values.top().equals(Symbol.LEFT_BRACKET))) {
              // Pop from stack and append to postfix expression
              postfix.append(values.pop()).append(" ");
            }

            values.push(operator);  // Push operator to OpStack
          }

        } catch (EmptyStack e) {
          // Isn't possible to reach here, as we already know size is not zero when top() is called
          // Just clearing an IDE warning
          throw new InvalidExpression("top() called on an empty stack");

        } catch (BadType e) {
          throw new InvalidExpression("Invalid type found in OpStack");
        }
      }
    }

    // Pop any remaining operators and append to postfix expression until stack is empty
    while (values.size() > 0) {
      try {
        postfix.append(values.pop()).append(" ");

      } catch (EmptyStack e) {
        throw new InvalidExpression("Stack is empty: " + e.getMessage());

      } catch (BadType e) {
        throw new InvalidExpression("Invalid type in operator stack: " + e.getMessage());
      }
    }

    try {
      // Passes the postfix expression to RevPolishCalc.evaluate() and returns the answers
      return rpCalc.evaluate(postfix.toString().trim(), false);
    } catch (InvalidExpression e) {
      // Overwriting the error thrown by rpCalc evaluate as it is not relevant to infix calculator.
      throw new InvalidExpression("Please try again.");
    }
  }


  // Singleton Design Pattern

  private static StandardCalc singletonInstance;

  /**
   * This method creates an instance of StandardCalc IF one does not already exist.
   * This ensures there is only ever one instance of StandardCalc.
   *
   * @return an instance of StandardCalc
   */
  public static synchronized StandardCalc getInstance() {
    if (singletonInstance == null) {
      singletonInstance = new StandardCalc();
    }

    return singletonInstance;
  }
}
