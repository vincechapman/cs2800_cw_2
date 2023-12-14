import application.model.calculators.InvalidExpression;
import application.model.calculators.StandardCalc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All test cases for the StandardCalc class.
 *
 * @author vchap
 */
public class StandardCalcTest {

  private StandardCalc infixCalc;

  /**
   * Initialises/resets infixCalc before each test in this class.
   */
  @BeforeEach
  public void instantiatingCalc() {
    infixCalc = StandardCalc.getInstance();
  }

  /**
   * Testing StandardCalc has been instantiated by @BeforeEach without any issues.
   */
  @Test
  public void testingInstantiation() {
    assertNotNull(infixCalc);
  }

  /**
   * Testing evaluate method throws correct exceptions and messages when an invalid expression is given,
   * and that it gives the correct answer when a valid expression is given.
   */
  @Test
  public void testingEvaluateMethod() throws InvalidExpression {
    // Check throws error if incorrect infix value
    InvalidExpression thrown1 = assertThrowsExactly(InvalidExpression.class, () -> infixCalc.evaluate("3 + 4", false));
    assertEquals("Infix argument should be true", thrown1.getMessage());

    // Check throws error if an empty expression is given
    InvalidExpression thrown2 = assertThrowsExactly(InvalidExpression.class, () -> infixCalc.evaluate("", true));
    assertEquals("Empty expression", thrown2.getMessage());

    // Check throws error if expression ends with an operator
    InvalidExpression thrown3 = assertThrowsExactly(InvalidExpression.class, () -> infixCalc.evaluate("3 4 +", true));
    assertEquals("Expression cannot end with an operator", thrown3.getMessage());
    InvalidExpression thrown4 = assertThrowsExactly(InvalidExpression.class, () -> infixCalc.evaluate("3 4 +  ", true));
    assertEquals("Expression cannot end with an operator", thrown4.getMessage());

    // Check correct answer is returned when a simple expression is given
    assertEquals(12, infixCalc.evaluate("3 * 4", true));
    assertEquals(-1, infixCalc.evaluate("3 - 4", true));
    assertEquals(5, infixCalc.evaluate("10 / 2", true));
    assertEquals(11, infixCalc.evaluate("8 + 3", true));

    // Check correct answer is returned when there are order of precedence implications
    assertEquals(11, infixCalc.evaluate("3 + 4 * 2", true));
    assertEquals(14, infixCalc.evaluate("( 3 + 4 ) * 2", true));
    assertEquals(63, infixCalc.evaluate("( 5 * ( 6 + 7 ) ) - 2", true));

    // Check throws appropriate error message for the following invalid expression
    InvalidExpression thrown5 = assertThrowsExactly(InvalidExpression.class, () -> infixCalc.evaluate("3 4", true));
    assertEquals("Please try again.", thrown5.getMessage());

    // FIX: Implement fix so that 3 4 * doesn't pass in infix mode
    InvalidExpression thrown6 = assertThrowsExactly(InvalidExpression.class, () -> infixCalc.evaluate("3 4 *", true));
    assertEquals("Expression cannot end with an operator", thrown6.getMessage());
  }
}
