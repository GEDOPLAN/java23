package de.gedoplan.showcase.structured_concurrency;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrency {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
//    executorService();
    structuredConcurrencyShutdownOnFailure();
  }

  // auf true setzen, um das Verhalten beim Fehlschlag von fetchOrder zu beobachten
  private static boolean failFetchingOrder = true;

  public static void executorService() throws ExecutionException, InterruptedException {
    System.out.println("--- ExecutorService ---");
    try (ExecutorService executorService = Executors.newCachedThreadPool()) {
      var userFuture = executorService.submit(() -> findUser());
      Future<Integer> orderFuture = executorService.submit(() -> fetchOrder());

      String user = userFuture.get();
      Integer order = orderFuture.get();

      UserOrder userOrder = new UserOrder(user, order);
      printWithThreadName("userOrder = " + userOrder);
    }
  }

  public static void structuredConcurrencyShutdownOnFailure() throws InterruptedException, ExecutionException {
    System.out.println("--- Structured Concurrency ShutdownOnFailure ---");

    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      var findUserTask = scope.fork(() -> findUser());
      var fetchOrderTask = scope.fork(() -> fetchOrder());

      scope.join() // blockiert, bis beide Teilaufgaben beendet sind
           .throwIfFailed(); // ... Abbruch, wenn eine Teilaufgabe fehlschlägt

      // beide Teilaufgaben erfolgreich, Ergebnisse zusammenführen
      UserOrder userOrder = new UserOrder(findUserTask.get(), fetchOrderTask.get());
      printWithThreadName("userOrder = " + userOrder);
    }
  }

  public void structuredConcurrencyShutdownOnSuccess() throws ExecutionException, InterruptedException {
    System.out.println("--- Structured Concurrency ShutdownOnSuccess ---");
    try (var scope = new StructuredTaskScope.ShutdownOnSuccess<NetworkConnection>()) {
      scope.fork(() -> tryToGetWifi());
      scope.fork(() -> tryToGet5g());
      scope.fork(() -> tryToGet4g());
      scope.join();
      printWithThreadName("found connection: " + scope.result());
    }
  }

  private static String findUser() {
    printWithThreadName("   searching user - takes a while");
    sleepSeconds(4);
    printWithThreadName("   found user");
    return "Klaus";
  }

  private static Integer fetchOrder() {
    printWithThreadName("   fetching order - should be quick");
    sleepSeconds(2);
    if (failFetchingOrder) {
      throw new RuntimeException("error while fetching order");
    }
    printWithThreadName("   found order");
    return 17;
  }

  private static void sleepSeconds(int seconds) {
    try {
      SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      printWithThreadName("was interrupted!");
      throw new RuntimeException(e);
    }
  }

  private static void printWithThreadName(String message) {
    System.out.println(Thread.currentThread() + " " + message);
  }

  private NetworkConnection tryToGetWifi() {
    sleepSeconds(5);
    return new NetworkConnection("WiFi");
  }

  private NetworkConnection tryToGet5g() {
    sleepSeconds(3);
    return new NetworkConnection("5G");
  }

  private NetworkConnection tryToGet4g() {
    sleepSeconds(4);
    return new NetworkConnection("4G");
  }
}

record UserOrder(String user, int orderId) {}
record NetworkConnection(String type) {}