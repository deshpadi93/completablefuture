import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class G_ChainFuturesTogether {

	public static void main(String[] args) throws Exception {
		Executor executor = Executors.newFixedThreadPool(5);

		CompletableFuture<Void> futureChain = CompletableFuture.supplyAsync(() -> {
			System.out.println("Step 1: Getting user " + Thread.currentThread().getName());
			return "JohnDoe";
		}).thenApplyAsync(user -> {
			System.out.println("Step 2: Fetching email for " + user + " " + Thread.currentThread().getName());
			return user + "@example.com";
		}, executor).thenAcceptAsync(email -> {
			System.out.println("Step 3: Sending welcome email to " + email +" " + Thread.currentThread().getName());
		}, executor).thenRunAsync(() -> {
			System.out.println("Step 4: Logging completion (no input/output). " + Thread.currentThread().getName());
		}, executor);

		futureChain.get();
		System.out.println("All tasks completed.");

	}

}
