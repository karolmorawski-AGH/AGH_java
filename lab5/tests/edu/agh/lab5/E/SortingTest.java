package edu.agh.lab5.E;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;


public class SortingTest {

	private int[] testArray = { 64,70,29,43,78,30,12,84,41,11,81,67,80,54,31,12,23,95,59,96};
	private int[] expectedResult = {11,12,12,23,29,30,31,41,43,54,59,64,67,70,78,80,81,84,95,96};

	@Test
	public void bubbleSort() {
		Sorting srt = new Sorting();
		int[] result = srt.bubbleSort(testArray);
		assertArrayEquals(expectedResult, result);
	}

	@Test
	public void selectionsort() {
		Sorting srt = new Sorting();
		int[] result = srt.selectionsort(testArray);
		assertArrayEquals(expectedResult, result);
	}

	@Test
	public void heapsort() {
		Sorting srt = new Sorting();
		int[] result = srt.heapsort(testArray);
		assertArrayEquals(expectedResult, result);
	}

	@Test
	public void insertionsort() {
		Sorting srt = new Sorting();
		int[] result = srt.insertionsort(testArray);
		assertArrayEquals(expectedResult, result);
	}

	@Test
	public void gnomeSort() {
		Sorting srt = new Sorting();
		int[] result = srt.gnomeSort(testArray, testArray.length);
		assertArrayEquals(expectedResult, result);
	}

}