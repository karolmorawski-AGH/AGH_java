package edu.agh.lab5.B;

public class EmptyListException extends  Exception {

	public EmptyListException() {
	}

	@Override
	public String getMessage() {
		String msg = "List cannot be empty";
		return msg;
	}
}
