import application.CalcController;
import application.model.CalcModel;
import application.CalcView;
import application.ViewInterface;
import application.model.calculators.OpType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All test cases for the CalcController class.
 *
 * @author vchap
 */
public class CalcControllerTest {

  private MockView mockView;
  private CalcController calcController;

  /**
   * Initialises/resets calcController before each test runs.
   */
  @BeforeEach
  public void initCalcController() {
    CalcModel calcModel = new CalcModel();
    mockView = new MockView();
    calcController = new CalcController(calcModel, mockView);

    mockView.addTypeObserver(calcController);
    mockView.addCalculateObserver(calcController);
    mockView.startView();
  }

  /**
   * Testing that CalcController can be instantiated without any errors.
   */
  @Test
  public void testingInitialised() {
    assertNotNull(calcController);
  }

  /**
   *
   */
  @Test
  public void testingRunnableForCalculating() {
    // Calculator will be in Reverse Polish by default
    mockView.setExpression("3 4 +");
    calcController.run();
    assertEquals("7.0", mockView.answer);

    // Switching to Standard Calculator
    mockView.type.accept(OpType.STANDARD);
    calcController.run();
    assertEquals("Invalid Expression: Expression cannot end with an operator", mockView.answer);

    mockView.setExpression("12 + 10");
    calcController.run();
    assertEquals("22.0", mockView.answer);
  }

  @Test
  public void testingConsumerForSwitchingTypes() {
    assertFalse(calcController.isInfix);

    calcController.accept(OpType.REV_POLISH);
    assertFalse(calcController.isInfix);

    calcController.accept(OpType.STANDARD);
    assertTrue(calcController.isInfix);

    calcController.accept(OpType.REV_POLISH);
    assertFalse(calcController.isInfix);
  }
}
