package application.model.stacks;

/**
 * The symbol enum class representing all the operands our calculator supports.
 *
 * @author vchap
 */
public enum Symbol implements Comparable<Symbol> {

  INVALID("invalid"),
  MINUS("-"),
  PLUS("+"),
  DIVIDE("/"),
  TIMES("*"),
  RIGHT_BRACKET(")"),
  LEFT_BRACKET("(");

  private final String string;

  private Symbol(String string) {
    this.string = string;
  }

  @Override
  public String toString() {
    return string;
  }
}
