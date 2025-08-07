import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class I_CombineFuturesTogether {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Fetching user...");
			return "John";
		}, executor);

		CompletableFuture<String> welcomeFuture = userFuture.thenCompose(user -> CompletableFuture.supplyAsync(() -> {
			System.out.println("Generating welcome message...");
			return "Welcome, " + user + "!";
		}, executor));

		System.out.println(welcomeFuture.get());

		CompletableFuture<Integer> priceFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("\n\nGetting base price...");
			return 100;
		}, executor);

		CompletableFuture<Integer> taxFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Calculating tax...");
			return 20;
		}, executor);

		CompletableFuture<Integer> totalFuture = priceFuture.thenCombine(taxFuture, (price, tax) -> {
			System.out.println("Combining price and tax...");
			return price + tax;
		});

		System.out.println("Total price: " + totalFuture.get());

		executor.shutdown();

	}

}
