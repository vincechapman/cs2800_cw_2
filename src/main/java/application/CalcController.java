package application;

import application.model.CalcModel;
import application.model.calculators.InvalidExpression;
import application.model.calculators.OpType;
import java.util.function.Consumer;

/**
 * The controller that sits between the calculator model that does actual evaluation and the view
 * that is the part the user interfaces with.
 *
 * @author vchap (fields and methods initially outlined by Dave Cohen)
 */
public class CalcController implements Consumer<OpType>, Runnable {

  private final CalcModel model;
  private final ViewInterface view;
  public boolean isInfix;

  /**
   * Constructor method, requires both model and view arguments.
   *
   * @param model an instance of CalcModel
   * @param view  an instance of ViewInterface (e.g. AsciiView or CalcView/GuiView)
   */
  public CalcController(CalcModel model, ViewInterface view) {
    this.model = model;
    this.view = view;
    this.isInfix = false; // As Gui is set to rev polish notation (postfix) by default
  }

  /**
   * Grabs expression from user input field and evaluates it using model
   * and current infix boolean value. If exception thrown, displays error message
   * in the answer field on the GUI.
   */
  private void handleCalculation() {
    try {
      float answer = model.evaluate(view.getExpression(), this.isInfix);
      view.setAnswer(String.valueOf(answer));

    } catch (InvalidExpression e) {
      view.setAnswer("Invalid Expression: " + e.getMessage());
    }
  }

  /**
   * Toggles infix boolean between true and false.
   */
  private void handleTypeChange() {
    this.isInfix = !this.isInfix;
  }

  @Override
  public void run() {
    handleCalculation();
  }

  @Override
  public void accept(OpType opType) {
    // Switches if this.infix does not match opType
    if ((opType.equals(OpType.STANDARD) && !this.isInfix)
            || (opType.equals(OpType.REV_POLISH) && this.isInfix)) {
      handleTypeChange();
    }
  }
}
