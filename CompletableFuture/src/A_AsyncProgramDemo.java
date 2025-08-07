import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class A_AsyncProgramDemo {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable runnableTask = () -> {
			System.out.println("Runnable Task is running on thread: " + Thread.currentThread().getName());
		};
		executor.execute(runnableTask);

		Callable<String> callableTask = () -> {
			return "Callable Task is running on thread: " + Thread.currentThread().getName();
		};
		Future<String> future = executor.submit(callableTask);
		try {
			Thread.sleep(10000);
			String result = future.get();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
