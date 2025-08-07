
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class B_WhyNotFuture {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(5);

		Future<List<Integer>> future = service.submit(() -> {
			System.out.println("Thread : " + Thread.currentThread().getName());
			// add delay to get result from api call
			delay(1);
			// limitation 4: no mechanism to handle such exceptions here
			// System.out.println(10/0);
			return Arrays.asList(1, 2, 3, 4);
		});

		// limitation 1: No future.complete() method to complete it manually
		List<Integer> integers = future.get();
		System.out.println(integers);
		// limitation 2: you can not chain this future to pass it to another task
		// you do not have thenApply() methods available here
		// limitation 3: you can not combine multiple futures together
		// we do not have future.thenCombine() methods available
	}

	private static void delay(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
