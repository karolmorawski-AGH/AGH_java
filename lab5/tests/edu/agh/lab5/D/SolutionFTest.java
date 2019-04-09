package edu.agh.lab5.D;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionFTest {

	float testArr[] =  {2, 7, 11, 15};
	float target = 18;
	int[] expectedindexes = {1,2};
	int[] alternativeindexes = {2,1};


	@Test
	public void checkIfCorrectOutput() throws Exception {
		SolutionF  sol = new SolutionF();
		int[] output = sol.solution(testArr, target);

		//assertTrue  doesnt seem to work properly
		if((output[0] != 1 && output[1] !=2) && (output[0] != 2 && output[1] !=1)) { fail(); }
	}

	@Test (expected = Exception.class)
	public void checkIfThrowsExceptionWhenNullArray() throws Exception {
		float arr[] = null;
		SolutionF  sol = new SolutionF();
		int[] output = sol.solution(arr, target);
	}

	@Test
	public void checkIfNoPossibleAnwser() throws EmptyArrayTargetException {
		SolutionF  sol = new SolutionF();
		assertNull(sol.solution(testArr, 666));
	}



}