import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C_CompleteFutureManually {

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(5);

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			Utils.delay(5);
			return "Result from thread: " + Thread.currentThread().getName();
		}, service);

		// System.out.println(future.get());
		future.complete("Completing the Future Manually");
		System.out.println(future.get());

	}

}
