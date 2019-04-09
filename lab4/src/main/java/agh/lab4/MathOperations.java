package agh.lab4;

public class MathOperations {

	private String name;
	private String functionname;
	private int position;

	public MathOperations(String name, String functionname, int position) {
		this.name = name;
		this.functionname = functionname;
		this.position = position;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getFunctionname() {
		return functionname;
	}

	public int getPosition() {
		return position;
	}
}
