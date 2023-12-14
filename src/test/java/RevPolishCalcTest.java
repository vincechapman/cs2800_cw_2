import application.model.calculators.InvalidExpression;
import application.model.calculators.RevPolishCalc;
import application.model.stacks.BadType;
import application.model.stacks.EmptyStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains all test cases for the RevPolishCalc class
 *
 * @author vchap
 */
public class RevPolishCalcTest {

  private RevPolishCalc calc;

  /**
   * This runs before each test and resets 'calc' to a fresh instance of 'RevPolishCalc'.
   */
  @BeforeEach
  public void initInstance() {
    calc = RevPolishCalc.getInstance();
  }

  /**
   * This test checks that our RevPolishCalc class exists and has correctly been assigned to the private field 'calc'.
   */
  @Test
  public void testingExistenceOfClass() {
    assertNotNull(calc);
  }

  /**
   * Tests the isOperand method. This method should return true for +, -, /, *
   * and false for anything else.
   */
  @Test
  public void testingIsOperand() {
    // Should evaluate to true
    assertTrue(calc.isOperand('+'));
    assertTrue(calc.isOperand('-'));
    assertTrue(calc.isOperand('/'));
    assertTrue(calc.isOperand('*'));

    // Should evaluate to false
    assertFalse(calc.isOperand('a'));
    assertFalse(calc.isOperand('1'));
    assertFalse(calc.isOperand('_'));
    assertFalse(calc.isOperand(' '));
  }

  /**
   * Testing the evaluate method returns the correct answer when a valid postfix expression is given,
   * and that it throws an appropriate exception when in invalid postfix expression is given.
   */
  @Test
  public void testingEvaluateMethod() throws InvalidExpression, BadType, EmptyStack {
    // Testing InvalidExpression is thrown when infix is set to true
    InvalidExpression exception = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("3 4 +", true));
    assertEquals(exception.getMessage(), "Please provide an expression in postfix form.");

    // Testing InvalidExpression is thrown when an empty expression is provided
    InvalidExpression exception2 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("", false));
    assertEquals(exception2.getMessage(), "Empty expression");

    // Testing that the following postfix expressions are evaluated correctly
    assertEquals(7, calc.evaluate("3 4 +", false));
    assertEquals(1, calc.evaluate("4 3 -", false));
    assertEquals(12, calc.evaluate("3 4 *", false));
    assertEquals(5, calc.evaluate("10 2 /", false));
    assertEquals(63, calc.evaluate("5 6 7 + * 2 -", false));

    // Testing Invalid Expression thrown if expression does not end with an operator
    InvalidExpression exception3 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("3 4", false));
    assertEquals("Expression does not end with an operator", exception3.getMessage());

    // Testing Invalid Expression thrown when not in proper <operand> <operand> <operator> form
    InvalidExpression exception4 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("4 +", false));
    assertEquals("Missing operand", exception4.getMessage());

    // Test Invalid Expression thrown if too many operands given compared to operators
    InvalidExpression exception5 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("3 4 5 +", false));
    assertEquals("Too many operands", exception5.getMessage());

    // Test Invalid Expression thrown if diving by zero at ANY point
    InvalidExpression exception6 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("3 0 /", false));
    assertEquals("Cannot divide by 0", exception6.getMessage());
    InvalidExpression exception7 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("5 10 + 3 3 - /", false));
    assertEquals("Cannot divide by 0", exception7.getMessage());

    // Testing Invalid expression and suitable message thrown when passing in invalid characters
    InvalidExpression exception8 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("5 10 p 3 3 - /", false));
    assertEquals("Invalid token found in expression: p", exception8.getMessage());

    // Testing suitable exception and message thrown when resulting answer is too large
    InvalidExpression exception9 = assertThrowsExactly(InvalidExpression.class, () -> calc.evaluate("9999999999999999999999999999999999999999999999999999999 999999999999999999999999999999999999999999999999999999999999999999 *", false));
    assertEquals("Resulting answer is too large", exception9.getMessage());
  }
}
