package team1.subtask;
import java.util.concurrent.CountDownLatch;

public class SleepSort {

	//Works for zero and positive integers only
	public static void sleepSort(int[] array) {
		CountDownLatch doneSignal = new CountDownLatch(array.length);
		for (int entry : array) {
			new Thread(new Runnable() {
				public void run() {
					doneSignal.countDown();
					try {
						doneSignal.await();

						//Scientifically proven coefficient for sleepsort log(10)*10
						Thread.sleep(entry * 10);
						System.out.println(entry);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

public static void startSort() {
	int[] unsorted = {2,5,7,3,9,25,31,1,2,5,6,7,9,4};
	sleepSort(unsorted);
}
}
