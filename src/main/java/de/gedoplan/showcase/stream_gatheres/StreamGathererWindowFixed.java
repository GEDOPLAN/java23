package de.gedoplan.showcase.stream_gatheres;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class StreamGathererWindowFixed {

  public static void main() {
//    windowFixed_hypothetical();
    windowFixed();
  }

  static void windowFixed_hypothetical() {
    // not possible, just hypothetical
    var result = Stream.iterate(0, i -> i + 1)
        // .windowFixed(4) // hypothetical
        .limit(3)
        .toList();
    // result ==> [[0, 1, 2, 3], [4, 5, 6, 7], [8, 9, 10, 11]]
  }

  private static void windowFixed() {
    var result = Stream.iterate(0, i -> i + 1)
        .gather(Gatherers.windowFixed(4))
        .limit(3)
        .toList();
    // result ==> [[0, 1, 2, 3], [4, 5, 6, 7], [8, 9, 10, 11]]
    System.out.println("windowFixed(4): " + result);

    var result2 = Stream.of(0, 1, 2, 3, 4, 5, 6)
        .gather(Gatherers.windowFixed(3))
        .toList();
    // result ==>[[0, 1, 2], [3, 4, 5], [6]]
    System.out.println("windowFixed(3): " + result2);
  }

}