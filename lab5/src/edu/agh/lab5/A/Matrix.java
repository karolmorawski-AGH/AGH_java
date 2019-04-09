package edu.agh.lab5.A;

import java.util.Iterator;

public class Matrix<T> implements Iterable<T> {

	private int n;
	private int j;
	private T[][] matrix;

	public Matrix(int n, int j, T[][] matrix) {
		this.n = n;
		this.j = j;
		this.matrix = matrix;
	}


	public void add(Matrix mat) {
		if (n != mat.getN() || j != getJ()) {
			System.err.println("Invalid matrixes");
		}
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < j; k++) {
				matrix[i][k]=(T)(Integer)((Integer)matrix[i][k]+(Integer)mat.matrix[i][k]);
			}

		}
	}

	public T[][] getMatrix() {
		return matrix;
	}

	private int getN() {
		return n;
	}

	private int getJ() {
		return j;
	}

	public Iterator<T> iterator()
	{
		Iterator it = new Iterator()
		{
			//columns
			private int i = 0;
			//rows
			private int j = 0;

			@Override
			public boolean hasNext()
			{
				return i < matrix.length && j < matrix[i].length;
			}

			private void resetIndexes()
			{
				if (j == matrix[i].length)
				{
					j = 0;
					i++;
				}
			}

			@Override
			public T next()
			{
				//Check
				if (!hasNext()) { return null; }

				//Loop through the array: rows
				while (i < matrix.length)
				{
					//Loop through the array: columns
					while (j < matrix[i].length)
					{
						//Nulls are not allowed
						if (matrix[i][j] != null)
						{
							//Save value because we need to modify indexes
							T value = matrix[i][j];

							//Increment now because the return will
							//exit the loop
							j++;

							//Reset j then increment i because the
							//return will exit the loop
							if (j == matrix[i].length)
							{
								j = 0;
								i++;
							}

							return value;
						}
						//If null move on
						else
						{
							j++;
							if (j == matrix[i].length)
							{
								j = 0;
								i++;
							}
						}
					}
					i++;
				}
				return null;
			}

			@Override
			public void remove()
			{
				//do nothing
			}
		};

		return it;
	}

}