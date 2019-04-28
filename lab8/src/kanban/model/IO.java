package kanban.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {

	private File FH;

	//Constructor
	public IO(File file) {
		this.FH = file;
	}

	//Reads all lines and stores in list
	public List<String> readLine() throws IOException {
		List<String> allLines = new ArrayList<>();
		BufferedReader BR = new BufferedReader(new FileReader(FH));

		try {
			String line;
			while ((line = BR.readLine()) != null) {
				allLines.add(line);
			}
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			BR.close();
			return allLines;
		}
	}

	//Writes all lines to text file
	public void writeTxt(ArrayList<String> line) throws IOException {
		BufferedWriter WR = new BufferedWriter(new FileWriter(FH));

		try {
			for (int i = 0; i < line.size(); i++) {
				WR.write(line.get(i) + "\n");
			}
		} catch (IOException e) {
			System.err.println("Caught IOException " + e.getMessage());
		} finally {
			WR.close();
		}
	}

	//Writes all lines to CSV (2nd argument specifies separator symbol)
	public void writeCsv(ArrayList<ArrayList<String>> line, String separator) throws IOException {
		BufferedWriter WR = new BufferedWriter(new FileWriter(FH));

		try {
			for (ArrayList<String> sublist : line) {
				for(int i=0; i<sublist.size()-1; i++) {
					WR.write(sublist.get(i) + separator);
				}
				WR.write(sublist.get(sublist.size() - 1) + "\n");
			}
		}
		catch (IOException e) { System.err.println("Caught IOException: " + e.getMessage()); }
		finally {
			WR.close();
		}
	}

}
