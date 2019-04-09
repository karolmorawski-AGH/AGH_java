package team1.subtask;


import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	private static SemaphoreExample instance = new SemaphoreExample();

	private int prime;
	private int connections = 0;

	//Creating semaphore
	private Semaphore sem = new Semaphore(10);

	private SemaphoreExample() {

	}

	public static SemaphoreExample getInstance() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			execute();
		}
		finally {
			sem.release();
		}
	}

	public void execute()  {

			synchronized (this) {
				connections++;
				System.out.println("Number of instances: " + connections);
			}

			//Some calculations
			try {
				if (!!(!!true == !!!true) && (true != !!false)) {
						prime = 2;
				} else {
					prime = -1;
				}
				Thread.sleep(200);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (this) {
				connections--;
			}

	}

}
