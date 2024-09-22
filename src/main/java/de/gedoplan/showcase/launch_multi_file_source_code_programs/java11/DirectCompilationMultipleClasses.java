package de.gedoplan.showcase.launch_multi_file_source_code_programs.java11;

/**
 * Direct execution without compilation.
 * <p/>
 * All classes have to be in the same file.
 * Call
 * <code>java DirectCompilation.java</code>
 */
public class DirectCompilationMultipleClasses {
  public static void main(String[] args) {
    Helper helper = new Helper();
    System.out.println(helper.getMessage());
  }
}

class Helper {
  public String getMessage() {
    return "Hello from Java 11 Helper class!";
  }
}
