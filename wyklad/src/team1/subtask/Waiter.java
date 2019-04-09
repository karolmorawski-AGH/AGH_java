package team1.subtask;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable{

	CountDownLatch latch = null;

	public Waiter(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("waiter released");
	}
}
