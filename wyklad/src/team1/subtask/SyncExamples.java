package team1.subtask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SyncExamples {
	public static void syncExamplesStart() throws InterruptedException {

		ExecutorService executor = Executors.newCachedThreadPool();

		for(int i =0; i<200; i++) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					SemaphoreExample.getInstance().connect();
				}
			});
	}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
}
