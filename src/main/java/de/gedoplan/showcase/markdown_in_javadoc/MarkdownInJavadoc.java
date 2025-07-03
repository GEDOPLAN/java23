package de.gedoplan.showcase.markdown_in_javadoc;


/// # Markdown in Javadoc
/// ## some subheading
/// - list item 1
/// - list item 2
public class MarkdownInJavadoc {

  // Copy from java.lang.Math
  /**
   * Returns the smaller of two {@code int} values. That is,
   * the result the argument closer to the value of
   * {@link Integer#MIN_VALUE}. If the arguments have the same
   * value, the result is that same value.
   *
   * @param   a   an argument.
   * @param   b   another argument.
   * @return  the smaller of {@code a} and {@code b}.
   */
  public static int min(int a, int b) {
    return (a <= b) ? a : b;
  }

  // same in Markdown

  /// Returns the smaller of two `int` values. That is,
  /// the result is the argument closer to the value of
  /// [Integer#MIN_VALUE]. If the arguments have the same
  /// value, the result is that same value.
  ///
  /// @param a an argument.
  /// @param b another argument.
  /// @return the smaller of `a` and `b`.
  public static int min2(int a, int b) {
    return (a <= b) ? a : b;
  }
}
