package de.gedoplan.showcase.primitive_types_in_patterns_instanceof_and_switch;

public class PrimitiveTypesInPatternsInstanceofAndSwitch {

  public static void main() {
    checkByteOld(123);
    checkByteNew(123);
    checkByteOld(987);
    checkByteNew(987);
  }

  record LogLevel(int severity) {
  }

  static LogLevel logLevel = new LogLevel(2);

  static String msgOld = switch (logLevel.severity()) {
    case 0 -> "info";
    case 1 -> "warning";
    case 2 -> "error";
    default -> "unknown severity: " + logLevel.severity();
  };

  static String msgNew = switch (logLevel.severity()) {
    case 0 -> "info";
    case 1 -> "warning";
    case 2 -> "error";
    case int severity -> "unknown severity: " + severity;
  };

  static void checkByteOld(int value) {
    if (value >= -128 && value <= 127) {
      byte byteValue = (byte) value;
      System.out.println("byteValue = " + byteValue);
    } else {
      System.out.println("value is not in byte range");
    }
  }

  static void checkByteNew(int value) {
    if (value instanceof byte byteValue) {
      System.out.println("byteValue = " + byteValue);
    } else {
      System.out.println("value is not in byte range");
    }
  }
}
