package application.model;

import application.model.calculators.Calculator;
import application.model.calculators.InvalidExpression;
import application.model.calculators.RevPolishCalc;
import application.model.calculators.StandardCalc;

/**
 * Evaluates an expression - the evaluation can be Standard (infix) or reverse polish.
 *
 * @author vchap
 */
public class CalcModel implements Calculator {

  private Calculator calc;

  @Override
  public float evaluate(String expression, Boolean infix) throws InvalidExpression {

    // Selecting the appropriate calculator based on infix boolean.
    calc = infix ? StandardCalc.getInstance() : RevPolishCalc.getInstance();

    return calc.evaluate(expression, infix);
  }
}
