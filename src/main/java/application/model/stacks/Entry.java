package application.model.stacks;

/**
 * This class is for entries in the stack.
 *
 * @author Vince Chapman
 */
public class Entry {

  // Attributes (all are private to help ensure that the class is immutable)

  private float number;
  private Symbol other;
  private String str;
  private Type type;


  // Constructors

  public Entry(float value) {
    this.type = Type.NUMBER;
    this.number = value;
  }

  public Entry(String str) {
    this.type = Type.STRING;
    this.str = str;
  }

  public Entry(Symbol which) {
    this.type = Type.SYMBOL;
    this.other = which;
  }

  public Entry(char charArg) {
    this.type = Type.INVALID;
  }

  public Entry(Object... args) {
    this.type = Type.INVALID;
  }


  // Getter methods

  public Type getType() {
    return type;
  }

  /**
   * Returns the string value stored in this Entry object.
   *
   * @return the string value stored in this Entry object
   * @throws BadType if getString() is called on a non-string Entry object
   */
  public String getString() throws BadType {
    if (type != Type.STRING) {
      throw new BadType("getString() called on wrong type. This Entry object is of type: " + type);
    } else {
      return str;
    }
  }

  /**
   * Retrieves the symbol associated with this Entry object.
   *
   * @return the symbol associated with this Entry object
   * @throws BadType if getSymbol() is called on an Entry object of the wrong type
   */
  public Symbol getSymbol() throws BadType {
    if (type != Type.SYMBOL) {
      throw new BadType("getSymbol() called on wrong type. This Entry object is of type: " + type);
    } else {
      return other;
    }
  }

  /**
   * Returns the value of the Entry object.
   *
   * @return the value of the Entry object
   * @throws BadType if the Entry object is not of type NUMBER
   */
  public float getValue() throws BadType {
    if (type != Type.NUMBER) {
      throw new BadType("getValue() called on wrong type. This Entry object is of type: " + type);
    } else {
      return number;
    }
  }

  @Override
  public boolean equals(Object o) {
    try {
      // If both are referring to the same instance/object, return true
      if (this == o) {
        return true;
      }

      // If o is not an instance of Entry, return false
      if (!(o instanceof Entry)) {
        return false;
      }

      // At this point, we know o is an instance of Entry, so cast it to Entry
      Entry other = (Entry) o;

      // If type is different, return false
      if (this.getType() != other.getType()) {
        return false;
      }

      // Handle equality check based on the type of Entry.
      // At this point, we know both Entry instances are same type
      switch (this.getType()) {
        case NUMBER:
          // If number is different, return false
          if (this.getValue() != other.getValue()) {
            return false;
          }
          break;

        case STRING:
          // If str is different, return false
          if (!(this.getString().equals(other.getString()))) {
            return false;
          }
          break;

        case SYMBOL:
          // If symbol is different, return false
          if (this.getSymbol() != other.getSymbol()) {
            return false;
          }
          break;

        case INVALID:
          throw new BadType("equals() called on invalid type");

        default:
          return false;
      }

      // If reached here, the two instances of Entry are equal, therefore return true
      return true;

    } catch (BadType e) {
      // Shouldn't be possible to reach here, in theory
      // Just catching exception to get rid of error in IDE
      return false;
    }
  }

  @Override
  public int hashCode() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.type.hashCode());

    try {
      switch (this.getType()) {
        case STRING:
          sb.append(this.getString().hashCode());
          break;
        case SYMBOL:
          sb.append(this.getSymbol().hashCode());
          break;
        case NUMBER:
          sb.append(String.valueOf(this.getValue()).replace(".", ""));
          break;
        default:
          throw new BadType("hashCode() called on invalid type");
      }

    } catch (BadType e) {
      // Shouldn't be possible to reach here, in theory
      // Just catching exception to get rid of error in IDE
      return -1;
    }

    return sb.toString().hashCode();
  }

  @Override
  public String toString() {
    String str;

    switch (this.type) {
      case NUMBER:
        str = String.valueOf(this.number);
        break;
      case STRING:
        str = this.str;
        break;
      case SYMBOL:
        str = this.other.toString();
        break;
      case INVALID:
        str = "Invalid";
        break;
      default:
        str = "Unknown";
    };

    return str;
  }

  // No setter methods as Entry should be immutable once instantiated
}
