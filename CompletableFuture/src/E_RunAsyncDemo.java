import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E_RunAsyncDemo {

	public static void main(String[] args) throws Exception {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println("Thread: " + Thread.currentThread().getName());
			int sum = 0;
			Utils.delay(2);
			for (int i = 1; i <= 5; i++) {
				sum += i;
			}
			System.out.println("Sum from 1 to 5 = " + sum);
		});
		future.get();
		System.out.println("Main thread: " + Thread.currentThread().getName());

		ExecutorService executor = Executors.newFixedThreadPool(1);
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
			System.out.println("\n\nThread: " + Thread.currentThread().getName());
			int sum = 0;
			Utils.delay(2);
			for (int i = 5; i <= 10; i++) {
				sum += i;
			}
			System.out.println("Sum from 5 to 10 = " + sum);
		}, executor);
		future2.get();
		System.out.println("Main thread: " + Thread.currentThread().getName());
	}

}
