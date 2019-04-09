package team1.subtask;

import java.util.concurrent.ExecutionException;

import static team1.subtask.SyncExamples.syncExamplesStart;

public class Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		/*
		//startSort();
		ThreadsCreatingExample handle = new ThreadsCreatingExample();
		handle.startExamples();
		*/

		//syncexamples
		syncExamplesStart();
		//CountDownLatchExample.startCountDownLatchExample();
	}
}
