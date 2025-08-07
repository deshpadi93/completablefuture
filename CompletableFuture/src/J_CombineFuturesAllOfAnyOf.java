import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class J_CombineFuturesAllOfAnyOf {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Task 1: " + Thread.currentThread().getName());
			return 10;
		}, executor);

		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Task 2: " + Thread.currentThread().getName());
			return 20;
		}, executor);

		CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Task 3: " + Thread.currentThread().getName());
			return 30;
		}, executor);

		CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

		CompletableFuture<List<Integer>> combinedResults = allFutures.thenApply(v -> {
			try {
				return List.of(future1.get(), future2.get(), future3.get());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		System.out.println("All results: " + combinedResults.get());

		CompletableFuture<Object> any = CompletableFuture.anyOf(future1, future2, future3);
		System.out.println("First completed result: " + any.get());

		executor.shutdown();
	}

}
