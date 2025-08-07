import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class F_SupplyAsyncDemo {

	public static void main(String[] args) throws Exception {

		CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread: " + Thread.currentThread().getName());
			Utils.delay(3);
			double average = (10 + 20 + 30) / 3.0;
			return average;
		});
		System.out.println("Average = " + future.get());
		System.out.println("Main thread: " + Thread.currentThread().getName());

		ExecutorService executor = Executors.newFixedThreadPool(1);
		CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("\n\nThread: " + Thread.currentThread().getName());
			Utils.delay(3);
			double average = (40 + 50 + 60) / 3.0;
			return average;
		}, executor);
		System.out.println("Average = " + future2.get());
		System.out.println("Main thread: " + Thread.currentThread().getName());
	}

}
