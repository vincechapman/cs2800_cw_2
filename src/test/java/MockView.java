import application.ViewInterface;
import application.model.calculators.OpType;

import java.util.function.Consumer;

public class MockView implements ViewInterface {

  public Runnable doCalculation;
  public Consumer<OpType> type;
  public String answer;
  public boolean started = false;
  public String expression;

  public void setExpression(String newExpression) {
    this.expression = newExpression;
  }

  @Override
  public void addCalculateObserver(Runnable f) {
    this.doCalculation = f;
  }

  @Override
  public void addTypeObserver(Consumer<OpType> c) {
    this.type = c;
  }

  @Override
  public String getExpression() {
    return expression;
  }

  @Override
  public void setAnswer(String a) {
    this.answer = a;
  }

  @Override
  public void startView() {
    this.started = true;
    System.out.println("Started MockView");
  }
}
