package de.gedoplan.showcase.unnamed_variables_and_patterns;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UnnamedVariable {

  void unnamedVariable1() {
    try {
      Thread.sleep(1000);
      System.out.println("done sleeping");
    } catch (InterruptedException _) {          // unnamed variable
      System.out.println("somehow the thread was interrupted!");
    }
  }

  Optional<Integer> unnamedVariable2() {
    try (FileReader reader = new FileReader("foo.txt")) {
      int data = reader.read();
      return Optional.of(data);
    } catch (IOException _) {                   // unnamed variable
      return Optional.empty();
    }
  }

  void unnamedVariable3() {
    List<String> names = Arrays.asList("Klaus", "Bärbel", "Peter", "Paul", "Mary");
    for (var _ : names) {
      System.out.println("Processing ...");
    }
    System.out.println("done");
  }

  void unnamedVariable4() {
    List<String> names = Arrays.asList("Klaus", "Bärbel", "Peter", "Paul", "Mary");
    names.forEach(_ -> System.out.println("Processing ..."));
    System.out.println("done");
  }
}
