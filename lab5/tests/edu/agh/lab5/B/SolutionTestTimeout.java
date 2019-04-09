package edu.agh.lab5.B;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SolutionTestTimeout {

	List<Integer> testArray = new ArrayList<Integer>();

	@Before
	public void  fillTestArray() {
		for(int i = 0; i<99999; i++) {
			testArray.add(1);
		}
	}

	@Test (timeout = 7000)
	public void timeoutWhileProcessingBigArray() throws EmptyListException, InvalidListSizeException {
		Solution instance = new Solution();
		instance.solution(testArray);
	}

}
