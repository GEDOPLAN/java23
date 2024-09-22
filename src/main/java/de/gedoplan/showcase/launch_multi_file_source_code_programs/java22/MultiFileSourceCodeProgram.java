package de.gedoplan.showcase.launch_multi_file_source_code_programs.java22;

/**
 * Direct execution without compilation.
 * <p/>
 * Each class gets its own file.
 * Just call
 * <code>java LaunchMultiFileSourceCodeProgramsJava22.java</code>
 */
public class MultiFileSourceCodeProgram {
  public static void main(String[] args) {
    Helper helper = new Helper();
    System.out.println(helper.getMessage());
  }
}
