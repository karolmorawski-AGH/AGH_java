package edu.agh.lab5.D;

public class EmptyArrayTargetException extends  Exception {

	public EmptyArrayTargetException() {
		super("Array cannot be empty");
	}

	@Override
	public String getMessage() {
		String msg = "Array cannot be empty";
		return msg;
	}

}
