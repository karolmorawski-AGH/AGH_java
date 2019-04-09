package team1.subtask;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

	//allows one or more threads to wait for a given set of operations to complete
	public static void startCountDownLatchExample() throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(3);

		Waiter  waiter = new Waiter(latch);
		Decrementer decrementer = new Decrementer(latch);

		new Thread(waiter).start();
		new Thread(decrementer).start();
		Thread.sleep(4000);
	}
}
