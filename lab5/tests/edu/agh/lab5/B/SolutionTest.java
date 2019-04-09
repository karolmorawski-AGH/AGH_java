package edu.agh.lab5.B;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

	List<Integer> testArray = new ArrayList<Integer>();
	List<Integer> negativeTestArray = new ArrayList<Integer>();
	List<Integer> bigarray = new ArrayList<Integer>();
	int expectedresult;

	@Before
	public void fillTestArray() {
		testArray.add(1);
		testArray.add(3);
		testArray.add(6);
		testArray.add(4);
		testArray.add(1);
		testArray.add(2);

		negativeTestArray.add(-5);
		negativeTestArray.add(-2);
		negativeTestArray.add(-3);

		for(int i = 0; i<100005; i++) {
		bigarray.add(1);
		}

		expectedresult = 5;
	}

	@Test
	public void checkIfExpectedResultCorrect() throws EmptyListException, InvalidListSizeException {
		Solution instance = new Solution();
		assertEquals(instance.solution(testArray), expectedresult);
	}

	//Since exceptions handles it now return value does not exist
	/*@Test
	public void checkEmptyArray() throws EmptyListException, InvalidListSizeException {
		Solution instance = new Solution();
		List<Integer> nullarray = new ArrayList<Integer>();
		assertEquals(instance.solution(nullarray), -1);
	}*/

	@Test
	public void checkArrayWithNegativeNumbersOnly() throws EmptyListException, InvalidListSizeException {
		Solution instance = new Solution();
		assertEquals(instance.solution(negativeTestArray), 1);
	}

	//TODO FIx this test -> EmptyArrayException
	@Test (expected = EmptyListException.class)
	public void checkEmpyArrayException() throws EmptyListException, InvalidListSizeException {
		Solution instance = new Solution();
		List<Integer> nullarray = new ArrayList<Integer>();
		instance.solution(nullarray);
	}

	//TODO FIx this test -> InvalidListSizeException
	@Test (expected = InvalidListSizeException.class)
	public void checkInvalidListSizeException() throws EmptyListException, InvalidListSizeException {
		Solution instance = new Solution();
		instance.solution(bigarray);
	}

}