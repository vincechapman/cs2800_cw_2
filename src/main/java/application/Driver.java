package application;

import application.model.CalcModel;

/**
 * A driver class that sets up the MVC framework and begins the application.
 *
 * @author Dave Cohen (modified by vchap)
 */
public class Driver {

  /**
   * The entry point for the calculator.
   *
   * @param args if "-cli" flag passed as arg use AsciiView, otherwise use Gui
   */
  public static void main(String[] args) {

    // Use GUI if no -cli flag argument given
    boolean useGui = args.length == 0 || !args[0].equals("-cli");

    // Initialising Gui or Cli view based on user selection
    ViewInterface view = useGui ? CalcView.getInstance() : new AsciiView();

    // Initialising model that performs the calculations
    CalcModel model = new CalcModel();

    // Initialising calcControl which translates view interactions into actions for model
    CalcController calcController = new CalcController(model, view);

    // All ready so begin the interface.
    view.addCalculateObserver(calcController);
    view.addTypeObserver(calcController);
    view.startView();
  }
}
