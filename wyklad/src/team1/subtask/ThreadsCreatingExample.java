package team1.subtask;

import javax.xml.crypto.Data;
import java.util.concurrent.*;

public class ThreadsCreatingExample {

	//1# Extends Thread Class
	public class BasicThread extends Thread {

		public void run() {
			//Displays thread name and id
			System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId());
		}
	}

	//#2 Implementing Runnable Inteface
	public class RunnableThread implements Runnable {


		public void setName(String name) {
			Thread.currentThread().setName(name);
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId());
		}

	}

	//#3 Implementing Callable and Future
	public class CallableThread implements Callable<String[]> {


		public void setName(String name) {
			Thread.currentThread().setName(name);
		}

		@Override
		public String[] call() throws Exception {
			String[] returnval = {Thread.currentThread().getName(), String.valueOf(Thread.currentThread().getId())};
			return returnval;
		}
	}

	public void startExamples() throws ExecutionException, InterruptedException {

		//Class Thread
		for(int i = 0; i<5; i++) {
			BasicThread worker = new BasicThread();
			String name = "Worker" + i;
			worker.setName(name);
			worker.start();
		}


		//Runnable interface
		for(int i = 0; i<5; i++) {
			RunnableThread worker = new RunnableThread();
			String name = "Worker" + i;
			Thread thread = new Thread(worker);
			thread.setName(name);
			thread.start();
		}

		//Callable interface + Future + Executor
		ExecutorService service = Executors.newFixedThreadPool(5);

		for(int i = 0; i<5; i++) {
			CallableThread worker = new CallableThread();
			String name = "Worker" + i;
			worker.setName(name);
			Future<String[]> future = service.submit(worker);
			String[] returnedarray = future.get();
			System.out.println(returnedarray[0] + " " + returnedarray[1]);
		}
		service.shutdown();
	}
}