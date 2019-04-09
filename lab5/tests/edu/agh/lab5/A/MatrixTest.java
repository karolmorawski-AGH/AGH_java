package edu.agh.lab5.A;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MatrixTest {

	Integer arr1[][] = {{1,2,3},{4,5,6},{7,8,9}};
	Integer arr2[][] = {{1,1,1},{1,1,1},{1,1,1}};

	@Test
	public void checkIfAdditionResultIsCorrect() {
		//Here because junit5 not available (@BeforeAll) and arrays are modified every addition operation
		Matrix<Integer> array1 = new Matrix<>(3,3, arr1);
		Matrix<Integer> array2 = new Matrix<>(3,3, arr2);

		Integer result[][] = {{2,3,4},{5,6,7},{8,9,10}};
		array1.add(array2);
		assertArrayEquals(array1.getMatrix(), result);
	}

	@Test
	public void checkIfIteratorWorksCorrectly() {

		String output = "";

		Matrix<Integer> array1 = new Matrix<>(3,3, arr1);
		Iterator<Integer> iterator = array1.iterator();
		while(iterator.hasNext()) {
			Integer current = iterator.next();
			output = output + current + ", ";
		}

		assertEquals(output, "1, 2, 3, 4, 5, 6, 7, 8, 9, ");
	}


	@Test
	public void returnsCorrectArray() {
		//Here because junit5 not available (@BeforeAll) and arrays are modified every addition operation
		Matrix<Integer> array1 = new Matrix<>(3,3, arr1);
		assertArrayEquals(array1.getMatrix(), arr1);
	}
}