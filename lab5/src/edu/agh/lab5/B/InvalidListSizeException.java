package edu.agh.lab5.B;

public class InvalidListSizeException extends  Exception {

	public InvalidListSizeException() {
	}

	@Override
	public String getMessage() {
		String msg = "List size cannot exceed specified value. See http://home.agh.edu.pl/~phajder/wp-content/uploads/java2019/Lab3.pdf";
		return msg;
	}
}
