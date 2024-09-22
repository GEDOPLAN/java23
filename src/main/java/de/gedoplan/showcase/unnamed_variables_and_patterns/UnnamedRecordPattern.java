package de.gedoplan.showcase.unnamed_variables_and_patterns;

public class UnnamedRecordPattern {

  double calculateAreaOld(Shape shape) {
    return switch (shape) {
      case Point(int x, int y) -> 0;
      case Square(Point c, int s) -> s * s;
      case ColoredSquare(Square(Point upperLeft, int s), Color c) -> s * s;
    };
  }

  double calculateAreaNew(Shape shape) {
    return switch (shape) {
      case Point _ -> 0;
      case Square(_, int s) -> s * s;
      case ColoredSquare(Square(_, int s), _) -> s * s;
    };
  }
}

sealed interface Shape {}
record Point(int x, int y) implements Shape {}
record Square(Point upperLeft, int sideLength) implements Shape {}
record Color(int red, int green, int blue) {}
record ColoredSquare(Square square, Color colour) implements Shape {}

