import application.model.CalcModel;
import application.model.calculators.InvalidExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All test cases for the CalcModel class.
 *
 * @author vchap
 */
public class CalcModelTest {

  private CalcModel calcModel;

  /**
   * Initialises/resets calcModel before each test in this class.
   */
  @BeforeEach
  public void initCalcModel() {
    calcModel = new CalcModel();
  }

  /**
   * Testing that calcModel has been instantiated successfully.
   */
  @Test
  public void testingInstantiating() {
    assertNotNull(calcModel);
  }

  /**
   * Testing the evaluate method.
   */
  @Test
  public void testingEvaluateMethod() throws InvalidExpression {
    // Initially testing with faked result of 0.0f
    // assertEquals(0.0f, calcModel.evaluate("", true));

    // Testing infix expressions return correct answer when used correctly
    assertEquals(12, calcModel.evaluate("3 * 4", true));
    assertEquals(-1, calcModel.evaluate("3 - 4", true));
    assertEquals(5, calcModel.evaluate("10 / 2", true));
    assertEquals(11, calcModel.evaluate("8 + 3", true));
    assertEquals(11, calcModel.evaluate("3 + 4 * 2", true));
    assertEquals(14, calcModel.evaluate("( 3 + 4 ) * 2", true));
    assertEquals(63, calcModel.evaluate("( 5 * ( 6 + 7 ) ) - 2", true));

    // Testing appropriate exception and message thrown when infix set to false on infix expression
    InvalidExpression thrown1 = assertThrowsExactly(InvalidExpression.class, () -> calcModel.evaluate("3 * 4", false));
    assertEquals("Expression does not end with an operator", thrown1.getMessage());

    // Testing postfix expressions return correct answer when used correctly
    assertEquals(7, calcModel.evaluate("3 4 +", false));
    assertEquals(1, calcModel.evaluate("4 3 -", false));
    assertEquals(12, calcModel.evaluate("3 4 *", false));
    assertEquals(5, calcModel.evaluate("10 2 /", false));
    assertEquals(63, calcModel.evaluate("5 6 7 + * 2 -", false));

    // Testing appropriate exception and message thrown when infix set to true on postfix expression
    InvalidExpression thrown2 = assertThrowsExactly(InvalidExpression.class, () -> calcModel.evaluate("3 4 +", true));
    assertEquals("Expression cannot end with an operator", thrown2.getMessage());
  }
}
