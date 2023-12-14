package application;

import application.model.calculators.OpType;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Skeleton code for a terminal based calculator that reads an expression from the user and
 * evaluates it and prints out the answer.
 */
public class AsciiView implements ViewInterface {
  // The current question that the calculator must solve: entered like ?3*(5+4)
  private String question;

  // This method will be injected so we can ask the controller to calculate
  Runnable doCalculation = null;

  // This method changes how the calculator will evaluate the question
  Consumer<OpType> setCalculatorType = null;


  private void menu() {
    Scanner s = new Scanner(System.in);
    boolean finished = false;
    help();

    while (!finished) {
      String t = s.nextLine();

      if (t.trim().isBlank()) {
        continue;
      }

      switch (t.trim().toUpperCase().charAt(0)) {
        case 'c':
        case 'C': // Ask the controller to calculate
          if (question == null || question.isBlank()) {
            System.out.println("Set an expression first!");

          } else {
            doCalculation.run();
          }

          break;

        case 's':
        case 'S':
          setCalculatorType.accept(OpType.STANDARD);
          System.out.println("Switched to: STANDARD CALC");
          break;

        case 'r':
        case 'R':
          setCalculatorType.accept(OpType.REV_POLISH);
          System.out.println("Switched to: REVERSE POLISH CALCULATOR");
          break;

        case 'e':
        case 'E':
          System.out.println("The current question is: " +  question);
          break;

        case '?': // Set current question
          System.out.println("What would you like to set the question to:");
          question = s.nextLine();
          System.out.println("Successfully set question to: " + question);
          break;

        case 'q':
        case 'Q':
          System.out.println("Bye");
          finished = true;
          break;

        default:
          help();
      }
    }
    s.close();
  }

  private void help() {
    System.out.println("Use one of the following:");
    System.out.println("  ? - to set expression");
    System.out.println("  E - to view the current expression");
    System.out.println("  C - to calculate");
    System.out.println("  S - change to a standard calculator");
    System.out.println("  R - change to a reverse polish calculator");
    System.out.println("  Q - to exit");
  }

  @Override
  public String getExpression() {
    return question;
  }

  @Override
  public void setAnswer(String answer) {
    System.out.println("Question: " + question);
    System.out.println("Answer: " + answer);
  }

  @Override
  public void addCalculateObserver(Runnable f) {
    this.doCalculation = f;
  }

  @Override
  public void addTypeObserver(Consumer<OpType> c) {
    this.setCalculatorType = c;
  }

  @Override
  public void startView() {
    menu();
  }

}
