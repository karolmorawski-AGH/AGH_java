package team1.subtask;

import java.util.concurrent.CountDownLatch;

	public class Decrementer implements Runnable {

		CountDownLatch latch = null;

		public Decrementer(CountDownLatch latch) {
			this.latch = latch;
		}

		public void run() {

			try {
				Thread.sleep(1000);
				this.latch.countDown();
				System.out.println("3");
				Thread.sleep(1000);
				this.latch.countDown();
				System.out.println("2");
				Thread.sleep(1000);
				this.latch.countDown();
				System.out.println("1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
