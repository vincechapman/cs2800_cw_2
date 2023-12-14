package application.model.calculators;

import application.model.stacks.BadType;
import application.model.stacks.EmptyStack;
import application.model.stacks.NumStack;

/**
 * This class is responsible for evaluating expressions given in reverse polish form.
 *
 * @author vchap
 */
public class RevPolishCalc implements Calculator {

  private NumStack numStack;

  /**
   * Private constructor method.
   * Added this to prevent instances of RevPolishCalc being made without
   * going through the getInstance() class.
   */
  private RevPolishCalc() {}

  /**
   * Checks if the char arg is a valid operand.
   */
  public boolean isOperand(char c) {
    switch (c) {
      case '+':
      case '-':
      case '*':
      case '/':
        return true;
      default:
        return false;
    }
  }

  /**
   * Evaluates expressions given to this class.
   *
   * @param expression the expression to be calculated
   * @param infix how to evaluate the question: true means infix, false means reverse polish
   * @return the answer as float value
   * @throws InvalidExpression if the expression is not in proper reverse polish form
   */
  @Override
  public float evaluate(String expression, Boolean infix) throws InvalidExpression {

    // Quick validity checks
    if (infix) {
      throw new InvalidExpression("Please provide an expression in postfix form.");

    } else if (expression.isEmpty()) {
      throw new InvalidExpression("Empty expression");

    } else if (!isOperand(expression.charAt(expression.length() - 1))) {
      throw new InvalidExpression("Expression does not end with an operator");
    }

    // Instantiates/resets numStack
    numStack = new NumStack();

    // Operand variables
    float op1;
    float op2;

    try {
      // Looping through each part of the expression
      for (String part : expression.split("\\s+")) {

        // If part is operator pop last two values from stack and perform the relevant operation...
        switch (part) {
          case "+":
            op2 = numStack.pop();
            op1 = numStack.pop();
            numStack.push(op1 + op2);
            break;

          case "-":
            op2 = numStack.pop();
            op1 = numStack.pop();
            numStack.push(op1 - op2);
            break;

          case "*":
            op2 = numStack.pop();
            op1 = numStack.pop();
            numStack.push(op1 * op2);
            break;

          case "/":
            op2 = numStack.pop();
            op1 = numStack.pop();
            if (op2 == 0) {
              throw new InvalidExpression("Cannot divide by 0");
            }
            numStack.push(op1 / op2);
            break;

          default:
            // ...else assume it is a number and add it to numStack
            try {
              numStack.push(Float.parseFloat(part));

            } catch (Exception e) {
              // If an exception is thrown here then we know the original expression is invalid
              // as this part of the expression is neither a valid operator nor a number.
              throw new InvalidExpression("Invalid token found in expression: " + part);
            }
        }
      }

      float answer = numStack.pop();

      // If a valid postfix expression was provided initially, numStack should now be empty.
      if (numStack.size() != 0) {
        throw new InvalidExpression("Too many operands");
      } else if (Float.isInfinite(answer)) {
        throw new InvalidExpression("Resulting answer is too large");
      } else {
        return answer;
      }

    } catch (EmptyStack e) {
      // We will only ever get an EmptyStack exception if an invalid expression with
      // the incorrect number of operands is given initially.
      throw new InvalidExpression("Missing operand");

    } catch (BadType e) {
      // Handling BadType in case we somehow ended up with an invalid data type in numStack.
      throw new InvalidExpression("Invalid type found in NumStack");
    }
  }


  // Singleton Design Pattern

  private static RevPolishCalc singletonInstance;

  /**
   * This method creates an instance of RevPolishCalc IF one does not already exist.
   * This ensures there is only ever one instance of RevPolishCalc.
   *
   * @return an instance of RevPolishCalc
   */
  public static synchronized RevPolishCalc getInstance() {
    if (singletonInstance == null) {
      singletonInstance = new RevPolishCalc();
    }

    return singletonInstance;
  }
}
