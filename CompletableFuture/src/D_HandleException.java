import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class D_HandleException {

	public static void main(String[] args) throws Exception {

		ExecutorService service = Executors.newFixedThreadPool(5);

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			Utils.delay(5);
			System.out.println(10 / 0);
			return "Result from thread: " + Thread.currentThread().getName();
		}, service);

		CompletableFuture<String> resultFuture = future.handle((exception_result, ex) -> {
			if (ex != null) {
				System.out.println("Exception occurred: " + ex);
				return "Handled divide-by-zero gracefully";
			} else {
				return "Result: " + exception_result;
			}
		});
		String comResult = resultFuture.join();
		System.out.println(comResult);
	}

}
